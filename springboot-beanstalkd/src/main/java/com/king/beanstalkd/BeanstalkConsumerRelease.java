package com.king.beanstalkd;

import com.dinstone.beanstalkc.BeanstalkClientFactory;
import com.dinstone.beanstalkc.Configuration;
import com.dinstone.beanstalkc.Job;
import com.dinstone.beanstalkc.JobConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 将未能成功消费的bt状态重置
 */
public class BeanstalkConsumerRelease {
    static final Logger log = LoggerFactory.getLogger(BeanstalkConsumerRelease.class);
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setServiceHost("192.168.99.122");
        config.setServicePort(11300);
        BeanstalkClientFactory factory = new BeanstalkClientFactory(config);
        JobConsumer consumer = factory.createJobConsumer("beanstalkd-demo");
        while (true) {
            Job job = consumer.reserveJob(3);
            if (Objects.isNull(job)) {
                continue;
            }
            System.out.println(String.valueOf(job.getId()));
            System.out.println(String.valueOf(job.getData()));
            consumer.releaseJob(job.getId(), 99, 5);
        }
    }
}
