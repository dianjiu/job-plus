package cn.org.dianjiu.job.common.job;

/**
 * @ProjectName: task-manage
 * @Package: cn.org.dianjiu.task.common.job
 * @ClassName: SendType
 * @Author: MengWei
 * @Description: 请求方式
 * @Date: 2020/7/6 23:08
 * @Version: 1.0
 */
public abstract class SendType {
    /**
     * 将策略接口当作抽象类的属性来封装
     */
    protected ExecuteStrategy executeStrategy;

    public void setExecuteStrategy(ExecuteStrategy executeStrategy) {
        this.executeStrategy = executeStrategy;
    }

    /**
     * 执行任务操作
     */
    public String runTask (){
        //执行任务前
        return executeStrategy.executeTask();
        //执行任务后
    }

    //更新执行记录
    //public abstract void updateTaskRecords();
}
