package com.example.test.service;

import com.example.test.model.Jybg;
import com.example.test.repostory.JybgRepostory;
import com.example.test.specification.JybgSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class JybgServicer {
    @Autowired
    private JybgRepostory JybgRepostory;

    @Autowired
    private JybgSpecification JybgSpecification;


    public Page<Jybg> getAllMobile() {
        // 根据价格降序
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        Sort sort = Sort.by(order);
        PageRequest of = PageRequest.of(0, 1, sort);
        return JybgRepostory.findAll(JybgSpecification.getMobileSpecification(), of);
    }

}
