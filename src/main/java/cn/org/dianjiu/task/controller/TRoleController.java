package cn.org.dianjiu.task.controller;

import cn.org.dianjiu.task.common.req.TRoleReq;
import cn.org.dianjiu.task.common.resp.TRoleResp;
import cn.org.dianjiu.task.common.vo.RespVO;
import cn.org.dianjiu.task.service.TRoleServiceI;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import java.util.List;

/**
 * (TRole)表控制层
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@RestController
@RequestMapping("/tRole")
public class TRoleController {
    
    /**
     * 服务对象
     */
    @Autowired
    private TRoleServiceI tRoleService;

    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping(value = "/get/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TRoleResp> getById(@PathVariable Integer id) {
      RespVO<TRoleResp> result = new RespVO<>();
        TRoleResp tRoleResp = tRoleService.getById(id);
      if(null == tRoleResp){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tRoleResp);
        return result;
   }

   /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tRoleReq
     * @return 实例对象
     */
    @GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TRoleResp> getByEntity(TRoleReq tRoleReq) {
      RespVO<TRoleResp> result = new RespVO<>();
        TRoleResp tRoleResp = tRoleService.getByEntity(tRoleReq);
      if(null == tRoleResp){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tRoleResp);
        return result;
   }

   /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tRoleReq 实例对象
     * @return 对象列表
     */
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<List> list(TRoleReq tRoleReq) {
        RespVO<List> result = new RespVO<>();
      List<TRoleResp> tRoleRespList = tRoleService.listByEntity(tRoleReq);
        if(null == tRoleRespList || tRoleRespList.isEmpty()){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("请求成功！");
        result.setData(tRoleRespList);
        return result;
    }

   /**
     * 新增实体属性不为null的记录
     *
     * @param tRoleReq 实例对象
     * @return 实例对象
     */
    @PostMapping(value = "/insert",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TRoleResp> insert(@RequestBody @Validated TRoleReq tRoleReq){
        RespVO<TRoleResp> result = new RespVO<>();
      int insert = tRoleService.insert(tRoleReq);
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
     * @param tRoleReq 实例对象
     * @return 实例对象
     */
    @PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TRoleResp> update(@RequestBody @Validated TRoleReq tRoleReq){
        RespVO<TRoleResp> result = new RespVO<>();
      int update = tRoleService.update(tRoleReq);
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
    public RespVO<TRoleResp> deleteOne(@PathVariable Integer id){
      RespVO<TRoleResp> result = new RespVO<>();
        int delete = tRoleService.deleteById(id);
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
    public RespVO<TRoleResp> deleteBatch(@RequestBody List<Integer> ids){
        RespVO<TRoleResp> result = new RespVO<>();
      int dels = 0;
        if (ids!=null&&ids.size()>0) {
         dels = tRoleService.deleteByIds(ids);
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