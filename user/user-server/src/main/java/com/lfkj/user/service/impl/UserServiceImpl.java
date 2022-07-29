package com.lfkj.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfkj.user.dto.UserAddDTO;
import com.lfkj.user.dto.UserUpdateDTO;
import com.lfkj.user.service.UserService;
import com.lfkj.user.vo.UserVO;
import com.lfkj.user.entity.User;
import com.lfkj.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Transactional
    @Override
    public Long add(UserAddDTO userAddDTO) {
        User user = new User();
        BeanUtils.copyProperties(userAddDTO, user);
        save(user);
        return user.getId();
    }

    @Transactional
    @Override
    public void update(UserUpdateDTO userUpdateDTO) {
        User user = getById(userUpdateDTO.getId());
        Assert.notNull(user, "用户不存在");
        user.setNickname(userUpdateDTO.getNickname());
        user.setSex(userUpdateDTO.getSex());
        updateById(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        User user = getById(id);
        Assert.notNull(user, "用户不存在");
        removeById(user);
    }

    @Override
    public UserVO findById(Long id) {
        User user = getById(id);
        Assert.notNull(user, "用户不存在");
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}
