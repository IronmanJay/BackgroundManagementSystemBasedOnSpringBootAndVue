package com.ironmanjay.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口统一返回包装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    // 返回成功还是失败
    private String code;
    // 失败的原因
    private String msg;
    // 后台所需要携带的数据
    private Object data;

    /**
     * 无数据的成功请求
     *
     * @return 返回封装的结果
     */
    public static Result success() {
        return new Result(Constants.CODE_200, "", null);
    }

    /**
     * 有数据的成功请求
     *
     * @param data 请求得到的数据
     * @return 返回封装的结果
     */
    public static Result success(Object data) {
        return new Result(Constants.CODE_200, "", data);
    }

    /**
     * 请求错误
     *
     * @param code 错误标志码
     * @param msg  错误信息
     * @return 返回封装的结果
     */
    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }

    /**
     * 默认的请求错误
     *
     * @return 返回封装的结果
     */
    public static Result error() {
        return new Result(Constants.CODE_500, "系统错误", null);
    }

}
