package com.jiukuaitech.bookkeeping.user.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> extends SuccessResponse<List<T>> {

    private int current;
    private int pageSize;
    private long total;

    public PageResponse(Page<T> page) {
        super(page.getContent());
        this.setTotal(page.getTotalElements());
        this.setCurrent(page.getNumber());
        this.setPageSize(page.getSize());
    }

}