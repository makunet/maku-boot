package net.maku.framework.common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {

    // 自定义Timestamp序列化器（Timestamp对象 -> JSON字符串）
    public static class TimestampSerializer extends StdScalarSerializer<Timestamp> {
        private final SimpleDateFormat sdf;

        public TimestampSerializer() {
            super(Timestamp.class);
            this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        }

        @Override
        public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if (value == null) {
                gen.writeNull();
                return;
            }
            // 按指定格式序列化
            gen.writeString(sdf.format(value));
        }
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return builder -> {

            // 自定义Timestamp序列化器（Timestamp对象 -> JSON字符串）
            builder.serializerByType(Timestamp.class, new TimestampSerializer());

            builder.serializerByType(LocalDateTime.class,
                    new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            builder.serializerByType(LocalDate.class,
                    new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            builder.serializerByType(LocalTime.class,
                    new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
            builder.deserializerByType(LocalDateTime.class,
                    new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            builder.deserializerByType(LocalDate.class,
                    new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            builder.deserializerByType(LocalTime.class,
                    new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
            // builder.serializationInclusion(JsonInclude.Include.NON_NULL);

            // 避免Long精度丢失，超过JS最大精度，使用String类型
            builder.serializerByType(Long.class, BigNumberSerializer.INSTANCE);
            
            // 避免BigDecimal精度丢失
            builder.serializerByType(BigDecimal.class, new BigDecimalSerializer());

            builder.failOnUnknownProperties(false);
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }
}
