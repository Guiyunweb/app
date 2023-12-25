package plus.guiyun.app.common.code.domain;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by myy on 2021/11/19.
 */
@Slf4j
public class SuccessResult<T> extends Result {

    public SuccessResult() {
        super(Boolean.TRUE, 200, "请求成功", null, traceID());
    }

    public SuccessResult(T data) {
        super(Boolean.TRUE, 200, "请求成功", data, traceID());
    }

    public SuccessResult(String message, T data) {
        super(Boolean.TRUE, 200, message, data, traceID());
    }

}
