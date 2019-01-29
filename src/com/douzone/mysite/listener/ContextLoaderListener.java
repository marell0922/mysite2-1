package com.douzone.mysite.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class ContextLoaderListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	String contextConfigLocation =
    		servletContextEvent.
    		getServletContext().
    		getInitParameter("contextConfigLocation");
    	
    	System.out.println("Container Starts..." + contextConfigLocation);
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }
}
