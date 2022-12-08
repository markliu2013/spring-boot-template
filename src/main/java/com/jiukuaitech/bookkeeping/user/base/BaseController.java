package com.jiukuaitech.bookkeeping.user.base;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BaseController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

}
