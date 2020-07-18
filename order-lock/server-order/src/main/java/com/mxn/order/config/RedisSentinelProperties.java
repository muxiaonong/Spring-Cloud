package com.mxn.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "sentinel")
@Order(0)
@Data
public class RedisSentinelProperties {

    private String[] address;

    private String masterName;
}
