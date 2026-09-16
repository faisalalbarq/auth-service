package mzn.faisal.employeesmanagement.business.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Value("${db.write.url}")
    private String writeUrl;

    @Value("${db.write.username}")
    private String writeUsername;

    @Value("${db.write.password}")
    private String writePassword;

    @Value("${db.read.url}")
    private String readUrl;

    @Value("${db.read.username}")
    private String readUsername;

    @Value("${db.read.password}")
    private String readPassword;

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(writeUrl)
                .username(writeUsername)
                .password(writePassword)
//                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean(name = "readDataSource")
    public DataSource readDataSource() {
        return DataSourceBuilder.create()
                .url(readUrl)
                .username(readUsername)
                .password(readPassword)
//                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
        return liquibase;
    }
}
