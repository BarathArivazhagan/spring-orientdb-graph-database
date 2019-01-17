package com.barath.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

@Configuration
public class OrietnDBGraphDatabaseConfiguration {
	
	
	@Value("${orientdb.url}")
	private String graphUrl;
	@Value("${orientdb.username}")
	private String  userName;
	@Value("${orientdb.password}")
	private String password;
	
	@Bean
	public OrientGraphFactory graphFactory() {
		return new OrientGraphFactory(graphUrl, userName, "admin");
	}

}
