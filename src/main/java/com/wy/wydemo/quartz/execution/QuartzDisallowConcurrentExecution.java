package com.wy.wydemo.quartz.execution;

import com.wy.wydemo.model.entity.Task;
import com.wy.wydemo.quartz.utils.TaskInvokeUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * @description:  定时任务处理（禁止并发执行）
 * @class: QuartzDisallowConcurrentExecution
 * @author: yu_wei
 * @create: 2024/11/11 09:55
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Task task) throws Exception {
        TaskInvokeUtils.invokeMethod(task);
    }
}

