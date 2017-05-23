package org.sangaizhi.nettychat.core.model;

/**
 * 请求消息对象
 * @author sangaizhi
 * @date 2017/5/22
 */
public class Request {

    /**
     * 请求模块号
     */
    private Short module;

    /**
     * 请求命令号
     */
    private Short command;

    /**
     * 请求数据
     */
    private byte[] data;

    public static Request valueOf(Short module, Short command, byte[] data){
        Request request = new Request();
        request.setModule(module);
        request.setCommand(command);
        request.setData(data);
        return request;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
