package com.nineone.nocm.listener;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.nineone.nocm.domain.User;

import lombok.Getter;

@Component
@Getter
public class WebsocketEventListener {
	
	Map<String,Object> sessions = new HashMap<>();
	
	
//	@EventListener
//	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
//		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage()); 
//		String userid = getId(headerAccessor);
//		sessions.put(userid, headerAccessor.getSessionId());
//		
//	}
//	
//	@EventListener
//	public void handleWebSocketDisConnectListener(SessionDisconnectEvent event) {
//		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
//		String userid = getId(headerAccessor);
//		sessions.remove(userid);
//	}
	
//	public String getId(SimpMessageHeaderAccessor headerAccessor) {
//		User user = (User)headerAccessor.getSessionAttributes().get("user");
//		return user.getUserid();
//	}
	
}