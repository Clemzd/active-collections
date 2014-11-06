package emn.fil.collection.impl;

import java.util.ArrayList;
import java.util.List;

import emn.fil.collection.interfaces.ICollection;
import emn.fil.collection.obs.observer.C;
import emn.fil.collection.obs.subject.Subject;

public abstract class AbstractCollection<T> extends Subject<T> implements ICollection<T> {

	// Content of this collection
	protected List<T> content;

	public AbstractCollection(List<T> content) {
		this.content = content;
	}

	public AbstractCollection() {
		this.content = new ArrayList<T>();
	}

	public List<T> getContent() {
		return content;
	}
	
	/**
	 * Intersection with another collection
	 * @param b
	 * @return
	 */
	public C<T> intersection(AbstractCollection<T> b) {
		
		// on cree C
		final List<T> newList = new ArrayList<T>();
		final List<T> bList = b.getContent();

		final int bListSize = bList.size();
		int i = 0;
		do {
			T bListElement = bList.get(i);
			if (this.content.contains(bListElement)) {
				add(newList, bListElement);
			}
			i++;
		} while (i < bListSize);

		// link
		C<T> c = new C<T>(newList);
		link(c, b);

		return c;
	}
	
	/**
	 * Union with another collection
	 * @param b
	 * @return
	 */
	public C<T> union(AbstractCollection<T> b) {

		// on cree C
		final List<T> newList = new ArrayList<T>(content);
		final List<T> bList = b.getContent();

		// evite les doublons
		final int bListSize = bList.size();
		int i = 0;
		do {
			add(newList, bList.get(i));
			i++;
		} while (i < bListSize);
		
		// link
		C<T> c = new C<T>(newList);
		link(c, b);

		return c;
	}
	
	/**
	 * Difference with another collection
	 * @param b
	 * @return
	 */
	public C<T> difference(AbstractCollection<T> b) {

		// on cree C
		final List<T> newList = new ArrayList<T>();
		final List<T> aList = this.getContent();

		final int aListSize = aList.size();
		int i = 0;
		do {
			T aListElement = aList.get(i);
			if (!b.getContent().contains(aListElement)) {
				add(newList, aListElement);
			}
			i++;
		} while (i < aListSize);
		
		// link
		C<T> c = new C<T>(newList);
		link(c, b);

		return c;
	}

	/**
	 * Function linking this collection and B with C
	 * 
	 * @param contentC
	 * @param b
	 * @return
	 */
	private void link(C<T> c, AbstractCollection<T> b) {
		// link
		this.addObserver(c);
		b.addObserver(c);
	}
	
	protected abstract boolean add(List<T> newList, T element);
}