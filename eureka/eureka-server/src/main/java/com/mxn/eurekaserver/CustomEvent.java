package com.mxn.eurekaserver;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomEvent {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event ) {
        System.out.println(LocalDateTime.now()+"服务下线事件:"+event.getAppName()+"---"+event.getServerId());
        //发短息或者通知
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.out.println(LocalDateTime.now()+"服务上线事件:"+instanceInfo.getAppName()+"---"+instanceInfo.getInstanceId());
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        System.out.println(LocalDateTime.now()+"服务续约/心跳上报事件:"+event.getAppName()+"---"+event.getServerId());

    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.out.println(LocalDateTime.now()+"注册中心可用事件");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        System.out.println(LocalDateTime.now()+"注册中心启动事件");

    }
}