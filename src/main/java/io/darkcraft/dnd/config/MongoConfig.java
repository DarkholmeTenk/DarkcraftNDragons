package io.darkcraft.dnd.config;

import java.io.IOException;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import io.darkcraft.dnd.store.CharSheetRepository;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses=CharSheetRepository.class)
public class MongoConfig extends AbstractMongoConfiguration
{
	@Value("${mongo.endpoint}")
	private String mongoEndpoint;

	@Value("${mongo.dbName}")
	private String dbName;
	
	@Autowired
	private ObjectMapper mapper;
	
    @Override
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());

        return new MappingMongoConverter(dbRefResolver, mongoMappingContext()) {
            @Override
            public <S> S read(Class<S> clazz, Bson dbo) {
                sortID(dbo);
                String string = JSON.serialize(dbo);
                try {
                    return mapper.readValue(string, clazz);
                } catch (IOException e) {
                    throw new RuntimeException(string, e);
                }
            }

            @Override
            public void write(Object obj, Bson dbo) {
                String string = null;
                try {
                    string = mapper.writeValueAsString(obj);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(string, e);
                }
                addAllToMap(dbo, (Map<String, ?>) JSON.parse(string));
            }
        };
    }
    
    private static String getID(Object o)
    {
        return o.toString();
    }
    
    private static void sortID(Bson bson)
    {
        if (bson instanceof Document) {
            Document d = ((Document) bson);
            d.put("id", getID(d.get("_id")));
            return;
        }

        if (bson instanceof DBObject) {
            DBObject d = ((DBObject) bson);
            d.put("id", getID(d.get("_id")));
            return;
        }
    }
    
    private static void addAllToMap(Bson bson, Map<String, ?> value) {

        if (bson instanceof Document) {
            ((Document) bson).putAll(value);
            return;
        }

        if (bson instanceof DBObject) {
            ((DBObject) bson).putAll(value);
            return;
        }

        throw new IllegalArgumentException(
                String.format("Cannot add all to %s. Given Bson must be a Document or DBObject.", bson.getClass()));
    }

    @Override
    public MongoClient mongoClient()
    {
        if(StringUtils.isEmpty(dbName))
            dbName = "dnd";
        if(StringUtils.isEmpty(mongoEndpoint))
            mongoEndpoint = "127.0.0.1";
        return new MongoClient(mongoEndpoint);
    }

    @Override
    protected String getDatabaseName()
    {
        return dbName;
    }
}
