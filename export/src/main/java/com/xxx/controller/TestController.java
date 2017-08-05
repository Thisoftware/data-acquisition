package com.xxx.controller;

import com.xxx.common.consts.ReData;
import com.xxx.common.util.ReUtil;
import com.xxx.dao.CarPcGpsMapper;
import com.xxx.model.CarPcGps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Wyl on 2017-06-13.
 */
@RestController
public class TestController {

    @Autowired
    private CarPcGpsMapper carPcGpsMapper;

    @RequestMapping(value = "hello",method = RequestMethod.POST)
    public ReData getMessage(CarPcGps carPcGps){

        CarPcGps carPcGps1 =  carPcGpsMapper.selectByPrimaryKey(carPcGps.getSerialNum());

        return ReUtil.success(carPcGps1);
    }

}
