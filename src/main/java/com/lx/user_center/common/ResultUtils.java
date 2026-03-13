package com.lx.user_center.common;

/**
 * 二次封装成功 或失败函数
 * @author lx
 *
 */
public class ResultUtils {

//    使用静态方法是为了简化调用、节约资源、保持代码清晰 无需声明new创建类

    /**
     * 执行成功
     * @param data
     * @return
     * @param <T>
     */
    public static  <T> BaseResponse<T> success(T data){
        return new  BaseResponse<>(400,data,"success! 成功执行");
    }

    /**
     * 执行失败
     * @param errorCode
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode){
        return new  BaseResponse<>(errorCode);
    }



}
