package com.jiukuaitech.bookkeeping.user.response;

import com.jiukuaitech.bookkeeping.user.utils.Constant;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse extends BaseResponse {

    private Integer errorCode;
    private String errorMessage;
    private int showType = Constant.ERROR_SHOW_TYPE_ERROR_MESSAGE;

    public ErrorResponse(Integer errorCode, String errorMessage) {
        super(false);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
