package observer.impl;

import java.util.List;

import observer.EventCollection;
import observer.interfaces.IUniqueness;

public class Set<T> extends AbstractCollection<T> implements IUniqueness<T> {

	public Set(List<T> content) {
		super(content);
	}

	protected void add(List<T> newC, T element) {
		if (!newC.contains(element)) {
			newC.add(element);
		}
	}

	public void add(T element) {
		if (!content.contains(element)) {
			content.add(element);
			this.notify(element, EventCollection.ADD);
		}
	}

	public void remove(T element) {
		if (!content.contains(element)) {
			content.remove(element);
			this.notify(element, EventCollection.REMOVE);
		}
	}

}
