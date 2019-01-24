package com.douzone.mvc.action;

public abstract class AbstractActionFactory {
	abstract public Action getAction(String actionName);
}
