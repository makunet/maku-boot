package net.maku.system.service;

import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.service.BaseService;
import net.maku.system.entity.SysDictDataEntity;
import net.maku.system.vo.dict.data.SysDictDataPostVO;
import net.maku.system.vo.dict.data.SysDictDataPutVO;
import net.maku.system.vo.dict.data.SysDictDataQuery;
import net.maku.system.vo.dict.data.SysDictDataVO;

import java.util.List;

/**
 * 数据字典
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    PageResult<SysDictDataVO> page(SysDictDataQuery query);

    void save(SysDictDataPostVO vo);

    void update(SysDictDataPutVO vo);

    void delete(List<Long> idList);

}