package plus.guiyun.app.framework.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import plus.guiyun.app.common.utils.JpaUtil;

import java.util.List;

public class CurdServiceImpl<R extends JpaRepository<T, ID>, T, ID> implements CurdService<T, ID> {

    @Autowired
    public R repository;

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T updateById(T exists, ID id) {
        T role = findById(id);
        JpaUtil.copyNotNullProperties(exists, role);
        return save(exists);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id).get();
    }

    @Override
    public Iterable<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<T> find(T entity) {
        Example<T> example = Example.of(entity);
        return repository.findAll(example);
    }

    @Override
    public Page<T> find(T entity, Pageable pageable) {
        Example<T> example = Example.of(entity);
        return repository.findAll(example, pageable);
    }

    @Override
    public void deleteAllById(ID[] ids) {
        repository.deleteAllByIdInBatch(List.of(ids));
    }
}
