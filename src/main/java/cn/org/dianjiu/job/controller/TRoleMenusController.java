package cn.org.dianjiu.job.controller;

import cn.org.dianjiu.job.common.req.TRoleMenusReq;
import cn.org.dianjiu.job.common.resp.TRoleMenusResp;
import cn.org.dianjiu.job.common.vo.RespVO;
import cn.org.dianjiu.job.service.TRoleMenusServiceI;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import java.util.List;

/**
 * (TRoleMenus)表控制层
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@RestController
@RequestMapping("/tRoleMenus")
public class TRoleMenusController {
    
    /**
     * 服务对象
     */
    @Autowired
    private TRoleMenusServiceI tRoleMenusService;

    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping(value = "/get/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TRoleMenusResp> getById(@PathVariable Integer id) {
      RespVO<TRoleMenusResp> result = new RespVO<>();
        TRoleMenusResp tRoleMenusResp = tRoleMenusService.getById(id);
      if(null == tRoleMenusResp){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tRoleMenusResp);
        return result;
   }

   /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tRoleMenusReq
     * @return 实例对象
     */
    @GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TRoleMenusResp> getByEntity(TRoleMenusReq tRoleMenusReq) {
      RespVO<TRoleMenusResp> result = new RespVO<>();
        TRoleMenusResp tRoleMenusResp = tRoleMenusService.getByEntity(tRoleMenusReq);
      if(null == tRoleMenusResp){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tRoleMenusResp);
        return result;
   }

   /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tRoleMenusReq 实例对象
     * @return 对象列表
     */
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<List> list(TRoleMenusReq tRoleMenusReq) {
        RespVO<List> result = new RespVO<>();
      List<TRoleMenusResp> tRoleMenusRespList = tRoleMenusService.listByEntity(tRoleMenusReq);
        if(null == tRoleMenusRespList || tRoleMenusRespList.isEmpty()){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("请求成功！");
        result.setData(tRoleMenusRespList);
        return result;
    }

   /**
     * 新增实体属性不为null的记录
     *
     * @param tRoleMenusReq 实例对象
     * @return 实例对象
     */
    @PostMapping(value = "/insert",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TRoleMenusResp> insert(@RequestBody @Validated TRoleMenusReq tRoleMenusReq){
        RespVO<TRoleMenusResp> result = new RespVO<>();
      int insert = tRoleMenusService.insert(tRoleMenusReq);
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
     * @param tRoleMenusReq 实例对象
     * @return 实例对象
     */
    @PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TRoleMenusResp> update(@RequestBody @Validated TRoleMenusReq tRoleMenusReq){
        RespVO<TRoleMenusResp> result = new RespVO<>();
      int update = tRoleMenusService.update(tRoleMenusReq);
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
    public RespVO<TRoleMenusResp> deleteOne(@PathVariable Integer id){
      RespVO<TRoleMenusResp> result = new RespVO<>();
        int delete = tRoleMenusService.deleteById(id);
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
    public RespVO<TRoleMenusResp> deleteBatch(@RequestBody List<Integer> ids){
        RespVO<TRoleMenusResp> result = new RespVO<>();
      int dels = 0;
        if (ids!=null&&ids.size()>0) {
         dels = tRoleMenusService.deleteByIds(ids);
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