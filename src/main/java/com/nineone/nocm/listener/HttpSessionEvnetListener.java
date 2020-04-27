package com.nineone.nocm.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.nineone.nocm.domain.JoinInfo;
import com.nineone.nocm.domain.LastAccess;
import com.nineone.nocm.domain.User;
import com.nineone.nocm.service.JoinInfoService;

@WebListener
public class HttpSessionEvnetListener implements HttpSessionListener{
	
	@Autowired
	private JoinInfoService joinInfoService;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSessionListener.super.sessionCreated(se);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		if(session.getAttribute("lastAccess")!=null) {
			LastAccess lastAccess = (LastAccess)session.getAttribute("lastAccess");
			if(lastAccess.isContentWrapper()) {
				if(lastAccess.isFocus()) {
					User user = (User)session.getAttribute("user");
					joinInfoService.updateLastAccessDate(lastAccess.getCurrentChannelId(),user.getEmail());
				}
			}
		}
	}
	  

}
