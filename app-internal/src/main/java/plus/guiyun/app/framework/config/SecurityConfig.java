package plus.guiyun.app.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import plus.guiyun.app.framework.security.handle.AuthenticationEntryPointImpl;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF禁用，因为不使用session
                .csrf().disable()
                // 基于token，所以不需要session
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 过滤请求
                .authorizeHttpRequests()
                .requestMatchers("/test").anonymous()
                .anyRequest().authenticated().and()
                .headers().frameOptions().disable();

        return http.build();
    }
}
