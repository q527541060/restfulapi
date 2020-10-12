package com.sinictek.restfulapi.model.apiResponse;

/**
 * 接口返回对象
 * @author sinictek-pd
 * @date 2020/06/02 14:20
 */
public class ApiResponse<T> {

    private Boolean success;

    private String message;

    private T data;


    public ApiResponse(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String message) {
        this.message = message;
    }
    /**
     * 请求成功
     * @author pd
     * @date 2020/06/02 14:20
     */
    public static <T> ApiResponse success(T data) {
        return ApiResponse.success("请求成功", data);
    }

    /**
     * 请求成功
     * @author pd
     * @date 2020/06/02 14:20
     */
    public static <T> ApiResponse success(String message, T data) {
        return new ApiResponse(Boolean.TRUE, message, data);
    }

    /**
     * table request
     * @author pd
     * @param message
     * @param <T>
     * @return
     */
    public static<T> ApiResponse tableResultData(String message){

        return new ApiResponse(message);
    }

    /**
     * 请求失败
     * @author pd
     * @date 2020/06/02 14:20
     */
    public static ApiResponse failed() {
        return ApiResponse.failed("请求失败");
    }

    /**
     * 请求失败
     * @author pd
     * @date 2020/06/02 14:20
     */
    public static ApiResponse failed(String message) {
        return new ApiResponse(Boolean.FALSE, message, null);
    }
  
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}