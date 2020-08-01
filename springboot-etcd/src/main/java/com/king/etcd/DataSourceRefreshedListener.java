package com.king.etcd;

import com.google.common.util.concurrent.ListenableFuture;
import com.xqbase.etcd4j.EtcdClient;
import com.xqbase.etcd4j.EtcdResult;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * 添加一个监听
 */
@Component
public class DataSourceRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        new Thread(()-> {
            System.out.println("etcd监听启动！");
            //配置etcd的地址，启动docker-compose的etcd
            EtcdClient client = new EtcdClient(URI.create("http://192.168.99.124:32379/"));
            String key = "/databases.properties";
            while (true){
                try {
                    ListenableFuture<EtcdResult> watchFuture = client.watch(key);
                    EtcdResult watchResult = watchFuture.get(1000000, TimeUnit.MILLISECONDS);
                    System.out.println("监听结果：\t" + watchResult.node.value);
                    //DynamicDataUtils.init();   //调用同步properties初始化
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();
    }
}
