package emn.fil.collection.mutable.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.mutable.interfaces.IOrdered;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;
import emn.fil.collection.obs.type.OAbstract;

public class Sequence<T extends OAbstract> extends Bag<T> implements IOrdered<T> {

	public Sequence(List<T> content) {
		super(content);
	}

	public Sequence() {
		super();
	}

	public Sequence(List<T> content, Function<T, T> func) {
		super(content, func);
	}

	public Sequence(List<T> content, Predicate<T> func) {
		super(content, func);
	}

	public Sequence(List<T> content, Comparator<T> functionSort) {
		super(content, functionSort);
	}

	public void add(final int index, final T element) {
		element.addObserver(this);
		this.content.add(index, element);
		this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.ADD, index));
	}

	public void remove(final int index) {
		this.content.remove(index);
		this.notify(new EventCollectionMessage<T>(null, TypeEventEnum.REMOVE, index));
	}

	@Override
	public ICollection<T> createCollectionType(final List<T> newList, final ICollection<T> b) {
		ICollection<T> c;
		if (b instanceof Bag)
		{
			c = new Bag<T>(newList);
		}
		else if (b instanceof Set)
		{
			c = new Set<T>(newList);
		}
		else
		{
			c = new Sequence<T>(newList);
		}
		link(c, b);
		return c;
	}

	@Override
	public ICollection<T> createCollectionTypeWhenSelec(final List<T> newList, final Predicate<T> func) {
		ICollection<T> c = new Sequence<T>(newList, func);
		link(c);
		return c;
	}

	@Override
	public ICollection<T> createCollectionTypeWhenApply(final List<T> newList, final Function<T, T> func) {
		ICollection<T> c = new Sequence<T>(newList, func);
		link(c);
		return c;
	}

	@Override
	public ICollection<T> createCollectionTypeWhenSort(final List<T> newList, final Comparator<T> functionSort) {
		ICollection<T> c = new Sequence<T>(newList);
		link(c);
		return c;
	}

	//

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(EventCollectionMessage<T> event) {
		if (this.functionSort != null)
		{
			final int pos = Collections.binarySearch(getContent(), event.getElement(), this.functionSort);
			if (pos < 0)
				add(-pos - 1, event.getElement());
		}
		else if (event.getIndex() != 0)
		{
			add(event.getIndex(), event.getElement());
		}
		else
		{
			final int pos = Collections.binarySearch(getContent(), event.getElement());
			if (pos < 0)
				add(-pos - 1, event.getElement());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(EventCollectionMessage<T> event) {
		if (event.getElement() != null)
		{
			remove(event.getElement());
		}
		else
		{
			remove(event.getIndex());
		}
	}

}
