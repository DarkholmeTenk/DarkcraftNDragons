package io.darkcraft.dnd.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

@Configuration
public class JacksonConfig
{
    public Module getEnumModule()
    {
        SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new BeanDeserializerModifier()
        {
            @Override
            public JsonDeserializer<Enum> modifyEnumDeserializer(DeserializationConfig config, final JavaType type,
                    BeanDescription beanDesc, final JsonDeserializer<?> deserializer)
            {
                return new JsonDeserializer<Enum>()
                {
                    @Override
                    public Enum deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException
                    {
                        Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
                        return Enum.valueOf(rawClass, jp.getValueAsString().replace(' ', '_').toUpperCase());
                    }
                };
            }
        });
        return module;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customize()
    {
        return builder -> {
            builder.modulesToInstall(new GuavaModule(), getEnumModule());
        };
    }

    @Bean
    public MappingJackson2HttpMessageConverter getConverter(ObjectMapper mapper)
    {
        return new MappingJackson2HttpMessageConverter(mapper);
    }
}
