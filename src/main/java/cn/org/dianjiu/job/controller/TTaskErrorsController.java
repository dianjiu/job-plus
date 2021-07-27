package cn.org.dianjiu.job.controller;

import cn.org.dianjiu.job.common.req.TTaskErrorsReq;
import cn.org.dianjiu.job.common.resp.TTaskErrorsResp;
import cn.org.dianjiu.job.common.vo.RespVO;
import cn.org.dianjiu.job.service.TTaskErrorsServiceI;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import java.util.List;

/**
 * 定时任务出错现场信息表(TTaskErrors)表控制层
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@RestController
@RequestMapping("/tTaskErrors")
public class TTaskErrorsController {
    
    /**
     * 服务对象
     */
    @Autowired
    private TTaskErrorsServiceI tTaskErrorsService;

    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping(value = "/get/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TTaskErrorsResp> getById(@PathVariable Integer id) {
      RespVO<TTaskErrorsResp> result = new RespVO<>();
        TTaskErrorsResp tTaskErrorsResp = tTaskErrorsService.getById(id);
      if(null == tTaskErrorsResp){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tTaskErrorsResp);
        return result;
   }

   /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tTaskErrorsReq
     * @return 实例对象
     */
    @GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TTaskErrorsResp> getByEntity(TTaskErrorsReq tTaskErrorsReq) {
      RespVO<TTaskErrorsResp> result = new RespVO<>();
        TTaskErrorsResp tTaskErrorsResp = tTaskErrorsService.getByEntity(tTaskErrorsReq);
      if(null == tTaskErrorsResp){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tTaskErrorsResp);
        return result;
   }

   /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tTaskErrorsReq 实例对象
     * @return 对象列表
     */
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<List> list(TTaskErrorsReq tTaskErrorsReq) {
        RespVO<List> result = new RespVO<>();
      List<TTaskErrorsResp> tTaskErrorsRespList = tTaskErrorsService.listByEntity(tTaskErrorsReq);
        if(null == tTaskErrorsRespList || tTaskErrorsRespList.isEmpty()){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("请求成功！");
        result.setData(tTaskErrorsRespList);
        return result;
    }

   /**
     * 新增实体属性不为null的记录
     *
     * @param tTaskErrorsReq 实例对象
     * @return 实例对象
     */
    @PostMapping(value = "/insert",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TTaskErrorsResp> insert(@RequestBody @Validated TTaskErrorsReq tTaskErrorsReq){
        RespVO<TTaskErrorsResp> result = new RespVO<>();
      int insert = tTaskErrorsService.insert(tTaskErrorsReq);
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
     * @param tTaskErrorsReq 实例对象
     * @return 实例对象
     */
    @PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TTaskErrorsResp> update(@RequestBody @Validated TTaskErrorsReq tTaskErrorsReq){
        RespVO<TTaskErrorsResp> result = new RespVO<>();
      int update = tTaskErrorsService.update(tTaskErrorsReq);
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
    public RespVO<TTaskErrorsResp> deleteOne(@PathVariable Integer id){
      RespVO<TTaskErrorsResp> result = new RespVO<>();
        int delete = tTaskErrorsService.deleteById(id);
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
    public RespVO<TTaskErrorsResp> deleteBatch(@RequestBody List<Integer> ids){
        RespVO<TTaskErrorsResp> result = new RespVO<>();
      int dels = 0;
        if (ids!=null&&ids.size()>0) {
         dels = tTaskErrorsService.deleteByIds(ids);
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