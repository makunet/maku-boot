package net.maku.framework.common.config;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdScalarSerializer;

import java.math.BigDecimal;

/**
 * 解决js精度丢失问题
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public class BigDecimalSerializer extends StdScalarSerializer<BigDecimal> {
    private static final long MAX_SAFE_INTEGER = 9007199254740991L;

    public BigDecimalSerializer() {
        super(BigDecimal.class);
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        if (value == null) {
            gen.writeNull();
            return;
        }

        // 根据整数长度决定序列化方式
        if (value.longValue() > MAX_SAFE_INTEGER) {
            // 序列化为字符串
            gen.writeString(value.toPlainString());
        } else {
            // 序列化为数字
            gen.writeNumber(value);
        }
    }
}