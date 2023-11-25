package com.example.hospitalbloodbank.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MaZeYuan
 * @date 2023/9/22 20:50
 */
public class ResponseWrite {
    public static void out(HttpServletResponse response, CommonResponse commonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        //封装response的状态码和内容格式
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        //内容：resultModel json
        try {
            //使用jackson，把json格式的commonResponse写入到response的输出流中
            objectMapper.writeValue(response.getOutputStream(), commonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
