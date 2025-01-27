package net.maku.framework.common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;

import java.io.IOException;

/**
 * 解决js精度丢失问题
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@JacksonStdImpl
public class BigNumberSerializer extends NumberSerializer {
    /**
     * JS Number.MAX_SAFE_INTEGER  Number.MIN_SAFE_INTEGER
     */
    private static final long MAX_SAFE_INTEGER = 9007199254740991L;
    private static final long MIN_SAFE_INTEGER = -9007199254740991L;
    /**
     * 提供实例
     */
    public static final BigNumberSerializer INSTANCE = new BigNumberSerializer(Number.class);

    public BigNumberSerializer(Class<? extends Number> rawType) {
        super(rawType);
    }

    @Override
    public void serialize(Number value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // 超出范围 序列化位字符串
        if (value.longValue() > MIN_SAFE_INTEGER && value.longValue() < MAX_SAFE_INTEGER) {
            super.serialize(value, gen, provider);
        } else {
            gen.writeString(value.toString());
        }
    }
}