package cn.org.dianjiu.task.entity;

import java.util.Date;
import lombok.Data;
                    import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 定时任务出错现场信息表(TTaskErrors)实体类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TTaskErrors {
    
    private Integer id;
    /**
    * 任务执行记录Id
    */
    private String taskexecuterecordid;
    /**
    * 信息关键字
    */
    private String errorkey;
    /**
    * 信息内容
    */
    private String errorvalue;
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