package org.data4j.srv;

import org.data4j.user.User;

import com.tangosol.net.NamedCache;

/**
 * IUserCacheService Interface exposes User Cache operations
 * 
 * @author data4j.org
 * @since 7 Oct 2013
 * @version 1.0.0
 *
 */
public interface IUserCacheService {

	/**
     * Adds user entries to cache
     *
     * @param User user
     * 
     */
	void addToUserCache(User user);
	
	/**
     * Deletes user entries from cache
     *
     * @param String id
     * 
     */
	void deleteFromUserCache(String id);
	
	/**
     * Gets user cache
     *
     * @retun NamedCache Coherence named cache
     */
	NamedCache getUserCache();
	
}
