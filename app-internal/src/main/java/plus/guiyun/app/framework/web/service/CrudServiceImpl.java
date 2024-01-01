package plus.guiyun.app.framework.web.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import plus.guiyun.app.common.utils.JpaUtil;

public class CrudServiceImpl<R extends JpaRepository<T, ID>, T, ID> implements CrudService<T, ID> {

    private final R repository;

    public CrudServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T updateById(T exists, ID id) {
        T role = findById(id);
        JpaUtil.copyNotNullProperties(role, exists);
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
        return repository.findAll(example,pageable);
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        repository.deleteAllById(ids);
    }
}
