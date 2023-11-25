package com.rup.apiPayload.exception.handler;

import com.rup.apiPayload.code.BaseCode;
import com.rup.apiPayload.exception.GeneralException;

public class MemberExceptionHandler extends GeneralException {

    public MemberExceptionHandler(BaseCode errorCommonStatus) {
        super(errorCommonStatus);
    }
}
