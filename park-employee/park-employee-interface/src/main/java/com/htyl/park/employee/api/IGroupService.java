package com.htyl.park.employee.api;

import com.htyl.park.employee.domain.Group;

/**
 * @description 分组服务
 * @author: weiguang
 * @create: 4:30 下午 2019/12/5
 **/
public interface IGroupService {
    Long saveGroup(Group group);

    Integer deleteGroup(Long groupId);

    Integer updateGroup(Group group);

    Group selectGroup(Long groupId);
}
