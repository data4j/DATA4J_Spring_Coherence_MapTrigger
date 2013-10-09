package org.data4j.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application class starts the application
 * 
 * @author data4j.org
 * @since 7 Oct 2013
 * @version 1.0.0
 *
 */
public class Application {

	/**
     * Starts the application
     *
     * @param  String[] args
     *
     */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		UserCacheUpdater cacheUpdater = (UserCacheUpdater) context.getBean("userCacheUpdater");
		new Thread(cacheUpdater).start();
	}
}
