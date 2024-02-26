package com.travel.controller.cityInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.city.Shopping;
import com.travel.result.Result;
import com.travel.service.cityInfo.ShoppingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Build_start
 * @create 2022-12-21 13:17
 */

@Api(tags = "购物点管理")
@RestController
@RequestMapping("/admin/city/shopping")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @ApiOperation(value = "分页查询购物点信息")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageShopping(@PathVariable Long current,
                                     @PathVariable Long limit) {

        Page<Shopping> shoppingPage = shoppingService.page(new Page<Shopping>(current, limit));

        return Result.ok(shoppingPage);
    }

    @ApiOperation(value = "获取指定id的购物点信息")
    @PostMapping("get/{id}")
    public Result getShopping(@PathVariable Long id) {
        Shopping shopping = shoppingService.getById(id);

        return Result.ok(shopping);
    }

    @ApiOperation(value = "添加购物点信息")
    @PostMapping("save")
    public Result saveShopping(@RequestBody Shopping shopping) {

        shopping.setCreateTime(new Date());
        shopping.setUpdateTime(new Date());

        boolean save = shoppingService.save(shopping);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改购物点信息")
    @PostMapping("update")
    public Result updateShopping(@RequestBody Shopping shopping) {
        shopping.setUpdateTime(new Date());

        boolean flag = shoppingService.updateById(shopping);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "逻辑删除指定id的购物点信息")
    @DeleteMapping("remove/{id}")
    public Result removeShopping(@PathVariable Long id) {
        boolean flag = shoppingService.removeById(id);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
