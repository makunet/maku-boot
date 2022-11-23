package net.maku.monitor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Redis Info
 *
 * @author Pure tea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cache {

    private String cacheKey;

    private Object cacheValue;
}
