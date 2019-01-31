package com.douzone.mysite.action.board;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;

public class BoardActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("writeform".equals(actionName)) {
			return new WriteFormAction();
		}else if("write".equals(actionName)) {
			return new WriteAction();
		}else if("modifyform".equals(actionName)) {
			return new ModifyFormAction();
		}else if("modify".equals(actionName)) {
			return new ModifyAction();
		}else if("delete".equals(actionName)) {
			return new DeleteAction();
		}else if("view".equals(actionName)){
			return new ViewAction();
		}else {
			return new ListAction();
		}

	}

}
