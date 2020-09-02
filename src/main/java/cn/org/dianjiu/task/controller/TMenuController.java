package cn.org.dianjiu.task.controller;

import cn.org.dianjiu.task.common.req.TMenuReq;
import cn.org.dianjiu.task.common.resp.TMenuResp;
import cn.org.dianjiu.task.common.vo.RespVO;
import cn.org.dianjiu.task.service.TMenuServiceI;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import java.util.List;

/**
 * (TMenu)表控制层
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:32
 */
@RestController
@RequestMapping("/tMenu")
public class TMenuController {
    
    /**
     * 服务对象
     */
    @Autowired
    private TMenuServiceI tMenuService;

    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping(value = "/get/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TMenuResp> getById(@PathVariable Integer id) {
      RespVO<TMenuResp> result = new RespVO<>();
        TMenuResp tMenuResp = tMenuService.getById(id);
      if(null == tMenuResp){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tMenuResp);
        return result;
   }

   /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tMenuReq
     * @return 实例对象
     */
    @GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TMenuResp> getByEntity(TMenuReq tMenuReq) {
      RespVO<TMenuResp> result = new RespVO<>();
        TMenuResp tMenuResp = tMenuService.getByEntity(tMenuReq);
      if(null == tMenuResp){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tMenuResp);
        return result;
   }

   /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tMenuReq 实例对象
     * @return 对象列表
     */
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<List> list(TMenuReq tMenuReq) {
        RespVO<List> result = new RespVO<>();
      List<TMenuResp> tMenuRespList = tMenuService.listByEntity(tMenuReq);
        if(null == tMenuRespList || tMenuRespList.isEmpty()){
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("请求成功！");
        result.setData(tMenuRespList);
        return result;
    }

   /**
     * 新增实体属性不为null的记录
     *
     * @param tMenuReq 实例对象
     * @return 实例对象
     */
    @PostMapping(value = "/insert",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TMenuResp> insert(@RequestBody @Validated TMenuReq tMenuReq){
        RespVO<TMenuResp> result = new RespVO<>();
      int insert = tMenuService.insert(tMenuReq);
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
     * @param tMenuReq 实例对象
     * @return 实例对象
     */
    @PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TMenuResp> update(@RequestBody @Validated TMenuReq tMenuReq){
        RespVO<TMenuResp> result = new RespVO<>();
      int update = tMenuService.update(tMenuReq);
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
    public RespVO<TMenuResp> deleteOne(@PathVariable Integer id){
      RespVO<TMenuResp> result = new RespVO<>();
        int delete = tMenuService.deleteById(id);
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
    public RespVO<TMenuResp> deleteBatch(@RequestBody List<Integer> ids){
        RespVO<TMenuResp> result = new RespVO<>();
      int dels = 0;
        if (ids!=null&&ids.size()>0) {
         dels = tMenuService.deleteByIds(ids);
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