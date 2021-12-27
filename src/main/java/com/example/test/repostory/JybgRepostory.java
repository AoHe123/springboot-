package com.example.test.repostory;

import com.example.test.model.Jybg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JybgRepostory extends JpaRepository<Jybg,Long>, JpaSpecificationExecutor<Jybg> {

}
