package org.data4j.trigger;

import org.apache.log4j.Logger;
import org.data4j.user.User;

import com.tangosol.util.MapTrigger;

/**
 * UserMapTrigger executes required logic before the operation is committed
 * 
 * @author data4j.org
 * @since 7 Oct 2013
 * @version 1.0.0
 * 
 */
public class UserMapTrigger implements MapTrigger {
	
	private static final long serialVersionUID = 5411263646665358790L;
	private static final Logger logger = Logger.getLogger(UserMapTrigger.class);
	
	/**
     * Processes user cache entries before the entry is inserted into the map.
     *
     * @param MapTrigger.Entry entry
     * 
     */
	@Override
	public void process(MapTrigger.Entry entry) {
		User user = (User) entry.getValue();
		String id = user.getId();
		String name = user.getName();
		String updatedName = name.toUpperCase();

		String surname = user.getSurname();
		String updatedSurname = surname.toUpperCase();

		if (!updatedName.equals(name)) {
			user.setName(updatedName);
		}
		
		if (!updatedSurname.equals(surname)) {
			user.setSurname(updatedSurname);
		}
		
		user.setId(user.getName() + "_" + user.getSurname());
		
		entry.setValue(user);
		
		logger.debug("UserMapTrigger processes the entry before committing. "
							+ "oldId : " + id 
	            			+ ", newId : " + ((User)entry.getValue()).getId() 
		                    + ", oldName : " + name 
				            + ", newName : " + ((User)entry.getValue()).getName() 
				            + ", oldSurname : " + surname 
				            + ", newSurname : " + ((User)entry.getValue()).getSurname()
				            );
		
	}
	
	@Override
	public boolean equals(Object o) {
		return o != null && o.getClass() == this.getClass();
	}

	@Override
	public int hashCode() {
		return getClass().getName().hashCode();
	}
	
}
