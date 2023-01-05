package com.utc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.utc.constant.MessageConstant;
import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.entity.Result;
import com.utc.pojo.CheckGroup;
import com.utc.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] ids) {
        try {
            checkGroupService.add(checkGroup, ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }

        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkGroupService.findPage(queryPageBean);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemsByCheckGroupId(Integer id) {
        try {
            List<Integer> checkItemIds = checkGroupService.findCheckItemsByCheckGroupId(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @DeleteMapping("/delete")
    public Result deleteById(Integer id) {
        try {
            checkGroupService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkItemIds) {
        try {
            checkGroupService.edit(checkGroup, checkItemIds);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch(Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<CheckGroup> checkGroupList = checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroupList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
}
