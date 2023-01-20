package net.maku.system.service;

import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.BaseService;
import net.maku.system.entity.SysAttachmentEntity;
import net.maku.system.query.SysAttachmentQuery;
import net.maku.system.vo.SysAttachmentVO;

import java.util.List;

/**
 * 附件管理
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysAttachmentService extends BaseService<SysAttachmentEntity> {

    PageResult<SysAttachmentVO> page(SysAttachmentQuery query);

    void save(SysAttachmentVO vo);

    void update(SysAttachmentVO vo);

    void delete(List<Long> idList);
}