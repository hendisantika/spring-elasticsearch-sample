package com.hendisantika.springelasticsearchsample.repository;

import com.hendisantika.springelasticsearchsample.AbstractElasticsearchContainerTest;
import com.hendisantika.springelasticsearchsample.domain.Department;
import com.hendisantika.springelasticsearchsample.domain.Employee;
import com.hendisantika.springelasticsearchsample.domain.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-elasticsearch-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/12/19
 * Time: 06.37
 * <p>
 * Lightweight concurrent load test of the REST API (replacement for the JUnit 4 based junit-benchmarks test).
 */
class EmployeeRepositoryPerformanceTest extends AbstractElasticsearchContainerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryPerformanceTest.class);
    private static final int CONCURRENCY = 10;
    private static final int ROUNDS = 50;

    @LocalServerPort
    private int port;

    private RestClient client;

    @BeforeEach
    void setUp() {
        client = RestClient.create("http://localhost:" + port);
    }

    @Test
    void addTest() throws Exception {
        List<Employee> results = runConcurrently(() -> {
            ThreadLocalRandom r = ThreadLocalRandom.current();
            Employee employee = new Employee();
            employee.setName("John Smith");
            employee.setAge(r.nextInt(100));
            employee.setPosition("Developer");
            employee.setDepartment(new Department((long) r.nextInt(1000), "TestD"));
            employee.setOrganization(new Organization((long) r.nextInt(100), "TestO", "Test Street No. 1"));
            return client.post().uri("/employees").body(employee).retrieve().body(Employee.class);
        });

        assertThat(results).hasSize(ROUNDS).allSatisfy(e -> assertThat(e.getId()).isNotBlank());
    }

    @Test
    void findByNameTest() throws Exception {
        List<Employee[]> results = runConcurrently(() -> client.get()
                .uri("/employees/{name}", "JohnSmith" + ThreadLocalRandom.current().nextInt(1_000_000))
                .retrieve()
                .body(Employee[].class));

        assertThat(results).hasSize(ROUNDS).doesNotContainNull();
    }

    @Test
    void findByOrganizationNameTest() throws Exception {
        List<Employee[]> results = runConcurrently(() -> client.get()
                .uri("/employees/organization/{organizationName}", "TestO" + ThreadLocalRandom.current().nextInt(5000))
                .retrieve()
                .body(Employee[].class));

        assertThat(results).hasSize(ROUNDS).doesNotContainNull();
    }

    private <T> List<T> runConcurrently(Callable<T> task) throws Exception {
        long start = System.nanoTime();
        List<T> results = new ArrayList<>();
        try (ExecutorService executor = Executors.newFixedThreadPool(CONCURRENCY)) {
            List<Future<T>> futures = new ArrayList<>();
            for (int i = 0; i < ROUNDS; i++) {
                futures.add(executor.submit(task));
            }
            for (Future<T> future : futures) {
                results.add(future.get());
            }
        }
        LOGGER.info("{} requests with concurrency {} took {} ms", ROUNDS, CONCURRENCY,
                (System.nanoTime() - start) / 1_000_000);
        return results;
    }
}
