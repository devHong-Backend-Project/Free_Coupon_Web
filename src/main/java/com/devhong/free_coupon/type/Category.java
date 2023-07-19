package com.devhong.free_coupon.type;

import com.devhong.free_coupon.exception.CustomErrorCode;
import com.devhong.free_coupon.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    CHICKEN("치킨"),
    PIZZA("피자"),
    BEVERAGE("커피/음료"),
    FASTFOOD("패스트푸드");

    private final String category;

    public static Category fromString(String category) {
        for (Category c : Category.values()) {
            if (c.category.equalsIgnoreCase(category)) {
                return c;
            }
        }
        throw new CustomException(CustomErrorCode.CATEGORY_NOT_FOUND);
    }
}
