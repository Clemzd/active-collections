package emn.fil.collection.mutable.impl;

import java.util.List;

import emn.fil.collection.mutable.interfaces.IUniqueness;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;

public class Set<T> extends AbstractCollection<T> implements IUniqueness<T> {

	public Set(List<T> content) {
		super(content);
	}

	protected boolean add(List<T> newC, T element) {
		boolean added = false;
		if (!newC.contains(element))
		{
			added = newC.add(element);
		}
		return added;
	}

	public void add(T element) {
		if (this.add(getContent(), element))
		{
			this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.ADD));
		}
	}

	public void remove(T element) {
		if (!this.content.contains(element))
		{
			this.content.remove(element);
			this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.REMOVE));
		}
	}

}
