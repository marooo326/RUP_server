package com.rup.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberKeyword {

    FREEWHEELING("자유분방한"),
    PUNCTUALITY("약속을 잘 지키는"),
    SASSY("기다리는게 싫은"),
    FREEZING("밖에가 추운"),
    CEO("시급이 비싼"),
    WILLINGNESS("늦지 않으려는 의지");

    private final String description;
}
