package io.darkcraft.dnd.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class JacksonConfig
{
    @Bean
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
                        return Enum.valueOf(rawClass, jp.getValueAsString().toUpperCase());
                    }
                };
            }
        });
        return module;
    }
}
