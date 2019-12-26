package com.hendisantika.springelasticsearchsample.repository;

import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-elasticsearch-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/12/19
 * Time: 06.37
 */
public class EmployeeRepositoryPerformanceTest extends AbstractBenchmark {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryPerformanceTest.class);

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    private TestRestTemplate template = new TestRestTemplate();
    private Random r = new Random();
}
