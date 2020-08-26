package com.nineone.nocm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/endpoint").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/sub");
		config.setApplicationDestinationPrefixes("/pub/");
	}

	// 인터셉터....
//	@Override
//	public void configureClientInboundChannel(ChannelRegistration registration) { 
//		registration.interceptors(new WebSocketInterceptor());
//		
//	}



//	@Override
//	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
//		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//		ObjectMapper copy = new ObjectMapper();
//        copy.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
//		converter.setObjectMapper(copy);
//		messageConverters.add(converter);
//		return false;
//	}
	
}
