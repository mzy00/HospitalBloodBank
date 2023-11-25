package com.example.hospitalbloodbank.common;

import cn.hutool.http.HttpStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author MaZeYuan
 * @date 2023/9/22 20:33
 */
@Data
public class CommonResponse<T> implements Serializable {
    @ApiModelProperty(value = "编码（200正常，500异常）", required = true, example = "200")
    private int code;
    @ApiModelProperty(value = "信息（success成功）")
    private String message;
    @ApiModelProperty(value = "数据")
    private T data;

    public static <T> CommonResponse<T> success() {
        CommonResponse<T> response = new CommonResponse<>();
        response.code = HttpStatus.HTTP_OK;
        response.message = "success";
        response.data = null;
        return response;
    }

    public static <T> CommonResponse<T> success(T data) {
        CommonResponse<T> response = new CommonResponse<>();
        response.code = HttpStatus.HTTP_OK;
        response.message = "success";
        response.data = data;
        return response;
    }

    public static <T> CommonResponse<T> success(T data, String message) {
        CommonResponse<T> response = new CommonResponse<>();
        response.code = HttpStatus.HTTP_OK;
        response.message = message;
        response.data = data;
        return response;
    }

    public static <T> CommonResponse<T> fail(String message) {
        CommonResponse<T> response = new CommonResponse<>();
        response.code = HttpStatus.HTTP_INTERNAL_ERROR;
        response.message = message;
        response.data = null;
        return response;
    }
}
