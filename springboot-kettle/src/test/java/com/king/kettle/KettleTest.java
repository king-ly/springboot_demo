package com.king.kettle;

import org.junit.Test;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;


public class KettleTest {
    @Test
    public void testJob() {
        String jobname = getFilePath() + "kettle/job1.kjb";
        try {
            //初始化
            KettleEnvironment.init();
            //加载路径信息
            JobMeta JobMeta = new JobMeta(jobname, null);
            Job job = new Job(null, JobMeta);
            //启动
            job.start();
            //等待运行完成
            job.waitUntilFinished();

        } catch (KettleException e) {
            e.printStackTrace();
        }
    }

    //执行转换
    @Test
    public void testTrans() {
        String filename = getFilePath() + "kettle/test.ktr";
        try {
            //初始化
            KettleEnvironment.init();
            //将filename存储的数据加载到trans对象中
            TransMeta transMeta = new TransMeta(filename);
            Trans trans = new Trans(transMeta);

            //mysql数据库的地址,需要根据domain从database配置中获取
            //todo
            trans.setParameterValue("my_hosts", "192.168.99.124");
            trans.setParameterValue("my_name", "root");
            trans.setParameterValue("my_pwd", "123456");
            trans.setParameterValue("my_port", "3306");
            //todo
            trans.setParameterValue("my_schme", "kettle_test");

            //pg参数,需要根据domain从database配置中获取
            //todo
            trans.setParameterValue("pg_hosts", "192.168.99.124");
            trans.setParameterValue("pg_port", "5432");
            trans.setParameterValue("pg_name", "postgre");
            trans.setParameterValue("pg_pwd", "postgres");
            trans.setParameterValue("pg_db", "postgres");
            trans.setParameterValue("pg_schme", "public");

            //转换参数
            trans.setParameterValue("startTime", "2019-08-01 18:09:20");
            trans.setParameterValue("endTime", "2019-08-01 21:09:43");

            //启动
            trans.execute(null);
            //等待文件的运行完成
            trans.waitUntilFinished();
        } catch (KettleException e) {
            e.printStackTrace();
        }
    }

    // 获得文件绝对地址(项目绝对地址+我的文件相对项目的地址)
    private String getFilePath() {
        String absolute = this.getClass().getClassLoader().getResource("").getPath();
        if (absolute.startsWith("/")) {
            absolute = absolute.substring(1);
        }
        return absolute;
    }
}
