package org.data4j.srv;

import javax.annotation.PostConstruct;

import org.data4j.listener.UserMapListener;
import org.data4j.trigger.UserMapTrigger;
import org.data4j.user.User;
import org.springframework.stereotype.Service;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.MapTriggerListener;

/**
 * CacheService Class is implementation of ICacheService Interface
 * 
 * @author data4j.org
 * @since 7 Oct 2013
 * @version 1.0.0
 *
 */
@Service("userCacheService")
public class UserCacheService implements IUserCacheService {
	
	private static final String USER_MAP = "user-map";
	private static final long   LOCK_TIMEOUT = -1;
	
	private NamedCache userCache = null;	
	
	@PostConstruct
	private void initialize() {
		setUserCache(CacheFactory.getCache(USER_MAP));
		getUserCache().addMapListener(new UserMapListener());		
		getUserCache().addMapListener(new MapTriggerListener(new UserMapTrigger())); 
	}	
	
	/**
     * Adds user entries to cache
     *
     * @param Object key
     * @param Object value
     * 
     */
	public void addToUserCache(User user) {
		// key is locked
		getUserCache().lock(user.getId(), LOCK_TIMEOUT);
		try {
			// application logic
			getUserCache().put(user.getId(), user);
		} finally {
			// key is unlocked
			getUserCache().unlock(user.getId());
		}
	}

	/**
     * Deletes user entries from cache
     *
     * @param String key
     * 
     */
	public void deleteFromUserCache(String key) {
		// key is locked
		getUserCache().lock(key, LOCK_TIMEOUT);
		try {
			// application logic
			getUserCache().remove(key);
		} finally {
			// key is unlocked
			getUserCache().unlock(key);
		}
	}

	/**
     * Gets user cache
     *
     * @retun NamedCache Coherence named cache
     */
	public NamedCache getUserCache() {
		return userCache;
	}

	public void setUserCache(NamedCache userCache) {
		this.userCache = userCache;
	}
			
}
