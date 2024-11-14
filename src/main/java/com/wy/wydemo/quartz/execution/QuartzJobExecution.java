package com.wy.wydemo.quartz.execution;

import com.wy.wydemo.model.entity.Task;
import com.wy.wydemo.quartz.utils.TaskInvokeUtils;
import org.quartz.JobExecutionContext;

/**
 * @description: 定时任务处理（允许并发执行）
 * @class: QuartzJobExecution
 * @author: yu_wei
 * @create: 2024/11/11 09:52
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Task task) throws Exception {
        TaskInvokeUtils.invokeMethod(task);
    }
}
