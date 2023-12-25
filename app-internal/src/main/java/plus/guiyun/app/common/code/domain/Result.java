package plus.guiyun.app.common.code.domain;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * Created by myy on 2021/11/19.
 */
@Data
public class Result<T> {

    private Boolean success;
    private Integer code;
    private String message;
    private T data;
    private String traceId;
    private Boolean encrypt = false;


    public Result() {
    }

    public Result(Boolean success, Integer code, String message, T data, String traceId) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.traceId = traceId;
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
