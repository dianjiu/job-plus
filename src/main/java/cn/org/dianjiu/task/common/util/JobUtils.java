package cn.org.dianjiu.task.common.util;

import cn.org.dianjiu.task.common.constants.Constant;
import org.quartz.CronExpression;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Component
public class JobUtils {

    @Autowired
    private Scheduler scheduler;

    /**
     * 根据jobName和jobGroup生成triggerKey
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    public TriggerKey getTriggerKeyByJob(String jobName, String jobGroup) {
        String triggerName = Constant.TRIGGER_PREFIX + jobName;
        String triggerGroup = Constant.TRIGGER_PREFIX + jobGroup;
        return TriggerKey.triggerKey(triggerName, triggerGroup);
    }

    /**
     * 生成组别编码
     */
    public static String getGroupNo() {
        return Constant.Group_PREFIX + getCode();
    }

    /**
     * 生成任务编码
     */
    public static String getTaskNo() {
        return Constant.TASK_PREFIX + getCode();
    }

    /**
     * 获取job状态
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    public String getJobStatusInfo(String jobName, String jobGroup) {
        String jobStatusInfo = "";
        TriggerKey triggerKey = getTriggerKeyByJob(jobName, jobGroup);
        try {
            jobStatusInfo = scheduler.getTriggerState(triggerKey).name();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return jobStatusInfo;
    }

    /**
     * 根据cron表达式获取下次执行时间
     *
     * @param cronExpression
     * @return
     */
    public static Date getNextFireDate(String cronExpression) {
        try {
            CronExpression cron = new CronExpression(cronExpression);
            Date nextFireDate = cron.getNextValidTimeAfter(new Date());
            return nextFireDate;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成不带类别标头的编码
     */
    private static synchronized String getCode() {
        return UUID.randomUUID().toString();
        //return getDateTime() + getRandom(4);
    }

    /**
     * 生成时间戳
     */
    private static String getDateTime() {
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    /**
     * 生成固定长度随机码
     *
     * @param len 长度
     */
    public static String getRandom(int len) {
        String source = "0123456789";
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int j = 0; j < len; j++) {
            rs.append(source.charAt(r.nextInt(10)));
        }
        return rs.toString();
    }
}
