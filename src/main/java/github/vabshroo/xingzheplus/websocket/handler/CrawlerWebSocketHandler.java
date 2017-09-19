package github.vabshroo.xingzheplus.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2017/9/16
 * @time 21:58
 * @desc 点击抓取的时候保持数据通信的WebSocket
 */
@Component
public class CrawlerWebSocketHandler implements WebSocketHandler {

    private static final Map<String, WebSocketSession> users = new HashMap<>();

    private final static Logger LOGGER = LoggerFactory.getLogger(CrawlerWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        LOGGER.info("Connection Established....");
        webSocketSession.sendMessage(new TextMessage("Server:connected OK!"));
        String sessionId = (String) webSocketSession.getAttributes().get("userSessionId");
        users.put(sessionId,webSocketSession);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        TextMessage returnMessage = new TextMessage(webSocketMessage.getPayload()
                + " received at server");
        LOGGER.info(webSocketSession.getHandshakeHeaders().getFirst("Cookie"));
        webSocketSession.sendMessage(returnMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(webSocketSession.isOpen()){
            webSocketSession.close();
        }
        LOGGER.error("websocket connection closed......",throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        LOGGER.info("websocket connection closed......");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessage(String sessionId,String message){
        TextMessage returnMessage = new TextMessage(message);
        LOGGER.info("sendMessage:{}",message);
        try {
            users.get(sessionId).sendMessage(returnMessage);
        } catch (IOException e) {
            LOGGER.error("send message error!",e);
        }
    }
}
