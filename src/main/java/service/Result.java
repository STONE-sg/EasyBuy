package service;

/**
 * @author: stone
 * @program: SSM_Demo
 * @description:
 * @date: 2021-11-18 11:50:07
 */
public class Result {
    private int status = 1;  //状态，成功：0，失败：1
    private String msg;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result [" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ']';
    }
}
