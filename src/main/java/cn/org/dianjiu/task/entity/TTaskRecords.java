package cn.org.dianjiu.task.entity;

import java.util.Date;
import lombok.Data;
                                        import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 定时任务执行情况记录表(TTaskRecords)实体类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TTaskRecords {
    
    private Integer id;
    /**
    * 任务名称
    */
    private String taskName;
    /**
    * 分组名称
    */
    private String groupName;
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
    * 返回信息
    */
    private String returnInfo;
    /**
    * 执行时间
    */
    private Date executeTime;
    /**
    * 任务状态
    */
    private String taskStatus;
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