package plus.guiyun.app.common.code.domain;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import plus.guiyun.app.common.constant.HttpStatus;

import java.util.Objects;

@Getter
@Setter
public class AjaxResult<T> {

    /**
     * 请求是否成功
     */
    private Boolean success;

    /**
     * 返回code
     */
    private Integer code;

    /**
     * 提示
     */
    private String message;
    private T data;

    /**
     * 请求ID
     */
    private String traceId;

    /**
     * 是否展示
     */
    private Boolean showType;

    /**
     * 是否加密 默认不加密
     */
    private Boolean encrypt = false;


    public AjaxResult(Boolean success, Integer code, String message, T data, String traceId, Boolean showType, Boolean encrypt) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.traceId = traceId;
        this.showType = showType;
        this.encrypt = encrypt;
    }

    public static <T> AjaxResult<T> success(T data) {
        return new AjaxResult<T>(true, HttpStatus.SUCCESS, "请求成功", data, traceID(), false, false);
    }

    public static <T> AjaxResult<T> showSuccess(T data) {
        return new AjaxResult<T>(true, HttpStatus.SUCCESS, "请求成功", data, traceID(), true, false);
    }

    public static <T> AjaxResult<T> success(T data, String message) {
        return new AjaxResult<T>(true, HttpStatus.SUCCESS, message, data, traceID(), false, false);
    }

    public static <T> AjaxResult<T> showSuccess(T data, String message) {
        return new AjaxResult<T>(true, HttpStatus.SUCCESS, message, data, traceID(), true, false);
    }

    public static <T> AjaxResult<T> error() {
        return new AjaxResult<T>(false, HttpStatus.ERROR, "请求失败", null, traceID(), false, false);
    }

    public static <T> AjaxResult<T> showError() {
        return new AjaxResult<T>(false, HttpStatus.ERROR, "请求失败", null, traceID(), true, false);
    }

    public static <T> AjaxResult<T> error(String message) {
        return new AjaxResult<T>(false, HttpStatus.ERROR, message, null, traceID(), false, false);
    }

    public static <T> AjaxResult<T> showError(String message) {
        return new AjaxResult<T>(false, HttpStatus.ERROR, message, null, traceID(), true, false);
    }

    public static <T> AjaxResult<T> error(String message, Integer code) {
        return new AjaxResult<T>(false, code, message, null, traceID(), false, false);
    }

    public static <T> AjaxResult<T> errorShow(String message, Integer code) {
        return new AjaxResult<T>(false, code, message, null, traceID(), true, false);
    }


    public static String traceID() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        String traceId = request.getHeader("traceId");
        return ObjectUtils.isEmpty(traceId) ? "-1" : traceId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
