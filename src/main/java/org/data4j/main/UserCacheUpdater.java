package org.data4j.main;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.data4j.srv.IUserCacheService;
import org.data4j.user.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * CacheUpdater Class updates and prints user cache entries
 * 
 * @author data4j.org
 * @since 7 Oct 2013
 * @version 1.0.0
 *
 */
@Component("userCacheUpdater")
public class UserCacheUpdater implements BeanFactoryAware, Runnable {
	
	private static final Logger logger = Logger.getLogger(UserCacheUpdater.class);
	
	@Autowired
	private IUserCacheService userCacheService;
	
	private BeanFactory beanFactory;
	
	/**
     * Runs the UserCacheUpdater Thread
     *
     */
	public void run() {		
		
		//New User is created...
		User user = beanFactory.getBean(User.class);
		
		//Id, Name and Surname properties are set. However, they will be updated at trigger level. 
		user.setId("user1");
		user.setName("james");
		user.setSurname("joyce");
		
		//Entries are added to cache...
		userCacheService.addToUserCache(user);

		//Cache Entries are being printed...
		printCacheEntries();		
	}
	
	/**
     * Prints User Cache Entries
     *
     */
	@SuppressWarnings("unchecked")
	private void printCacheEntries() {
		Collection<User> userCollection = null;
		try {
			while(true) {
				userCollection = (Collection<User>)userCacheService.getUserCache().values();
				
				for(User user : userCollection) {
					logger.debug("Cache Content : " + user);
				}
				
				Thread.sleep(60_000);			
			}
		} catch (InterruptedException e) {
			logger.error("CacheUpdater is interrupted!", e);
		}
	}

	public void setUserCacheService(IUserCacheService userCacheService) {
		this.userCacheService = userCacheService;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}	
}
