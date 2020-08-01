package com.king.beanstalkd;

import com.dinstone.beanstalkc.BeanstalkClientFactory;
import com.dinstone.beanstalkc.Configuration;
import com.dinstone.beanstalkc.JobProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * bt生产服务端
 */

public class BeanstalkProducer {

    static final Logger log = LoggerFactory.getLogger(BeanstalkProducer.class);

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setServiceHost("192.168.99.124");
        config.setServicePort(11300);
        BeanstalkClientFactory factory = new BeanstalkClientFactory(config);
        JobProducer producer = factory.createJobProducer("beanstalkd-demo");
        String msg = "hello, beanstalkd==========================";
        // 返回job id
        long p = producer.putJob(100, 0, 5, msg.getBytes());
        System.out.println("生产者："+String.valueOf(p));
    }
}
