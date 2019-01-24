package com.douzone.mysite.action.user;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;
import com.douzone.mysite.action.main.IndexAction;

public class UserActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("joinform".equals(actionName)) {
			action = new JoinFormAction();
		} else if("join".equals(actionName)) {
			action = new JoinAction();
		} else if("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if("loginform".equals(actionName)) {
			action = new LoginFormAction();
		} else {
			action = new IndexAction();
		}
		
		return action;
	}

}
