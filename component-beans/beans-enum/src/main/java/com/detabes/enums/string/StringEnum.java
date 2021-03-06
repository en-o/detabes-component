package com.detabes.enums.string;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tn
 * @version 1
 * @ClassName StringEnum
 * @description 字符串枚举
 * @date 2020/12/14 17:04
 */
@Getter
@AllArgsConstructor
public enum StringEnum {

    /** null串 */
    NULL_STRING("null","null串"),
    /** 空串 */
    LENGTH_ZERO(" ","空串"),
    /** empty串 */
    EMPTY_STRING("empty","empty");


    private String str;
    private String remark;
}
