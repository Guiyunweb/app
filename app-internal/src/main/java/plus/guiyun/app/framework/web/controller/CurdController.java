package plus.guiyun.app.framework.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.common.code.domain.Pagination;
import plus.guiyun.app.common.code.domain.model.LoginUser;
import plus.guiyun.app.common.code.redis.RedisCache;
import plus.guiyun.app.common.constant.CacheConstants;
import plus.guiyun.app.common.utils.JWTUtil;
import plus.guiyun.app.framework.config.TokenConfig;
import plus.guiyun.app.framework.web.service.CurdService;
import plus.guiyun.app.framework.web.service.TokenService;

import java.util.Objects;


/**
 * 增删改查控制器
 *
 * @param <S>  服务
 * @param <T>  实体
 * @param <ID> 主键类型
 */
public class CurdController<S extends CurdService<T, ID>, T, ID> {

    @Autowired
    private S service;

    @Autowired
    RedisCache redisCache;

    @GetMapping("/list")
    public AjaxResult<Pagination<T>> list(Pagination<T> pagination, T t) {
        Pageable pageable = PageRequest.of(pagination.getCurrentPage(), pagination.getPageSize());
        Page<T> page = service.find(t, pageable);
        pagination.setPage(page.getContent());
        pagination.setTotal(page.getTotalElements());
        return AjaxResult.success(pagination);
    }

    @GetMapping("/all")
    public AjaxResult<Iterable<T>> all() {
        return AjaxResult.success(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public AjaxResult<T> getInfo(@PathVariable("id") ID id) {
        T t = service.findById(id);
        return AjaxResult.success(t);
    }

    @PostMapping
    public AjaxResult<T> add(@RequestBody T t) {
        service.save(t);
        return AjaxResult.success();
    }


    @PutMapping(value = "/{id}")
    public AjaxResult<T> edit(@PathVariable("id") ID id, @RequestBody T t) {
        service.updateById(t, id);
        return AjaxResult.success();
    }

    @DeleteMapping("/{ids}")
    public AjaxResult<T> remove(@PathVariable ID[] ids) {
        service.deleteAllById(ids);
        return AjaxResult.success();
    }


    /**
     * 获取用户
     *
     * @return
     */
    public LoginUser getUserInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        String uuid = TokenService.getLoginUUID(request);
        return redisCache.getCacheObject(uuid);
    }

}
