package com.detabes.jpa.server.service.impl;

import com.detabes.entity.basics.vo.SerializableVO;
import com.detabes.jap.core.util.CommUtils;
import com.detabes.jap.core.util.JPAUtilExpandCriteria;
import com.detabes.jap.core.util.JPageUtil;
import com.detabes.jpa.server.dao.JpaBasicsDao;
import com.detabes.jpa.server.enums.FieldName;
import com.detabes.jpa.server.service.JService;
import com.detabes.result.page.ResourcePage;
import com.detabes.result.response.PageVO;
import com.detabes.result.response.SortVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * 预约模块公共service实现
 *
 * @author tn
 * @version 1
 * @date 2021/1/23 12:03
 */
@Slf4j
@NoRepositoryBean
public class JServiceImpl<T extends SerializableVO, D> implements JService<T> {

    private final JpaBasicsDao<T, D> commonDao;

    public JServiceImpl(JpaBasicsDao<T, D> commonDao) {
        this.commonDao = commonDao;
    }

    @Override
    public T saveByBean(T bean) {
        return commonDao.save(bean);
    }

    @Override
    public Boolean saveByBoolean(T bean) {
        return Optional.ofNullable(commonDao.save(bean)).isPresent();
    }

    @Override
    public Boolean deleteById(List<Integer> ids) {
        return commonDao.deleteByIdIn((List<D>) ids)>0;
    }

    @Override
    public Boolean deleteById(Integer id) {
        try {
            commonDao.deleteById((D) id);
            return true;
        }catch (Exception e){
            log.error("根据id删除出错",e);
        }
        return false;
    }

    @Override
    public Boolean deleteByUuid(List<String> uuid) {
        return commonDao.deleteByUuidIn(uuid)>0;
    }

    @Override
    public Boolean deleteByUuid(String uuid) {
        return commonDao.deleteByUuid(uuid)>0;
    }

    @Override
    public Boolean updateByBean(T bean) {
        try {
            return commonDao.updateEntity(bean, FieldName.ID);
        } catch (Exception e) {
            throw new RuntimeException("更新出错");
        }
    }

    @Override
    public T findById(Integer id) {
        return commonDao.findById((D) id).get();
    }

    @Override
    public T findByUuid(String uuid) {
        return commonDao.findByUuid(uuid);
    }

    @Override
    public List<T> findByBean(T t) {
        JPAUtilExpandCriteria<T> selectRegionBean = CommUtils.getSelectBean(t);
        return commonDao.findAll(selectRegionBean);
    }

    @Override
    public <R> ResourcePage<List<R>> findByBean(T t, PageVO pageVO, SortVO sortVO, Class<R> clazz) {
        JPAUtilExpandCriteria<T> selectRegionBean = CommUtils.getSelectBean(t);
        Pageable pageable = JPageUtil.getPageable(pageVO, sortVO);
        Page<T> pages = commonDao.findAll(selectRegionBean, pageable);
        return JPageUtil.to(pages, clazz);
    }
}