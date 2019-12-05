package com.htyl.park.employee.provider;/**
 * @description 角色服务实现类
 * @author: weiguang
 * @create: 3:34 下午 2019/12/4
 **/

import com.htyl.park.employee.api.IRoleService;
import com.htyl.park.employee.domain.Role;
import com.htyl.park.employee.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description 角色服务实现类
 * @author: weiguang
 * @create: 3:34 下午  2019/12/4
 **/
@Slf4j
@Service
@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Long saveRole(Role role){
        roleMapper.insertSelective(role);
        return role.getRoleId();
    }

    @Override
    public Integer deleteRole(Long roleId){
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public Integer updateRole(Role role){
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role selectRole(Long roleId){
        return roleMapper.selectByPrimaryKey(roleId);
    }


}
