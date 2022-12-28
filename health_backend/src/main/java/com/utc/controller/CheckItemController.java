package com.utc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.utc.constant.MessageConstant;
import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.entity.Result;
import com.utc.pojo.CheckItem;
import com.utc.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {
    // 使用 @Reference 来从注册中心来寻找服务
    @Reference
    private CheckItemService checkItemService;

    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.add(checkItem);
        } catch(Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult getList(@RequestBody QueryPageBean queryPageBean) {
        return checkItemService.getList(queryPageBean);
    }

    @RequestMapping("/delete")
    public Result deleteById(Integer id) {
        try {
            checkItemService.deleteById(id);
        } catch(Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/edit")
    public Result updateCheckItem(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.update(checkItem);
        } catch(Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @RequestMapping("findById")
    public Result findById(Integer id) {
        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
    }

    @RequestMapping("findAll")
    public Result findAll() {
        List<CheckItem> checkItemList;
        try {
            checkItemList = checkItemService.findAll();
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemList);
    }

}
