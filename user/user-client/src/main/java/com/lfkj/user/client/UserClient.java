package com.lfkj.user.client;

import com.lfkj.user.dto.UserAddDTO;
import com.lfkj.user.dto.UserUpdateDTO;
import com.lfkj.user.vo.UserVO;
import com.lfkj.web.utils.RestVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user", path = "/user", fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    @PostMapping
    RestVO<Long> register(@RequestBody UserAddDTO userAddDTO);

    @PutMapping
    RestVO<Void> update(@RequestBody UserUpdateDTO userUpdateDTO);

    @DeleteMapping("/{id}")
    RestVO<Void> delete(@PathVariable("id") Long id);

    @GetMapping("/{id}")
    RestVO<UserVO> findById(@PathVariable("id") Long id);

    @Component
    class UserClientFallback implements UserClient {

        @Override
        public RestVO<Long> register(UserAddDTO userAddDTO) {
            return null;
        }

        @Override
        public RestVO<Void> update(UserUpdateDTO userUpdateDTO) {
            return null;
        }

        @Override
        public RestVO<Void> delete(Long id) {
            return null;
        }

        @Override
        public RestVO<UserVO> findById(Long id) {
            UserVO userVO = new UserVO();
            userVO.setNickname("关羽");
            return RestVO.OK(userVO);
        }
    }
}
