package dev.paie.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:app.properties")
public class DataSourceMySQLConfig {
	
	@Bean
	public DataSource dataSource(@Value("${driver}") String driver,
			@Value("${url}") String url,
			@Value("${userdbname}") String username,
			@Value("${password}") String password) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
//		dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("");

		return dataSource;
	}
}
