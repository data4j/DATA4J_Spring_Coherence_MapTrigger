package org.data4j.listener;

import org.apache.log4j.Logger;

import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

/**
 * UserMapListener Class listens user cache events
 * 
 * @author data4j.org
 * @since 7 Oct 2013
 * @version 1.0.0
 *
 */
public class UserMapListener implements MapListener {

	private static final Logger logger = Logger.getLogger(UserMapListener.class);
	
	/**
     * This method is invoked when an entry is deleted from the cache...
     *
     * @param MapEvent me
     */
	public void entryDeleted(MapEvent me) {
		logger.debug("Deleted Key = " + me.getKey() + ", Value = " + me.getOldValue());
	}

	/**
     * This method is invoked when an entry is inserted to the cache...
     *
     * @param MapEvent me
     */
	public void entryInserted(MapEvent me) {
		logger.debug("Inserted Key = " + me.getKey() + ", Value = " + me.getNewValue());
	}

	/**
     * This method is invoked when an entry is updated on the cache...
     *
     * @param MapEvent me
     */
	public void entryUpdated(MapEvent me) {
//		logger.debug("Updated Key = " + me.getKey() + ", New_Value = " + me.getNewValue() + ", Old Value = " + me.getOldValue());
	}	
}
