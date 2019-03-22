package batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class TransactionManagerConfig {

    @Resource
    private DataSource dataSource;

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManagerInit() {
        return new DataSourceTransactionManager(dataSource);
    }
}
