package com.vognev.textilewebproject.model;

import java.util.List;

/**
 * textilewebproject_2  20/10/2021-19:10
 */
public enum Status {
    TO_SEND ("на відправку"),
    SENT ("відправлено"),
    CANCELED("скасований"),
    PAID("оплачений"),
    NOT_PAID("не оплачений"),
    WAITING("очікуе");
    //WITHOUT_TTN("без ТТН"),
    //TTN("ТТН")

    private  String status;

    Status(String status) {
        this.status = status;
    }

    Status() {
    }

    public String getStatus() {
        return status;
    }
}
