package com.lfkj.whale.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfkj.web.utils.PageVO;
import com.lfkj.whale.ProduceAddDTO;
import com.lfkj.whale.ProduceFactorDTO;
import com.lfkj.whale.ProduceUpdateAddDTO;
import com.lfkj.whale.ProduceVO;
import com.lfkj.whale.entity.Produce;
import com.lfkj.whale.mapper.ProduceMapper;
import com.lfkj.whale.service.ProduceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class ProduceServiceImpl extends ServiceImpl<ProduceMapper, Produce> implements ProduceService {

    @Transactional
    @Override
    public Long add(ProduceAddDTO produceAddDTO) {
        Produce produce = new Produce();
        BeanUtils.copyProperties(produceAddDTO, produce);
        save(produce);
        return produce.getId();
    }

    @Transactional
    @Override
    public void update(ProduceUpdateAddDTO produceUpdateDTO) {
        Produce produce = getById(produceUpdateDTO.getId());
        Assert.notNull(produce, "产量不存在");
        BeanUtils.copyProperties(produceUpdateDTO, produce);
        updateById(produce);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Produce produce = getById(id);
        Assert.notNull(produce, "产量不存在");
        removeById(produce);
    }

    @Override
    public PageVO<ProduceVO> findByCondition(ProduceFactorDTO produceFactorDTO) {
        LambdaQueryChainWrapper<Produce> wrapper = lambdaQuery();
        Optional.ofNullable(produceFactorDTO.getRecordTime()).ifPresent(e -> wrapper.eq(Produce::getRecordTime, e));
        Page<Produce> page = wrapper.orderByDesc(Produce::getRecordTime)
                .page(Page.of(produceFactorDTO.getCurrent(), produceFactorDTO.getSize()));
        return PageVO.build(page, ProduceVO.class);
    }
}
