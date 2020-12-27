package com.ovopark.tao.mp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovopark.tao.mp.common.api.CommonPage;
import com.ovopark.tao.mp.common.api.CommonResult;
import com.ovopark.tao.mp.entity.po.CMain;
import com.ovopark.tao.mp.entity.po.CMainExample;
import com.ovopark.tao.mp.mapper.CMainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CPController {

    @Autowired
    CMainMapper cMainMapper;

    @RequestMapping("getCPList")
    public List<CMain> getCPList(){
        List<CMain> cMains = cMainMapper.selectByExample(null);
        System.out.println(cMains.size());
        return cMains;
    }

    @RequestMapping("getCPPage")
    public CommonResult<CommonPage<CMain>> getCPPage(
            @RequestParam(defaultValue = "10") Integer pageSize
            , @RequestParam(defaultValue = "1") Integer pageNum){
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        //写查询语句
        QueryWrapper<CMain> cMainQueryWrapper = new QueryWrapper<CMain>();
        cMainQueryWrapper.
                orderBy(true,true,"start1");
        // 查询数据
        List<CMain> cMains = cMainMapper.selectList(cMainQueryWrapper);
        return  CommonResult.success(CommonPage.restPage(cMains));
    }
}
