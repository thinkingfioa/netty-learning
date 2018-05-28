package org.lwl.netty.message.body;

import org.lwl.netty.constant.MessageTypeEnum;
import org.lwl.netty.message.Body;

/**
 * @author thinking_fioa
 * @createTime 2018/5/9
 * @description 登陆消息
 */


public class LoginReqBody extends Body{

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoginReqBody [");
        sb.append("userName=").append(userName);
        sb.append(", password=").append(password).append("]");

        return sb.toString();
    }

    @Override
    public MessageTypeEnum msgType() {
        return MessageTypeEnum.LOGIN_REQ;
    }
}
