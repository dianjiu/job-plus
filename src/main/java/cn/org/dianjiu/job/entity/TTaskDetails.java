package cn.org.dianjiu.job.entity;

import java.util.Date;
import lombok.Data;
/**
 * 定时任务信息表(TTaskDetails)实体类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TTaskDetails {
    
    private Integer id;
    /**
    * 任务编号
    */
    private String taskNo;
    /**
    * 任务名称
    */
    private String taskName;
    /**
    * 分组编号
    */
    private String groupNo;
    /**
    * 分组名称
    */
    private String groupName;
    /**
    * 任务描述
    */
    private String taskDesc;
    /**
    * CRON表达式
    */
    private String cornRule;
    /**
    * 请求方式
    */
    private String sendType;
    /**
    * 请求地址
    */
    private String sendUrl;
    /**
    * 请求参数
    */
    private String sendParam;
    /**
    * 任务状态 0-停用 1-启用
    */
    private String status;
    /**
    * 下次执行时间
    */
    private Date nextExecuteTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String updator;
    /**
     * 修改时间
     */
    private Date updateTime;

}