package com.lfkj.user.service;

import com.lfkj.user.dto.UserAddDTO;
import com.lfkj.user.dto.UserUpdateDTO;
import com.lfkj.user.vo.UserVO;

public interface UserService {

    UserVO findById(Long id);

    Long add(UserAddDTO userAddDTO);

    void update(UserUpdateDTO userUpdateDTO);

    void deleteById(Long id);
}
