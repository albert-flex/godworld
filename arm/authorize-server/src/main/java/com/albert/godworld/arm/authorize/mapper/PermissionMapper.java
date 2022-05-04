package com.albert.godworld.arm.authorize.mapper;

import com.albert.godworld.arm.authorize.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select p.id as id,p.name as name,p.description\n" +
            "from user u\n" +
            "join user_group ug on ug.user_id=u.id\n" +
            "join u_group g on g.id=ug.group_id\n" +
            "join group_permission gp on gp.group_id=g.id\n" +
            "join permission p on p.id=gp.permission_id\n" +
            "where u.id=#{userId} group by p.id,p.name,p.description")
    List<Permission> allPerOfUser(Long userId);
}

