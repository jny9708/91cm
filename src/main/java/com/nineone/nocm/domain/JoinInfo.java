package com.nineone.nocm.domain;

import java.time.LocalDateTime;

import com.nineone.nocm.domain.Channel.ChannelBuilder;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinInfo {
	int id;
	int channel_id;
	String register_date;
	String member_email;
	
	 @Builder
	 public JoinInfo(int id, int channel_id, String register_date, String member_email){
	        this.id = id;
	        this.channel_id= channel_id;
	        this.register_date = register_date;
	        this.member_email = member_email;;
	    }
	
}