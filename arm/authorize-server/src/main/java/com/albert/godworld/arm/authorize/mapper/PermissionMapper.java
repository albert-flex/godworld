package com.albert.godworld.arm.authorize.mapper;

import com.albert.godworld.arm.authorize.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select p.id as id,p.name as name,p.description as description\n"+
            "from permission p\n"+
            "join group_permission gp on gp.permission_id=p.id\n"+
            "join u_group ug on ug.id=gp.group_id\n"+
            "join user_group sg on sg.group_id=ug.id\n"+
            "join user u on u.id=sg.user_id\n"+
            "where u.id=#{userId}")
    List<Permission> allPerOfUser(Long userId);
}

