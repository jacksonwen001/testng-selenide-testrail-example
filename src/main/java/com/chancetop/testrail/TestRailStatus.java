package com.chancetop.testrail;


import lombok.Getter;

@Getter
public enum TestRailStatus {

    PASSED(1),
    BLOCKED(2),
    UNTESTED(3),
    RETEST(4),
    FAILED(5);

    int status;
    TestRailStatus(int status) {
        this.status = status;
    }
}
