package cn.org.dianjiu.task.common.job;

/**
 * @ProjectName: task-manage
 * @Package: cn.org.dianjiu.task.common.job
 * @ClassName: ExecuteStrategy
 * @Author: MengWei
 * @Description: 定时任务的执行策略入口
 * @Date: 2020/7/6 22:44
 * @Version: 1.0
 */
public interface ExecuteStrategy {
    /**
     * 执行任务
     * @return
     */
    String executeTask();
}
