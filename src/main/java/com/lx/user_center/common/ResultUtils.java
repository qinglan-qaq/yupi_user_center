package com.lx.user_center.common;

/**
 * 二次封装成功 或失败函数
 *
 * @author lx
 *
 */
public class ResultUtils {

//    在BusinessExpection继承RuntimeExpection后, 自定义的ResultUtils被完美替代


    /**
     * 执行成功
     *
     * @param data
     * @param <T>
     * @return
     */
    //    使用静态方法是为了简化调用、节约资源、保持代码清晰 无需声明new创建类
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(400, data, "执行成功", "");
    }

    /**
     * 执行失败
     * 自定义程度最高
     *
     * @param code
     * @param message
     * @param description
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> error(int code, String message, String description) {
        return new BaseResponse<>(code, null, message, description);
    }

    /**
     * 执行失败
     *
     * @param errorCode
     * @param description
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode.getCode(), null, errorCode.getMessage(), description);
    }

    /**
     * 执行失败
     *
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse<>(errorCode.getCode(), null, message, description);
    }


}
