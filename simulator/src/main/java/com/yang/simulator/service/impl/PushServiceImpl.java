package com.yang.simulator.service.impl;
import com.yang.simulator.netty.NettyConfig;
import com.yang.simulator.service.PushService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sixiaojie
 * @date 2020-03-30-20:10
 */
@Service
public class PushServiceImpl implements PushService {

    @Override
    public void pushMsgToOne(String userId, String msg){
        ConcurrentHashMap<String, Channel> userChannelMap = NettyConfig.getUserChannelMap();
        Channel channel = userChannelMap.get(userId);
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }
    @Override
    public void pushMsgToAll(String msg){
        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
