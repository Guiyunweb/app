package plus.guiyun.app.common.code.domain;

/**
 * Created by myy on 2021/9/6.
 */
public class ErrorResultData extends Result {

    public ErrorResultData() {
        super(Boolean.FALSE, 500, "请求失败", null, traceID());
    }

    public ErrorResultData(Integer code) {
        super(Boolean.FALSE, code, "请求失败", null, traceID());
    }

    public ErrorResultData(Integer code, String message) {
        super(Boolean.FALSE, code, message, (Object) null, traceID());
    }
}
