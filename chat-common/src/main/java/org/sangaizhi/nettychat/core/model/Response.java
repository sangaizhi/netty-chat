package org.sangaizhi.nettychat.core.model;

/**
 * 响应消息对象
 * @author sangaizhi
 * @date 2017/5/22
 */
public class Response {

    private Short module;

    private Short command;

    private int resultCode = ResultCode.SUCCESS;

    private byte[] data;

    public Response(){}

    public Response(Request request){
        this.command = request.getCommand();
        this.module = request.getModule();
    }
    public Response(Short module, Short command, byte[] data){
        this.module = module;
        this.command = command;
        this.data = data;
    }

    public Short getModule() {
        return module;
    }

    public void setModule(Short module) {
        this.module = module;
    }

    public Short getCommand() {
        return command;
    }

    public void setCommand(Short command) {
        this.command = command;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

