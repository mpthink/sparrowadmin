/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.remexplus.scheduler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.think.sparrowadmin.remexplus.entity.RpTask;
import com.think.sparrowadmin.remexplus.service.IRpTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @author map6
 */
@Component
public class ScheduleCronTaskServiceImpl implements IScheduleCronTaskService {

    private static Map<String, ScheduledFuture<?>> futureMap = new HashMap<>();

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @Autowired
    private IRpTaskService taskService;

    @PostConstruct
    private void init(){
        QueryWrapper<RpTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",0);
        List<RpTask> rpTaskList = taskService.list(queryWrapper);
        rpTaskList.forEach(task -> {
            ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(new TaskRunner(task), new CronTrigger(task.getQuartz()));
            futureMap.put(task.getId(),future);
        });
    }

    @Override
    public void updateCronTask(RpTask task) {
        removeCronTask(task.getId());
        if(task.getStatus() == 0) {
            addCronTask(task);
        }
    }

    @Override
    public void addCronTask(RpTask task) {
        ScheduledFuture<?> scheduledFuture = futureMap.get(task.getId());
        if(scheduledFuture == null){
            ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(new TaskRunner(task), new CronTrigger(task.getQuartz()));
            futureMap.put(task.getId(),future);
        }
    }

    @Override
    public void removeCronTask(String taskId) {
        ScheduledFuture<?> scheduledFuture = futureMap.get(taskId);
        if(scheduledFuture != null){
            scheduledFuture.cancel(true);
            futureMap.remove(taskId);
        }
    }
}
