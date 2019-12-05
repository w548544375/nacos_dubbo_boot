package com.htyl.park.employee.provider;/**
 * @description
 * @author: weiguang
 * @create: 4:32 下午 2019/12/5
 **/

import com.htyl.park.employee.api.IDictService;
import com.htyl.park.employee.domain.Dict;
import com.htyl.park.employee.mapper.DictMapper;
import org.apache.dubbo.config.annotation.Service;

/**
 * @description
 * @author: weiguang
 * @create: 4:32 下午  2019/12/5
 **/
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class DictServiceImpl implements IDictService {

    private final DictMapper dictMapper;

    public DictServiceImpl(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public Long saveDict(Dict dict) {
        dictMapper.insertSelective(dict);
        return dict.getDictId();
    }

    @Override
    public Integer deleteDict(Long dictId) {
        return dictMapper.deleteByPrimaryKey(dictId);
    }

    @Override
    public Integer updateDict(Dict dict) {
        return dictMapper.updateByPrimaryKeySelective(dict);
    }

    @Override
    public Dict selectDict(Long dictId) {
        return dictMapper.selectByPrimaryKey(dictId);
    }

}
