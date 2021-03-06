package com.detabes.websocket.client.controller;

import com.detabes.websocket.core.service.WebSocketServer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * websocket 基础接口
 * @ClassName SocketController
 * @description   socket
 * @author tn
 * @date 2020-07-08 12:40
 */
public interface SocketController {

    /**
     * 给指定用户推送消息
     * @param userName 用户名
     * @param message 消息
     * @param webSocketServer  webSocketServer
     */
    @RequestMapping(value = "/only", method = RequestMethod.GET)
    @ApiOperation("给指定用户推送消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName", value="用户名",  dataType="String", required=true),
            @ApiImplicitParam(name="message", value="消息", dataType="String", required=true)
    })
    default void onlyUserSocket(@RequestParam String userName, @RequestParam String message, WebSocketServer webSocketServer){
        webSocketServer.sendInfo(userName, message);
    }

    /**
     * 给所有用户推送消息
     * @param message 消息
     * @param webSocketServer  webSocketServer
     */
    @ApiOperation("给所有用户推送消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="message", value="消息", dataType="String", required=true)
    })
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    default void allUserSocket(@RequestParam String message, WebSocketServer webSocketServer){
        webSocketServer.onMessage(message);
    }
}
