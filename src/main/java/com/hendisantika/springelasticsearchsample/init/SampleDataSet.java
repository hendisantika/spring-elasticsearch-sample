package com.hendisantika.springelasticsearchsample.init;

import com.hendisantika.springelasticsearchsample.domain.Department;
import com.hendisantika.springelasticsearchsample.domain.Employee;
import com.hendisantika.springelasticsearchsample.domain.Organization;
import com.hendisantika.springelasticsearchsample.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-elasticsearch-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/12/19
 * Time: 06.27
 */
@Slf4j
@Component
public class SampleDataSet {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleDataSet.class);
    private static final String INDEX_NAME = "sample";
    private static final String INDEX_TYPE = "employees";
    private static final int COUNTER = 0;

//    @Autowired
//    private ElasticsearchTemplate template;

//    @Autowired
//    private TaskExecutor taskExecutor;
//
//    @PostConstruct
//    public void init() {
//        if (!template.indexExists(INDEX_NAME)) {
//            template.createIndex(INDEX_NAME);
//            LOGGER.info("New index created: {}", INDEX_NAME);
//        }
//        for (int i = 0; i < 10000; i++) {
//            taskExecutor.execute(() -> bulk());
//        }
//    }
//
//    @Bean
//    public Client elasticsearchClient() throws UnknownHostException {
//        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//        return client;
//    }

//    @Bean(name = { "elasticsearchOperations", "elasticsearchTemplate" })
//    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
//        return new ElasticsearchTemplate(elasticsearchClient());
//    }
//
//    public void bulk() {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            List<IndexQuery> queries = new ArrayList<>();
//            List<Employee> employees = employees();
//            for (Employee employee : employees) {
//                IndexQuery indexQuery = new IndexQuery();
//                indexQuery.setSource(mapper.writeValueAsString(employee));
//                indexQuery.setIndexName(INDEX_NAME);
//                indexQuery.setType(INDEX_TYPE);
//                queries.add(indexQuery);
//            }
//            if (queries.size() > 0) {
//                template.bulkIndex(queries);
//            }
//            template.refresh(INDEX_NAME);
//            LOGGER.info("BulkIndex completed: {}", ++COUNTER);
//        } catch (Exception e) {
//            LOGGER.error("Error bulk index", e);
//        }
//    }

    private List<Employee> employees() {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Random r = new Random();
            Employee employee = new Employee();
            employee.setName("JohnSmith" + r.nextInt(1000000));
            employee.setAge(r.nextInt(100));
            employee.setPosition("Developer");
            int departmentId = r.nextInt(500000);
            employee.setDepartment(new Department((long) departmentId, "TestD" + departmentId));
            int organizationId = departmentId / 100;
            employee.setOrganization(new Organization((long) organizationId, "TestO" + organizationId, "Test Street No. " + organizationId));
            employees.add(employee);
        }
        return employees;
    }

    @Bean
    public CommandLineRunner initDatabase(EmployeeRepository employeeRepository) {
        return args -> {
            employeeRepository.saveAll(employees());
            log.info("Employee data saved");
        };
    }

}
