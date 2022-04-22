package net.maku.system.service;

import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.service.BaseService;
import net.maku.system.entity.SysPostEntity;
import net.maku.system.vo.post.SysPostPostVO;
import net.maku.system.vo.post.SysPostPutVO;
import net.maku.system.vo.post.SysPostQuery;
import net.maku.system.vo.post.SysPostVO;

import java.util.List;

/**
 * 岗位管理
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysPostService extends BaseService<SysPostEntity> {

    PageResult<SysPostVO> page(SysPostQuery query);

    List<SysPostVO> getList();

    void save(SysPostPostVO vo);

    void update(SysPostPutVO vo);

    void delete(List<Long> idList);
}