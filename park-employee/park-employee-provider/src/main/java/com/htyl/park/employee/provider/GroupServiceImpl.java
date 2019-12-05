package com.htyl.park.employee.provider;/**
 * @description 用户分组服务实现类
 * @author: weiguang
 * @create: 4:25 下午 2019/12/5
 **/

import com.htyl.park.employee.api.IGroupService;
import com.htyl.park.employee.domain.Group;
import com.htyl.park.employee.mapper.GroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *@description 用户分组服务实现类
 *
 *@author: weiguang
 *
 *@create: 4:25 下午  2019/12/5
 **/
@Slf4j
@Service
@org.springframework.stereotype.Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public Long saveGroup(Group group){
        groupMapper.insertSelective(group);
        return group.getGroupId();
    }

    @Override
    public Integer deleteGroup(Long groupId){
        int deleteCount = groupMapper.deleteGroupUser(groupId);
        log.info("[UAC]Delete group related user,removed {} rows.",deleteCount);
        return groupMapper.deleteByPrimaryKey(groupId);
    }

    @Override
    public Integer updateGroup(Group group){
        return groupMapper.updateByPrimaryKeySelective(group);
    }

    @Override
    public Group selectGroup(Long groupId){
        return groupMapper.selectByPrimaryKey(groupId);
    }



}
