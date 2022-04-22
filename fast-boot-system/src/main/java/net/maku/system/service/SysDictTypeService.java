package net.maku.system.service;

import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.service.BaseService;
import net.maku.system.entity.SysDictTypeEntity;
import net.maku.system.vo.dict.SysDictVO;
import net.maku.system.vo.dict.type.SysDictTypePostVO;
import net.maku.system.vo.dict.type.SysDictTypePutVO;
import net.maku.system.vo.dict.type.SysDictTypeQuery;
import net.maku.system.vo.dict.type.SysDictTypeVO;

import java.util.List;

/**
 * 数据字典
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysDictTypeService extends BaseService<SysDictTypeEntity> {

    PageResult<SysDictTypeVO> page(SysDictTypeQuery query);

    void save(SysDictTypePostVO vo);

    void update(SysDictTypePutVO vo);

    void delete(List<Long> idList);

    /**
     * 获取全部字典列表
     */
    List<SysDictVO> getDictList();

}