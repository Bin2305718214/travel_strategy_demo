package com.travel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.travel.model.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Build_start
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
