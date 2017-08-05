package com.xxx.dao;

import com.xxx.model.CarPcGps;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarPcGpsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarPcGps record);

    int insertSelective(CarPcGps record);

    CarPcGps selectByPrimaryKey(String serialNum);

    int updateByPrimaryKeySelective(CarPcGps record);

    int updateByPrimaryKey(CarPcGps record);
}