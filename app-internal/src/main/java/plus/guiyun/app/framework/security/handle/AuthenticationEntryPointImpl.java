package plus.guiyun.app.framework.security.handle;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.common.constant.HttpStatus;
import plus.guiyun.app.common.utils.ServletUtils;

import java.io.IOException;
import java.io.Serializable;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        AjaxResult resultData = resultData = AjaxResult.showError("认证失败，请重新登录", HttpStatus.UNAUTHORIZED);
        ServletUtils.renderString(response, resultData.toString());
    }
}
