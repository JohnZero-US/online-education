package com.johnzero.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 描述:
 */
/*
  Created by IntelliJ IDEA.
  Type: Class
  User: John Zero
  DateTime: 2019/5/2 18:10
  Description: 
*/
@RefreshScope
@RestController
public class TestController {
    /**
     * 刷新配置路径
     * http://127.0.0.1:8780/actuator/bus-refresh
     */

    @Value("${server.tomcat.max-connections}")
    private int maxConnections;

    @Value("${server.tomcat.max-threads}")
    private int maxThreads;


    @Value("${server.port}")
    private String port;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getMaxConnectAndMaxThread")
    public String getMaxConnectAndMaxThread() {
        return String.format("总连接数为：%s，总线程数为：%s", String.valueOf(maxConnections), String.valueOf(maxThreads));
    }


    /**
     *
     * @return
     */
    @GetMapping("/getPort")
    public String getPort() {
        //
        String us_port = restTemplate.getForObject("http://USER-SERVICE-LOAD-BALANCED/getPort", String.class);

        return String.format("View Port:%s,User-Service port:%s", port, us_port);
    }


}
