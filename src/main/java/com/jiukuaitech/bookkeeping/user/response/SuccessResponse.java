package com.jiukuaitech.bookkeeping.user.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> extends BaseResponse {

    private T data;

    public SuccessResponse() {
        super(true);
    }

    public SuccessResponse(T data) {
        super(true);
        this.data = data;
    }

}
