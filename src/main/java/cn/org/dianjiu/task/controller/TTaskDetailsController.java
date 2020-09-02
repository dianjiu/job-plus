package cn.org.dianjiu.task.controller;

import cn.org.dianjiu.task.common.req.PageReq;
import cn.org.dianjiu.task.common.req.TTaskDetailsReq;
import cn.org.dianjiu.task.common.resp.PageResp;
import cn.org.dianjiu.task.common.resp.TTaskDetailsResp;
import cn.org.dianjiu.task.common.util.ObjectUtils;
import cn.org.dianjiu.task.common.vo.RespVO;
import cn.org.dianjiu.task.service.TTaskDetailsServiceI;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 定时任务信息表(TTaskDetails)表控制层
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@RestController
@RequestMapping("/tTaskDetails")
public class TTaskDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(TTaskDetailsController.class);
    /**
     * 服务对象
     */
    @Autowired
    private TTaskDetailsServiceI tTaskDetailsService;

    /**
     * 立即运行一次定时任务
     *
     * @return
     */
    @RequestMapping(value = "/runtask", method = RequestMethod.POST)
    public RespVO<TTaskDetailsResp> runtask(@RequestParam(value = "id", required = true) Integer id) {
        RespVO<TTaskDetailsResp> result = new RespVO<>();
        int run = tTaskDetailsService.runtask(id);
        if (run != 1) {
            result.setCode("400");
            result.setMsg("立即执行定时任务失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("立即执行定时任务成功！");
        return result;
    }

    /**
     * 启动 或者 暂定定时任务
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/optionTask", method = RequestMethod.GET)
    public RespVO<TTaskDetailsResp> optionTask(Integer id) {
        RespVO<TTaskDetailsResp> result = new RespVO<>();
        // TODO 对异常做统一处理
        int option = tTaskDetailsService.optionTask(id);
        if (option != 1) {
            result.setCode("400");
            result.setMsg("更新数据失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("更新数据成功！");
        return result;
    }

    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public RespVO<TTaskDetailsResp> getById(@PathVariable Integer id) {
        RespVO<TTaskDetailsResp> result = new RespVO<>();
        TTaskDetailsResp tTaskDetailsResp = tTaskDetailsService.getById(id);
        if (null == tTaskDetailsResp) {
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tTaskDetailsResp);
        return result;
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tTaskDetailsReq
     * @return 实例对象
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public RespVO<TTaskDetailsResp> getByEntity(@RequestBody TTaskDetailsReq tTaskDetailsReq) {
        RespVO<TTaskDetailsResp> result = new RespVO<>();
        TTaskDetailsResp tTaskDetailsResp = tTaskDetailsService.getByEntity(tTaskDetailsReq);
        if (null == tTaskDetailsResp) {
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tTaskDetailsResp);
        return result;
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tTaskDetailsReq 实例对象
     * @return 对象列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public RespVO<List> list(@RequestBody TTaskDetailsReq tTaskDetailsReq) {
        RespVO<List> result = new RespVO<>();
        List<TTaskDetailsResp> tTaskDetailsRespList = tTaskDetailsService.listByEntity(tTaskDetailsReq);
        if (null == tTaskDetailsRespList || tTaskDetailsRespList.isEmpty()) {
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("请求成功！");
        result.setData(tTaskDetailsRespList);
        return result;
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param  pageReq 实例对象
     * @return 对象列表
     */
    @ApiOperation(value="分页获取任务列表接口",notes="当前页和页大小必传")
    @RequestMapping(value = "/listByPage",method = RequestMethod.POST)
    public RespVO<PageResp> listByPage(@RequestBody PageReq<TTaskDetailsReq> pageReq){
        PageResp<List<TTaskDetailsResp>> pageVO = new PageResp<>();
        RespVO<PageResp> result = new RespVO<>();
        PageInfo<TTaskDetailsResp> pages = tTaskDetailsService.listByPage(pageReq);
        if (ObjectUtils.checkObjAllFieldsIsNull(pages)) {
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        pageVO.setTotal(pages.getTotal());
        pageVO.setPages(pages.getPages());
        pageVO.setPageNum(pages.getPageNum());
        pageVO.setPageSize(pages.getPageSize());
        pageVO.setData(pages.getList());
        result.setCode("200");
        result.setMsg("请求成功！");
        result.setData(pageVO);
        return result;
    }

    /**
     * 新增实体属性不为null的记录
     *
     * @param tTaskDetailsReq 实例对象
     * @return 实例对象
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespVO<TTaskDetailsResp> insert(@RequestBody @Validated TTaskDetailsReq tTaskDetailsReq) {
        RespVO<TTaskDetailsResp> result = new RespVO<>();
        int insert = tTaskDetailsService.insert(tTaskDetailsReq);
        if (insert != 1) {
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
     * @param tTaskDetailsReq 实例对象
     * @return 实例对象
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespVO<TTaskDetailsResp> update(@RequestBody @Validated TTaskDetailsReq tTaskDetailsReq) {
        RespVO<TTaskDetailsResp> result = new RespVO<>();
        int update = tTaskDetailsService.update(tTaskDetailsReq);
        if (update != 1) {
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
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public RespVO<TTaskDetailsResp> deleteOne(@PathVariable Integer id) {
        RespVO<TTaskDetailsResp> result = new RespVO<>();
        int delete = tTaskDetailsService.deleteById(id);
        if (delete != 1) {
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
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RespVO<TTaskDetailsResp> deleteBatch(@RequestBody List<Integer> ids) {
        RespVO<TTaskDetailsResp> result = new RespVO<>();
        int dels = 0;
        if (ids != null && ids.size() > 0) {
            dels = tTaskDetailsService.deleteByIds(ids);
        }
        if (dels <= 0) {
            result.setCode("400");
            result.setMsg("批量删除数据失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("批量删除数据成功！");
        return result;
    }

}