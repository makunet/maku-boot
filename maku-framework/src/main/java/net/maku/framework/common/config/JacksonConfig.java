package net.maku.framework.common.config;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.ext.javatime.deser.LocalDateDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalTimeSerializer;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.ser.std.StdScalarSerializer;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Jackson 配置
 * <p>
 * Spring Boot 4 推荐使用 SimpleModule Bean + JsonMapperBuilderCustomizer，
 * 框架会自动将 Module 注册到内置的 JsonMapper 中，避免多个 ObjectMapper Bean 冲突。
 */
@Configuration
public class JacksonConfig {

    public static class TimestampSerializer extends StdScalarSerializer<Timestamp> {
        private final SimpleDateFormat sdf;

        public TimestampSerializer() {
            super(Timestamp.class);
            this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        }

        @Override
        public void serialize(Timestamp value, JsonGenerator gen, SerializationContext provider)
                throws JacksonException {
            if (value == null) {
                gen.writeNull();
                return;
            }
            gen.writeString(sdf.format(value));
        }
    }

    /**
     * 自定义序列化/反序列化模块，Spring Boot 会自动注册到 JsonMapper
     */
    @Bean
    public SimpleModule customSerializerModule() {
        SimpleModule module = new SimpleModule("CustomSerializerModule");

        // 日期时间序列化
        module.addSerializer(Timestamp.class, new TimestampSerializer());
        module.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        module.addSerializer(LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        // 日期时间反序列化
        module.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        module.addDeserializer(LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        // Long 类型序列化为 String，解决前端 js 精度丢失问题
        module.addSerializer(Long.class, BigNumberSerializer.INSTANCE);
        module.addSerializer(Long.TYPE, BigNumberSerializer.INSTANCE);
        module.addSerializer(BigDecimal.class, new BigDecimalSerializer());

        return module;
    }

    /**
     * 自定义 JsonMapper 构建器配置
     */
    @Bean
    public JsonMapperBuilderCustomizer jsonMapperBuilderCustomizer() {
        return builder -> builder
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
