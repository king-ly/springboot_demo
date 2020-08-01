package com.xkcoding.task.quartz.job;

import cn.hutool.core.date.DateUtil;
import com.xkcoding.task.quartz.job.base.BaseJob;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * <p>
 * Hello Job
 * </p>
 *
 * @package: com.xkcoding.task.quartz.job
 * @description: Hello Job
 * @author: yangkai.shen
 * @date: Created in 2018-11-26 13:22
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
//@DisallowConcurrentExecution    //当上一个任务没有完成，下一个任务不会执行。
public class HelloJob implements BaseJob {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) {
        log.error("Hello Job ====执行时间: {}", DateUtil.now());

    }
}
