package com.htyl.park.employee.api;

import com.htyl.park.employee.domain.Role;

/**
 * @description 角色服务
 * @author: weiguang
 * @create: 3:36 下午 2019/12/4
 **/
public interface IRoleService {
    Long saveRole(Role role);

    Integer deleteRole(Long roleId);

    Integer updateRole(Role role);

    Role selectRole(Long roleId);
}
