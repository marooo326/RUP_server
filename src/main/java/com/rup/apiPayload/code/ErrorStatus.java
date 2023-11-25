package com.rup.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseCode {
    _INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR, 5000, "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(BAD_REQUEST, 4000, "잘못된 요청입니다."),
    _UNAUTHORIZED(UNAUTHORIZED, 4001, "로그인이 필요합니다."),
    _FORBIDDEN(FORBIDDEN, 4003, "금지된 요청입니다."),

    _MEMBER_NOT_FOUND_EXCEPTION(NOT_FOUND, 4004, "존재하지 않는 회원입니다."),
    _MEMBER_ALREADY_EXIST_EXCEPTION(BAD_REQUEST, 4005, "이미 존재하는 회원입니다."),
    _MEMBER_NOT_MATCH_EXCEPTION(BAD_REQUEST, 4006, "회원 정보가 일치하지 않습니다."),
    _MEMBER_NOT_AUTHORIZED_EXCEPTION(UNAUTHORIZED, 4007, "권한이 없습니다."),

    _PROMISE_NOT_FOUND_EXCEPTION(NOT_FOUND, 4010, "존재하지 않는 약속입니다."),
    _PROMISE_ALREADY_EXIST_EXCEPTION(BAD_REQUEST, 4011, "이미 존재하는 약속입니다."),
    _PROMISE_NOT_MATCH_EXCEPTION(BAD_REQUEST, 4012, "약속 정보가 일치하지 않습니다."),
    _PROMISE_NOT_AUTHORIZED_EXCEPTION(UNAUTHORIZED, 4013, "권한이 없습니다."),
    _PROMISE_NOT_PARTICIPANT_EXCEPTION(UNAUTHORIZED, 4014, "참여자가 아닙니다."),

    INVALID_TOKEN_EXCEPTION(UNAUTHORIZED, 4050, "토큰이 올바르지 않습니다.");
    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;

    @Override
    public Reason getReason() {
        return Reason.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public Reason getReasonHttpStatus() {
        return Reason.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
