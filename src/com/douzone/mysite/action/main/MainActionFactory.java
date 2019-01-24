package com.douzone.mysite.action.main;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;

public class MainActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new IndexAction();
	}

}
