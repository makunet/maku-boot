package net.maku.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.query.Query;
import net.maku.framework.common.service.impl.BaseServiceImpl;
import net.maku.system.convert.SysOauthClientConvert;
import net.maku.system.dao.SysOauthClientDao;
import net.maku.system.entity.SysOauthClientEntity;
import net.maku.system.service.SysOauthClientService;
import net.maku.system.vo.SysOauthClientVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户端管理
 *
 * @author 阿沐 babamu@126.com
 */
@Service
public class SysOauthClientServiceImpl extends BaseServiceImpl<SysOauthClientDao, SysOauthClientEntity>
        implements SysOauthClientService {

    @Override
    public PageResult<SysOauthClientVO> page(Query query) {
        IPage<SysOauthClientEntity> page = baseMapper.selectPage(getPage(query), Wrappers.emptyWrapper());

        return new PageResult<>(SysOauthClientConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    @Override
    public void save(SysOauthClientVO vo) {
        SysOauthClientEntity entity = SysOauthClientConvert.INSTANCE.convert(vo);
        //entity.setAuthorizedGrantTypes(JsonUtils.toJsonString(vo.getAuthorizedGrantTypes()));

        baseMapper.insert(entity);
    }

    @Override
    public void update(SysOauthClientVO vo) {
        SysOauthClientEntity entity = SysOauthClientConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }
}
