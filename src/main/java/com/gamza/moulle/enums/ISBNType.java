package com.gamza.moulle.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ISBNType {

    ISBN13("ISBN13"),
    ISBN10("ISBN10");

    private final String key;

}
