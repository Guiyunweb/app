package plus.guiyun.app.framework.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.common.code.domain.Pagination;
import plus.guiyun.app.framework.web.service.CrudService;

public class CurdController<S extends CrudService<T, ID>, T, ID> {

    private final S service;

    public CurdController(S service) {
        this.service = service;
    }

    @GetMapping("/list")
    public AjaxResult<Pagination<T>> list(Pagination<T> pagination, T t) {
        Pageable pageable = PageRequest.of(pagination.getCurrentPage(), pagination.getPageSize());
        Page<T> page = service.find(t, pageable);
        pagination.setPage(page.getContent());
        pagination.setTotal(page.getTotalElements());
        return AjaxResult.success(pagination);
    }



}
