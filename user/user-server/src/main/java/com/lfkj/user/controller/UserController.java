package com.lfkj.user.controller;

import com.lfkj.user.dto.UserAddDTO;
import com.lfkj.user.dto.UserUpdateDTO;
import com.lfkj.user.service.UserService;
import com.lfkj.user.vo.UserVO;
import com.lfkj.web.utils.RestVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PostMapping
    public RestVO<Long> register(@RequestBody @Valid UserAddDTO userAddDTO) {
        Long id = userService.add(userAddDTO);
        return RestVO.OK(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PutMapping
    public RestVO<Void> update(@RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        userService.update(userUpdateDTO);
        return RestVO.OK();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @DeleteMapping("/{id:\\d{19}}")
    public RestVO<Void> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return RestVO.OK();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id:\\d{19}}")
    public RestVO<UserVO> findById(@PathVariable("id") Long id) {
        UserVO userVO = userService.findById(id);
        return RestVO.OK(userVO);
    }
}
