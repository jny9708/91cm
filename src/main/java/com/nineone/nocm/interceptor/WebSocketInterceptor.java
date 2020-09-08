package com.nineone.nocm.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

public class WebSocketInterceptor implements ChannelInterceptor {
	
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		
		 StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		 String destination = accessor.getDestination();
		 


		return message;
	}
}
