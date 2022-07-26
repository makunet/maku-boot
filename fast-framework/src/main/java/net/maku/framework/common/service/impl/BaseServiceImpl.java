package net.maku.framework.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.maku.framework.common.constant.Constant;
import net.maku.framework.common.interceptor.DataScope;
import net.maku.framework.common.query.Query;
import net.maku.framework.common.service.BaseService;
import net.maku.framework.security.user.SecurityUser;
import net.maku.framework.security.user.UserDetail;

import java.util.List;


/**
 * 基础服务类，所有Service都要继承
 *
 * @author 阿沐 babamu@126.com
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T>  extends ServiceImpl<M, T> implements BaseService<T> {

    /**
     * 获取分页对象
     * @param query   分页参数
     */
    protected IPage<T> getPage(Query query) {
        Page<T> page = new Page<>(query.getPage(), query.getLimit());

        // 排序
        if(StringUtils.isNotBlank(query.getOrder())){
            if(query.isAsc()) {
                return page.addOrder(OrderItem.asc(query.getOrder()));
            }else {
                return page.addOrder(OrderItem.desc(query.getOrder()));
            }
        }

        return page;
    }

    /**
     * 原生SQL 数据权限
     * @param tableAlias 表别名，多表关联时，需要填写表别名
     * @param orgIdAlias 机构ID别名，null：表示org_id
     * @return 返回数据权限
     */
    protected DataScope getDataScope(String tableAlias, String orgIdAlias)  {
        UserDetail user = SecurityUser.getUser();
        // 如果是超级管理员，则不进行数据过滤
        if(user.getSuperAdmin().equals(Constant.SUPER_ADMIN)) {
            return null;
        }

        // 如果为null，则设置成空字符串
        if(tableAlias == null){
            tableAlias = "";
        }

        // 获取表的别名
        if(StringUtils.isNotBlank(tableAlias)){
            tableAlias +=  ".";
        }

        StringBuilder sqlFilter = new StringBuilder();
        sqlFilter.append(" (");

        // 数据权限范围
        List<Long> dataScopeList = user.getDataScopeList();
        // 全部数据权限
        if (dataScopeList == null){
            return null;
        }
        // 数据过滤
        if(dataScopeList.size() > 0){
            if(StringUtils.isBlank(orgIdAlias)){
                orgIdAlias = "org_id";
            }
            sqlFilter.append(tableAlias).append(orgIdAlias);

            sqlFilter.append(" in(").append(StrUtil.join(",", dataScopeList)).append(")");

            sqlFilter.append(" or ");
        }

        // 查询本人数据
        sqlFilter.append(tableAlias).append("creator").append("=").append(user.getId());

        sqlFilter.append(")");

        return new DataScope(sqlFilter.toString());
    }

    /**
     * MyBatis-Plus 数据权限
     */
    protected void dataScopeWrapper(LambdaQueryWrapper<T> queryWrapper)  {
        DataScope dataScope = getDataScope(null, null);
        if (dataScope != null){
            queryWrapper.apply(dataScope.getSqlFilter());
        }
    }

}