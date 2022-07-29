package com.lfkj.whale.controller;

import com.lfkj.web.utils.PageVO;
import com.lfkj.web.utils.RestVO;
import com.lfkj.whale.ProduceAddDTO;
import com.lfkj.whale.ProduceFactorDTO;
import com.lfkj.whale.ProduceUpdateAddDTO;
import com.lfkj.whale.ProduceVO;
import com.lfkj.whale.service.ProduceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/produce")
public class ProduceController {

    @Resource
    private ProduceService produceService;

    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCE')")
    @PostMapping
    public RestVO<Long> add(@RequestBody @Valid ProduceAddDTO produceAddDTO) {
        Long id = produceService.add(produceAddDTO);
        return RestVO.OK(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCE')")
    @PutMapping
    public RestVO<Void> update(@RequestBody @Valid ProduceUpdateAddDTO produceUpdateDTO) {
        produceService.update(produceUpdateDTO);
        return RestVO.OK();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCE')")
    @DeleteMapping("{id:\\d{19}}")
    public RestVO<Void> delete(@PathVariable("id") Long id) {
        produceService.delete(id);
        return RestVO.OK();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCE')")
    @GetMapping
    public RestVO<PageVO<ProduceVO>> findByCondition(ProduceFactorDTO produceFactorDTO) {
        PageVO<ProduceVO> pageVO = produceService.findByCondition(produceFactorDTO);
        return RestVO.OK(pageVO);
    }
}
