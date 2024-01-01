package plus.guiyun.app.common.code.domain;

import java.util.List;

public class Pagination<T> {

    public Pagination(Integer current, Integer size, Long total, List<T> page) {
        this.total = total;
        this.pageSize = size;
        this.currentPage = current;
        this.page = page;
    }

    protected Long total;

    protected Integer pageSize;

    protected Integer currentPage;

    protected List<T> page;
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getPage() {
        return page;
    }

    public void setPage(List<T> page) {
        this.page = page;
    }
}
