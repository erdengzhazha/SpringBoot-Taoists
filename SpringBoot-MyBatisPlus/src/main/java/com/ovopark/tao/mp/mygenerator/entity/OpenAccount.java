package com.ovopark.tao.mp.mygenerator.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author XiuEr
 * @since 2021-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OpenAccount implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户帐号(默认是手机号)
     */
    private String usercode;

    /**
     * 手机号
     */
    private String cellphone;

    private String password;

    private LocalDateTime createTime;

    /**
     * 是否禁用(1:是，2：否)
     */
    private String forbidden;

    /**
     * 禁用时间
     */
    private LocalDateTime forbiddenTime;

    /**
     * 密码修改时间
     */
    private LocalDateTime pwdUpdateTime;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    private String lastLoginIp;

    /**
     * 1:万店掌超级管理员，2：万店掌客服，3：三方超级管理员，4：三方成员
     */
    private String userType;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 企业id
     */
    private Long enterpriseId;


}
