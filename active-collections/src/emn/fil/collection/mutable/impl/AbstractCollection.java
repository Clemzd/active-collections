package emn.fil.collection.mutable.impl;

import java.util.ArrayList;
import java.util.List;

import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.immutable.impl.ImmutableBag;
import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.obs.subject.Subject;

public abstract class AbstractCollection<T> extends Subject<T> implements ICollection<T> {

	/** Content of this collection. */
	protected List<T> content;

	public AbstractCollection(List<T> content) {
		this.content = content;
	}

	public AbstractCollection() {
		this.content = new ArrayList<T>();
	}

	/**
	 * {@inheritDoc}
	 */
	public AbstractImmutableCollection<T> intersection(AbstractCollection<T> b) {

		// we create C
		final List<T> newList = new ArrayList<T>();
		final List<T> bList = b.getContent();

		final int bListSize = bList.size();
		int i = 0;
		do
		{
			T bListElement = bList.get(i);
			if (this.content.contains(bListElement))
			{
				add(newList, bListElement);
			}
			i++;
		} while (i < bListSize);

		// link
		AbstractImmutableCollection<T> c = new ImmutableBag<T>(newList);
		link(c, b);

		return c;
	}

	/**
	 * {@inheritDoc}
	 */
	public AbstractImmutableCollection<T> union(AbstractCollection<T> b) {

		// on cree C
		final List<T> newList = new ArrayList<T>(content);
		final List<T> bList = b.getContent();

		// evite les doublons
		final int bListSize = bList.size();
		int i = 0;
		do
		{
			add(newList, bList.get(i));
			i++;
		} while (i < bListSize);

		// link
		AbstractImmutableCollection<T> c = new ImmutableBag<T>(newList);
		link(c, b);

		return c;
	}

	/**
	 * {@inheritDoc}
	 */
	public AbstractImmutableCollection<T> difference(AbstractCollection<T> b) {

		// on cree C
		final List<T> newList = new ArrayList<T>();
		final List<T> aList = this.getContent();

		final int aListSize = aList.size();
		int i = 0;
		do
		{
			T aListElement = aList.get(i);
			if (!b.getContent().contains(aListElement))
			{
				add(newList, aListElement);
			}
			i++;
		} while (i < aListSize);

		// link
		AbstractImmutableCollection<T> c = new ImmutableBag<T>(newList);
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
	private void link(AbstractImmutableCollection<T> c, AbstractCollection<T> b) {
		// link
		this.addObserver(c);
		b.addObserver(c);
	}

	/**
	 * {@inheritDoc}
	 */
	protected abstract boolean add(List<T> newList, T element);

	public List<T> getContent() {
		return content;
	}
}