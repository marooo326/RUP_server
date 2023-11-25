package com.rup.apiPayload.exception.handler;

import com.rup.apiPayload.code.BaseCode;
import com.rup.apiPayload.exception.GeneralException;

public class PromiseExceptionHandler extends GeneralException {

    public PromiseExceptionHandler(BaseCode errorCommonStatus) {
        super(errorCommonStatus);
    }
}
