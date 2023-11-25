package com.rup.domain.enums;

import lombok.Getter;

@Getter
public enum DiscountPercentage {
    THIRTY(30), FIFTY(50), SEVENTY(70);

    private int percentage;

    DiscountPercentage(int percentage) {
        this.percentage = percentage;
    }

}
