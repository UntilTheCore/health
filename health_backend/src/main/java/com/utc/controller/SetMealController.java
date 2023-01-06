package com.utc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.utc.constant.MessageConstant;
import com.utc.entity.PageResult;
import com.utc.entity.QueryPageBean;
import com.utc.entity.Result;
import com.utc.pojo.Setmeal;
import com.utc.service.SetMealService;
import com.utc.utils.QiNiuUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetMealController {

    @Reference
    SetMealService setMealService;

    @RequestMapping("/upload")
    public Result uploadFile(@RequestParam("imgFile") MultipartFile imgFile) {
        String originalFilename = imgFile.getOriginalFilename();
        assert originalFilename != null;
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index - 1);
        String fileName = UUID.randomUUID().toString() + suffix;

        try {
            byte[] fileBytes = imgFile.getBytes();
            QiNiuUtils.upload2QiNiu(fileBytes, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }

        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);

    }

    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, @RequestParam("checkGroupIds") Integer[] checkGroupIds) {
        try {
            setMealService.add(setmeal, checkGroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }

        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @GetMapping("/findPage")
    public PageResult findPage(QueryPageBean queryPageBean) {
        return setMealService.findPage(queryPageBean);
    }

    @DeleteMapping("/delete")
    public Result deleteById(Integer id) {
        try {
            setMealService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除套餐失败");
        }

        return new Result(true, "删除套餐成功");
    }
}
