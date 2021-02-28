package com.ovopark.tao.mp.mygenerator.service.impl;

import com.ovopark.tao.mp.mygenerator.entity.MyUser;
import com.ovopark.tao.mp.mygenerator.mapper.MyUserMapper;
import com.ovopark.tao.mp.mygenerator.service.IMyUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XiuEr
 * @since 2021-02-28
 */
@Service
public class MyUserServiceImpl extends ServiceImpl<MyUserMapper, MyUser> implements IMyUserService {

}
