package com.nineone.nocm.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
	private int id;
	private int tasklist_id;
	private String content;
	private Date register_date;
	private Date edit_date;
	private String member_email;
	private int position;
	private boolean state;
	private Date start_date;
	private Date end_date;
	private String color;
	private String title;
	private int prog_rate;
	private int importance;
	private String assignee;
	private String reg_username;
	private String tasklist_name;
	private String channel_name;
	private String assignee_name;
	
	
}
