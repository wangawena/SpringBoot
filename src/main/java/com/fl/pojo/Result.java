package com.fl.pojo;

//信息封装
public class Result<T>{

    private String msg;
    private String success;
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }


    @Override
    public String toString() {
        return "Result{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
