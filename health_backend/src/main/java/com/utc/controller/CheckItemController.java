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
            return new Result(true, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult getList(@RequestBody QueryPageBean queryPageBean) {
        return checkItemService.getList(queryPageBean);
    }
}
