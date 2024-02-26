package com.travel.controller.cityInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.travel.model.city.Hotel;
import com.travel.result.Result;
import com.travel.service.cityInfo.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Build_start
 * @create 2022-12-21 13:17
 */

@Api(tags = "酒店管理")
@RestController
@RequestMapping("/admin/city/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @ApiOperation(value = "分页查询酒店信息")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageHotel(@PathVariable Long current,
                                     @PathVariable Long limit) {

        Page<Hotel> hotelPage = hotelService.page(new Page<Hotel>(current, limit));

        return Result.ok(hotelPage);
    }

    @ApiOperation(value = "获取指定id的酒店信息")
    @PostMapping("get/{id}")
    public Result getHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.getById(id);

        return Result.ok(hotel);
    }

    @ApiOperation(value = "添加酒店信息")
    @PostMapping("save")
    public Result saveHotel(@RequestBody Hotel hotel) {

        hotel.setCreateTime(new Date());
        hotel.setUpdateTime(new Date());

        boolean save = hotelService.save(hotel);

        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "修改酒店信息")
    @PostMapping("update")
    public Result updateHotel(@RequestBody Hotel hotel) {
        hotel.setUpdateTime(new Date());

        boolean flag = hotelService.updateById(hotel);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "逻辑删除指定id的酒店信息")
    @DeleteMapping("remove/{id}")
    public Result removeHotel(@PathVariable Long id) {
        boolean flag = hotelService.removeById(id);

        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
