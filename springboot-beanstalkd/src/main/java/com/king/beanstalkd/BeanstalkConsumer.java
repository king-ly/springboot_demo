package com.king.beanstalkd;

import com.dinstone.beanstalkc.BeanstalkClientFactory;
import com.dinstone.beanstalkc.Configuration;
import com.dinstone.beanstalkc.Job;
import com.dinstone.beanstalkc.JobConsumer;
import java.util.Objects;

/**
 * bt消费服务端
 */

public class BeanstalkConsumer {


    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setServiceHost("192.168.99.124");
        config.setServicePort(11300);
        BeanstalkClientFactory factory = new BeanstalkClientFactory(config);
        JobConsumer consumer = factory.createJobConsumer("beanstalkd-demo");
        while (true) {
            // reserveJob 有一个超时时间参数，单位是秒，表示获取消息最多花费多长时间
            Job job = consumer.reserveJob(3);
            if (Objects.isNull(job)) {
                continue;
            }
            System.out.println("consumer1_"+job.getId());
            System.out.println("consumer1_"+new String(job.getData()));

            consumer.deleteJob(job.getId());
        }
    }
}
