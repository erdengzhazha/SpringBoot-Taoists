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
public class OpenEnterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 企业全称
     */
    private String enterpriseName;

    /**
     * 组织机构代码
     */
    private String organizeCode;

    /**
     * 工商注册账户
     */
    private String registerAccount;

    /**
     * 法定人姓名
     */
    private String legalPerson;

    /**
     * 一般经营范围
     */
    private String businessScope1;

    /**
     * 许可经营范围
     */
    private String businessScope2;

    /**
     * 企业规模
     */
    private String enterpriseScale;

    /**
     * 开户名称
     */
    private String accountName;

    /**
     * 开户银行
     */
    private String accountBank;

    /**
     * 银行账户
     */
    private String account;

    /**
     * 手机号
     */
    private String developerCellphone;

    /**
     * 开发者姓名
     */
    private String developerName;

    /**
     * 组织机构代码证
     */
    private String organizeCodeCard;

    /**
     * 工商营业执照
     */
    private String enterpriseCard;

    /**
     * 申请公函
     */
    private String applayCard;

    /**
     * 其他证明材料
     */
    private String elseCard;

    /**
     * 所属行业
     */
    private String firstIndustry;

    /**
     * 子行业
     */
    private String secondIndustry;

    /**
     * 1:待审核；2：审核不通过;3：审核通过；
     */
    private String status;

    /**
     * 默认登陆者为开发者
     */
    private String adminUsercode;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 审核时间
     */
    private LocalDateTime examineTime;


}
