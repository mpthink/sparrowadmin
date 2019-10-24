package com.think.sparrowadmin.remexplus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.think.sparrowadmin.common.bean.Rest;
import com.think.sparrowadmin.common.config.Config;
import com.think.sparrowadmin.common.controller.SuperController;
import com.think.sparrowadmin.common.scheduler.IScheduleCronTaskService;
import com.think.sparrowadmin.remexplus.entity.RpTask;
import com.think.sparrowadmin.remexplus.remexplusUtils.CronValidate;
import com.think.sparrowadmin.remexplus.remexplusUtils.RemexPomValidate;
import com.think.sparrowadmin.remexplus.service.IRpTaskRecordService;
import com.think.sparrowadmin.remexplus.service.IRpTaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * table for creating regular task 前端控制器
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
@Controller
@RequestMapping("/remexplus/task")
public class RpTaskController extends SuperController {

    @Autowired
    private IRpTaskService rpTaskService;

    @Autowired
    private IRpTaskRecordService rpTaskRecordService;

    @Autowired
    private IScheduleCronTaskService scheduleCronTaskService;
    /**
     * 分页查询
     */
    @RequestMapping("/list/{pageNumber}")
    public  String list(@PathVariable Integer pageNumber, @RequestParam(defaultValue="15") Integer pageSize, String search, Model model){
        if(StringUtils.isNotBlank(search)){
            model.addAttribute("search", search);
        }
        Page<RpTask> page = getPage(pageNumber,pageSize);
        model.addAttribute("pageSize", pageSize);
        IPage<RpTask> pageData = rpTaskService.selectTaskPage(page, search);
        model.addAttribute("pageData", pageData);

        return "remexplus/task/list";
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public  String add(Model model){
        return "remexplus/task/add";
    }

    /**
     * 执行新增
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public Rest doAdd(RpTask task){
        //validate cron expression, and remex pom file
        if(!CronValidate.isValidExpression(task.getQuartz())){
            return Rest.failure("The format of cron expression is wrong, please check!");
        }
        String remexPomAbsolutePath = Config.UPLOAD_FOLDER + task.getRemexPom().replace("/upload/","");
        if(RemexPomValidate.validateRemexPomWithFilePath(remexPomAbsolutePath) == 0){
            return Rest.failure("The format of remex pom file is wrong, please double check!");
        }
        rpTaskService.save(task);
        scheduleCronTaskService.addCronTask(task);
        return Rest.ok("Success");
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public  Rest delete(String id){
        rpTaskService.removeById(id);
        rpTaskRecordService.removeRelatedTaskRecordAndJob(id);
        scheduleCronTaskService.removeCronTask(id);
        return Rest.ok();
    }

    /**
     * 编辑
     */
    @RequestMapping("/edit/{id}")
    public  String edit(@PathVariable String id,Model model){
        RpTask rpTask = rpTaskService.getById(id);
        model.addAttribute("rpTask",rpTask);
        return "remexplus/task/edit";
    }

    /**
     * 执行编辑
     */
    @RequestMapping("/doEdit")
    @ResponseBody
    public  Rest doEdit(RpTask rpTask, Model model){

        if(!CronValidate.isValidExpression(rpTask.getQuartz())){
            return Rest.failure("The format of cron expression is wrong, please check!");
        }
        String remexPomAbsolutePath = Config.UPLOAD_FOLDER + rpTask.getRemexPom().replace("/upload/","");
        if(RemexPomValidate.validateRemexPomWithFilePath(remexPomAbsolutePath) == 0){
            return Rest.failure("The format of remex pom file is wrong, please double check!");
        }

        rpTaskService.saveOrUpdate(rpTask);
        scheduleCronTaskService.updateCronTask(rpTask);
        return Rest.ok();
    }

    @RequestMapping("/last/status")
    @ResponseBody
    public String lastStatus(){
        return toJson(rpTaskService.getTaskLastStatus());
    }
}

