package com.hendisantika.springelasticsearchsample.repository;

import com.hendisantika.springelasticsearchsample.AbstractElasticsearchContainerTest;
import com.hendisantika.springelasticsearchsample.domain.Department;
import com.hendisantika.springelasticsearchsample.domain.Employee;
import com.hendisantika.springelasticsearchsample.domain.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-elasticsearch-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/12/19
 * Time: 06.33
 */
class EmployeeRepositoryTest extends AbstractElasticsearchContainerTest {

    @Autowired
    EmployeeRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.saveAll(List.of(
                employee("John Smith", 33, "TestO"),
                employee("Uzumaki Naruto", 17, "Konoha"),
                employee("Uchiha Sasuke", 17, "Konoha")));
    }

    @Test
    void testAdd() {
        Employee saved = repository.save(employee("Hatake Kakashi", 30, "Konoha"));

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotBlank();
        assertThat(repository.findById(saved.getId())).isPresent();
    }

    @Test
    void testFindAll() {
        assertThat(repository.findAll()).hasSize(3);
    }

    @Test
    void testFindByOrganization() {
        List<Employee> employees = repository.findByOrganizationName("Konoha");

        assertThat(employees)
                .extracting(Employee::getName)
                .containsExactlyInAnyOrder("Uzumaki Naruto", "Uchiha Sasuke");
    }

    @Test
    void testFindByName() {
        List<Employee> employees = repository.findByName("Uzumaki Naruto");

        assertThat(employees)
                .singleElement()
                .satisfies(e -> assertThat(e.getOrganization().getName()).isEqualTo("Konoha"));
    }

    private static Employee employee(String name, int age, String organizationName) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        employee.setPosition("Developer");
        employee.setDepartment(new Department(1L, "TestD"));
        employee.setOrganization(new Organization(1L, organizationName, "Test Street No. 1"));
        return employee;
    }
}
