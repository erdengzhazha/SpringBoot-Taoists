package com.ovopark.tao.mp.mygenerator.mapper;

import com.ovopark.tao.mp.mygenerator.entity.MyUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XiuEr
 * @since 2021-02-28
 */
@Repository
public interface MyUserMapper extends BaseMapper<MyUser> {

  List<MyUser> selectUserList();
}
