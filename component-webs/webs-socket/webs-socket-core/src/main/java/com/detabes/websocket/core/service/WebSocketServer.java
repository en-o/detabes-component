package com.detabes.websocket.core.service;

import com.detabes.websocket.core.util.SocketUtil;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 包含接收消息，推送消息等接口
 *
 * @ClassName  WebSocketServer
 * @description  webSocker服务端
 * @author  tn
 * @date 2020-07-08 12:36
 */
@Component
@ServerEndpoint(value = "/socket/{name}")
public class WebSocketServer {

    /** 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。 */
    private static AtomicInteger online = new AtomicInteger();

    /** concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。*/
    /**
     * 不允许多端登陆
     * private static Map<String, Session> sessionPools = new HashMap<>();
     */

    /** 允许多端登陆 */
    private static Map<String, List<Session>> sessionPoolsS = new HashMap<>();

    /**
     * 发送消息方法
     * @param session 客户端与socket建立的会话
     * @param message 消息
     * @throws IOException 抛出异常
     */
    public void sendMessage(Session session, String message) throws IOException {
        if(session != null){
            //没验证过 - 异步
            session.getAsyncRemote().sendText(message);
        }
    }


    /**
     * 连接建立成功调用
     * @param session 客户端与socket建立的会话
     * @param userName 客户端的userName
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "name") String userName){

        List<Session> sessionsArray = new ArrayList<>();
        //获取session
        List<Session> sessions = sessionPoolsS.get(userName);
        //存在session
        if (sessions != null) {
            sessionsArray.addAll(sessions);
        }
        sessionsArray.add(session);

        sessionPoolsS.put(userName, sessionsArray);
        addOnlineCount();
        System.out.println(userName + "加入webSocket！当前人数为" + online);
        try {
            sendMessage(session, "欢迎" + userName + "加入连接！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接时调用
     * @param userName 关闭连接的客户端的姓名
     * @param session session
     */
    @OnClose
    public void onClose(@PathParam(value = "name") String userName, Session session){

        //获取session
        List<Session> sessions = sessionPoolsS.get(userName);
        if(session==null){
            sessionPoolsS.remove(userName);
        }else {
            sessions.remove(session);
            sessionPoolsS.put(userName, sessions);
        }
        subOnlineCount();
        System.out.println(userName + "断开webSocket连接！当前人数为" + online);
    }

    /**
     * 收到客户端消息时触发（群发）
     * @param message 消息
     */
    @OnMessage
    public void onMessage(String message){
        for (List<Session> session: sessionPoolsS.values()) {
            try {
                session.forEach(sessionf -> {
                    try {
                        sendMessage(sessionf, message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 发生错误时候
     * @param throwable throwable
     */
    @OnError
    public void onError(Throwable throwable){
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    /**
     * 给指定用户发送消息
     * @param userName 用户名
     * @param message 消息
     */
    public void sendInfo(String userName, String message){
        List<Session> session = sessionPoolsS.get(userName);
        if(session!=null){
            try {
                session.forEach(sessionf -> {
                    try {
                        sendMessage(sessionf, message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * eg:  name:324
     *      name:432
     *
     * 传入 name:   就可以给上面的那个一起发数据（前提这来两个在线）
     * 给指定用户(模糊用户)发送消息
     * @param userName 用户名
     * @param message 消息
     * @throws IOException
     */
    public void sendInfoByLikeKey(String userName, String message){
        Map<String, List<Session>> stringListMap = SocketUtil.parseMapForFilter(sessionPoolsS, userName);
        List<Session> session = new ArrayList<>();
        stringListMap.forEach((key,value) -> {
            session.addAll(value);
        });
        if(session!=null){
            try {
                session.forEach(sessionf -> {
                    try {
                        sendMessage(sessionf, message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void addOnlineCount(){
        online.incrementAndGet();
    }

    public static void subOnlineCount() {
        online.decrementAndGet();
    }


}
