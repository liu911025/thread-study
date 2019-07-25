package com.example.demotest.vo;



/**
 * Created by Cetacean on 2019/2/20 14:50
 */
public class RespData<T> {

    private String status;

    private String message;

    private T data;

    /**
     * 构建请求失败
     *
     * @param errcode
     * @return
     */
    public static RespData buildFail(String errcode, String errmsg) {
        RespData respData = new RespData();
        respData.setMessage(errmsg);
        respData.setStatus(errcode);
        return respData;
    }

    public static RespData buildFail(String errcode, String errmsg, Object obj) {
        RespData respData = new RespData();
        respData.setMessage(errmsg);
        respData.setStatus(errcode);
        respData.setData(obj);
        return respData;
    }

    /**
     * 构建请求成功 (无返回
     *
     * @return
     */
    public static<T> RespData buildSuccess(T t) {
        RespData respData = new RespData();
        respData.setMessage("success");
        respData.setStatus("0");
        respData.setData(t);
        return respData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
