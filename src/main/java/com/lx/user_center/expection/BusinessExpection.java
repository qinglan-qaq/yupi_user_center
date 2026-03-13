package com.lx.user_center.expection;

import com.lx.user_center.common.ErrorCode;

public class BusinessExpection extends RuntimeException {

    private final int code;
    private final String description;

    public BusinessExpection(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessExpection(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessExpection(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
