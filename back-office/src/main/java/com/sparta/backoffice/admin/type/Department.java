package com.sparta.backoffice.admin.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Department {

    CURRICULUM("커리큘럼"),
    MARKETING("마케팅"),
    DEVELOPMENT("개발");

    private final String value;
}
