package com.king.etcd;

import com.google.common.util.concurrent.ListenableFuture;
import com.xqbase.etcd4j.EtcdClient;
import com.xqbase.etcd4j.EtcdClientException;
import com.xqbase.etcd4j.EtcdResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Slf4j
public class EtcdTest {
    EtcdClient client = new EtcdClient(URI.create("http://192.168.99.124:42379/"));

    @Test
    public void testEtcd() throws EtcdClientException {
        String key = "/watch12";
        client.set(key, "hello222222211111");
        String result = client.get(key);
        log.info("结果：{}",result);
    }


    @Test
    public void testWatch(){
        String key = "/watch";
        while (true) {
            try {
                ListenableFuture<EtcdResult> watchFuture = client.watch(key);
                EtcdResult watchResult = watchFuture.get(10000000, TimeUnit.MILLISECONDS);
                log.info("结监听结果：{}",watchResult.node.value);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
