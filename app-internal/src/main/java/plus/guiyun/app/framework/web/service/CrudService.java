package plus.guiyun.app.framework.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, ID> {

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    T save(T entity);


    T updateById(T entity, ID id);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    T findById(ID id);


    /**
     * 查询全部
     *
     * @return
     */
    Iterable<T> findAll();


    Iterable<T> find(T entity);

    Page<T> find(T entity, Pageable pageable);

    /**
     * 删除
     *
     * @param ids
     */
    void deleteAllById(Iterable<? extends ID> ids);

}
