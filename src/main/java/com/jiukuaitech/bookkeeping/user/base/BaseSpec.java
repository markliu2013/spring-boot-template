package com.jiukuaitech.bookkeeping.user.base;

import org.springframework.data.jpa.domain.Specification;

public final class BaseSpec {

    public static<T> Specification<T> isEnable(Boolean enable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("enable"), enable);
    }

    public static<T> Specification<T> isType(Integer type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
    }

}
