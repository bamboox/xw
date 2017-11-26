package com.ace.web.controller;

import com.ace.entity.User;
import com.ace.exception.UsernameIsExitedException;
import com.ace.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bamboo
 */
@Api(value = "用户controller", description = "用户操作", tags = {"用户操作接口"})
@RestController
@RequestMapping("/oauth")
public class OauthController {
    @Autowired
    private UserRepository applicationUserRepository;

    /**
     * 该方法是注册用户的方法，默认放开访问控制
     *
     * @param user
     */
    @ApiOperation("注册")
    @RequestMapping(value = "/signup", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public void signUp(@RequestBody User user) {
        User user1 = applicationUserRepository.getByUsername(user.getUsername());
        if (null != user1) {
            throw new UsernameIsExitedException("用户已经存在~");
        }
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()));
        applicationUserRepository.save(user);
    }
}
