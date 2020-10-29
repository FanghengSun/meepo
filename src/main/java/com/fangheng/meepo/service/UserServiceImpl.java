package com.fangheng.meepo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fangheng.meepo.entity.User;
import com.fangheng.meepo.mapper.UserMapper;
import org.springframework.stereotype.Service;


/**
 * @author Fangheng Sun on 2020/10/27
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IService<User> {
}
