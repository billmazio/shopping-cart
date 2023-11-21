package teachers.project.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class WebMvcConfiguration {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String dbURL;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${connection.pool.initialPoolSize}")
    private int connPoolInitSize;

    @Value("${connection.pool.minPoolSize}")
    private int connPoolMinSize;

    @Value("${connection.pool.maxPoolSize}")
    private int connPoolMaxSize;

    @Value("${connection.pool.maxIdleTime}")
    private int connPoolMaxIdleTime;

    // Configure a MessageSource for handling internationalization and localization.
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    // Configure a data source using C3P0 for connection pooling.
    @Bean
    public DataSource securityDataSource() {

        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        try {
            securityDataSource.setDriverClass(driver);
        }
        catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }
        securityDataSource.setJdbcUrl(dbURL);
        securityDataSource.setUser(dbUsername);
        securityDataSource.setPassword(dbPassword);

        securityDataSource.setInitialPoolSize(connPoolInitSize);
        securityDataSource.setMinPoolSize(connPoolMinSize);
        securityDataSource.setMaxPoolSize(connPoolMaxSize);
        securityDataSource.setMaxIdleTime(connPoolMaxIdleTime);

        return securityDataSource;
    }

}
