package emn.fil.collection.obs.observer;

import emn.fil.collection.obs.event.EventCollectionAttribute;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.type.OAbstract;

public interface Observer<T extends OAbstract> {

	/**
	 * Update the list with the element following the event.
	 * 
	 * @param event
	 */
	public void updateCollection(EventCollectionMessage<T> event);
	
	public void updateAttributeChanged(EventCollectionAttribute<? extends OAbstract> event);
}