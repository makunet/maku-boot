package net.maku.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.maku.framework.mybatis.service.impl.BaseServiceImpl;
import net.maku.system.dao.SysUserPostDao;
import net.maku.system.entity.SysUserPostEntity;
import net.maku.system.service.SysUserPostService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户岗位关系
 *
 * @author 阿沐 babamu@126.com
 */
@Service
public class SysUserPostServiceImpl extends BaseServiceImpl<SysUserPostDao, SysUserPostEntity> implements SysUserPostService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> postIdList) {
        // 数据库岗位ID列表
        List<Long> dbPostIdList = getPostIdList(userId);

        // 需要新增的岗位ID
        Collection<Long> insertPostIdList = CollUtil.subtract(postIdList, dbPostIdList);
        if (CollUtil.isNotEmpty(insertPostIdList)){
            List<SysUserPostEntity> postList = insertPostIdList.stream().map(postId -> {
                SysUserPostEntity entity = new SysUserPostEntity();
                entity.setUserId(userId);
                entity.setPostId(postId);
                return entity;
            }).collect(Collectors.toList());

            // 批量新增
            saveBatch(postList);
        }

        // 需要删除的岗位ID
        Collection<Long> deletePostIdList = CollUtil.subtract(dbPostIdList, postIdList);
        if (CollUtil.isNotEmpty(deletePostIdList)){
            LambdaQueryWrapper<SysUserPostEntity> queryWrapper = new LambdaQueryWrapper<>();
            remove(queryWrapper.eq(SysUserPostEntity::getUserId, userId).in(SysUserPostEntity::getPostId, deletePostIdList));
        }
    }

    @Override
    public void deleteByPostIdList(List<Long> postIdList) {
        remove(new LambdaQueryWrapper<SysUserPostEntity>().in(SysUserPostEntity::getPostId, postIdList));
    }

    @Override
    public void deleteByUserIdList(List<Long> userIdList) {
        remove(new LambdaQueryWrapper<SysUserPostEntity>().in(SysUserPostEntity::getUserId, userIdList));
    }

    @Override
    public List<Long> getPostIdList(Long userId) {
        return baseMapper.getPostIdList(userId);
    }
}