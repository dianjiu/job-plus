package cn.org.dianjiu.task.controller;

import cn.org.dianjiu.task.common.req.TTaskRecordsReq;
import cn.org.dianjiu.task.common.resp.TTaskRecordsResp;
import cn.org.dianjiu.task.common.vo.RespVO;
import cn.org.dianjiu.task.service.TTaskRecordsServiceI;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import java.util.List;

/**
 * 定时任务执行情况记录表(TTaskRecords)表控制层
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@RestController
@RequestMapping("/tTaskRecords")
public class TTaskRecordsController {
    
    /**
     * 服务对象
     */
    @Autowired
    private TTaskRecordsServiceI tTaskRecordsService;

    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping(value = "/get/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TTaskRecordsResp> getById(@PathVariable Integer id) {
      RespVO<TTaskRecordsResp> result = new RespVO<>();
        TTaskRecordsResp tTaskRecordsResp = tTaskRecordsService.getById(id);
      if(null == tTaskRecordsResp){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tTaskRecordsResp);
        return result;
   }

   /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tTaskRecordsReq
     * @return 实例对象
     */
    @GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TTaskRecordsResp> getByEntity(TTaskRecordsReq tTaskRecordsReq) {
      RespVO<TTaskRecordsResp> result = new RespVO<>();
        TTaskRecordsResp tTaskRecordsResp = tTaskRecordsService.getByEntity(tTaskRecordsReq);
      if(null == tTaskRecordsResp){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tTaskRecordsResp);
        return result;
   }

   /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tTaskRecordsReq 实例对象
     * @return 对象列表
     */
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<List> list(TTaskRecordsReq tTaskRecordsReq) {
        RespVO<List> result = new RespVO<>();
      List<TTaskRecordsResp> tTaskRecordsRespList = tTaskRecordsService.listByEntity(tTaskRecordsReq);
        if(null == tTaskRecordsRespList || tTaskRecordsRespList.isEmpty()){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("请求成功！");
        result.setData(tTaskRecordsRespList);
        return result;
    }

   /**
     * 新增实体属性不为null的记录
     *
     * @param tTaskRecordsReq 实例对象
     * @return 实例对象
     */
    @PostMapping(value = "/insert",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TTaskRecordsResp> insert(@RequestBody @Validated TTaskRecordsReq tTaskRecordsReq){
        RespVO<TTaskRecordsResp> result = new RespVO<>();
      int insert = tTaskRecordsService.insert(tTaskRecordsReq);
        if(insert !=1 ){
            result.setCode("400");
            result.setMsg("新增数据失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("新增数据成功！");
        return result;
    }

   /**
     * 通过表字段修改实体属性不为null的列
     *
     * @param tTaskRecordsReq 实例对象
     * @return 实例对象
     */
    @PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TTaskRecordsResp> update(@RequestBody @Validated TTaskRecordsReq tTaskRecordsReq){
        RespVO<TTaskRecordsResp> result = new RespVO<>();
      int update = tTaskRecordsService.update(tTaskRecordsReq);
      if(update != 1){
            result.setCode("400");
            result.setMsg("更新数据失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("更新数据成功！");
        return result;
   }
   
   /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TTaskRecordsResp> deleteOne(@PathVariable Integer id){
      RespVO<TTaskRecordsResp> result = new RespVO<>();
        int delete = tTaskRecordsService.deleteById(id);
      if(delete != 1){
            result.setCode("400");
            result.setMsg("删除数据失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("删除数据成功！");
        return result;
    }

    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param ids 实例对象
     * @return 实例对象
     */
    @DeleteMapping(value = "/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TTaskRecordsResp> deleteBatch(@RequestBody List<Integer> ids){
        RespVO<TTaskRecordsResp> result = new RespVO<>();
      int dels = 0;
        if (ids!=null&&ids.size()>0) {
         dels = tTaskRecordsService.deleteByIds(ids);
      }
      if(dels <= 0){
            result.setCode("400");
            result.setMsg("批量删除数据失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("批量删除数据成功！");
        return result;
    }

}