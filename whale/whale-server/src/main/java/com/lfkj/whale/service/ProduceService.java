package com.lfkj.whale.service;

import com.lfkj.web.utils.PageVO;
import com.lfkj.whale.ProduceAddDTO;
import com.lfkj.whale.ProduceFactorDTO;
import com.lfkj.whale.ProduceUpdateAddDTO;
import com.lfkj.whale.ProduceVO;

public interface ProduceService {

    Long add(ProduceAddDTO produceAddDTO);

    void update(ProduceUpdateAddDTO produceUpdateDTO);

    void delete(Long id);

    PageVO<ProduceVO> findByCondition(ProduceFactorDTO produceFactorDTO);
}
