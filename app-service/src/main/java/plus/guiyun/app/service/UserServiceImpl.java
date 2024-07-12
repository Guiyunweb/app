package plus.guiyun.app.service;

import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import plus.guiyun.app.api.MenuService;
import plus.guiyun.app.api.UserService;
import plus.guiyun.app.api.vo.RouteVO;
import plus.guiyun.app.api.vo.UserInfoVo;
import plus.guiyun.app.common.code.domain.AjaxResult;
import plus.guiyun.app.common.code.domain.entity.BaseUser;
import plus.guiyun.app.common.code.domain.model.LoginUser;
import plus.guiyun.app.common.utils.SecurityUtils;
import plus.guiyun.app.domain.UserDo;
import plus.guiyun.app.framework.web.service.CurdServiceImpl;
import plus.guiyun.app.framework.web.service.TokenService;
import plus.guiyun.app.repository.UserRepository;

@Service
public class UserServiceImpl extends CurdServiceImpl<UserRepository, UserDo, Long> implements UserService {

    @Resource
    UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    MenuService menuService;


    @Override
    public String getUserName(Long id) {
        return repository.getReferenceById(id).getUsername();
    }

    @Override
    public AjaxResult<UserInfoVo> login(String account, String password){
        UserDo userDo = repository.findByAccount(account);
        if (ObjectUtils.isEmpty(userDo)) {
            return AjaxResult.showError("对不起,登录用户 " + account + " 不存在",500);
        }

        System.out.println(SecurityUtils.encryptPassword(password));

        if (!SecurityUtils.matchesPassword(password, userDo.getPassword())) {
            return AjaxResult.showError("对不起,密码错误",500);
        }
        userDo.setPassword(null);

        LoginUser loginUser = new LoginUser();
        BaseUser user = new BaseUser();
        BeanUtils.copyProperties(userDo, user);

        loginUser.setUser(user);
        loginUser.setId(userDo.getId());
        loginUser.setToken(tokenService.createToken(loginUser));

        RouteVO route = menuService.updateUserMenu(userDo.getId());
        return AjaxResult.success(new UserInfoVo(loginUser,route),"登录成功");
    }
}
