package com.htyl.park.employee.api;

import com.htyl.park.employee.domain.Dict;

/**
 * @description 字典类
 * @author: weiguang
 * @create: 4:31 下午 2019/12/5
 **/
public interface IDictService {
    Long saveDict(Dict dict);

    Integer deleteDict(Long dictId);

    Integer updateDict(Dict dict);

    Dict selectDict(Long dictId);
}
