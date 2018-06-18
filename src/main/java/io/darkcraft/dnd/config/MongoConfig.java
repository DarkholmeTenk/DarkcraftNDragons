package io.darkcraft.dnd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.StringUtils;

import com.mongodb.MongoClient;

import io.darkcraft.dnd.store.CharSheetRepository;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses=CharSheetRepository.class)
public class MongoConfig
{
	@Value("${mongo.endpoint}")
	private String mongoEndpoint;

	@Value("${mongo.dbName}")
	private String dbName;

	@Bean
	public MongoTemplate mongoTemplate()
	{
		if(StringUtils.isEmpty(dbName))
			dbName = "dnd";
		if(StringUtils.isEmpty(mongoEndpoint))
			mongoEndpoint = "127.0.0.1";
		MongoClient client = new MongoClient(mongoEndpoint);
		return new MongoTemplate(client, dbName);
	}
}
