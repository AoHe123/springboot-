package com.example.test.specification;

import com.example.test.model.Jybg;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component
public class JybgSpecification {
    /**
     * root 就是mobile实例  root.get("name") name是属性名 不是数据库字段名
     * @return
     * */
    public Specification<Jybg> getMobileSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            String flag = "null";
            list.add(criteriaBuilder.isNull(root.get("flag").as(String.class)));
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
    }


}
