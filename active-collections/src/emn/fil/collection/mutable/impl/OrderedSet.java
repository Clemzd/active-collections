package emn.fil.collection.mutable.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.mutable.interfaces.IOrdered;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;
import emn.fil.collection.obs.type.OAbstract;

public class OrderedSet<T extends OAbstract> extends Set<T> implements IOrdered<T> {

	private Sequence<T> sequence;

	public OrderedSet(List<T> content) {
		super(content);
	}
	
	public OrderedSet() {
		super();
	}
	
	public OrderedSet(List<T> content, Function<T, T> func) {
		super(content, func);
	}

	public OrderedSet(List<T> content, Predicate<T> func) {
		super(content, func);
	}
	
	public OrderedSet(List<T> content, Comparator<T> functionSort) {
		super(content, functionSort);
	}
	
	@Override
	public void add(int index, T element) {
		if (!this.content.contains(element))
		{
			this.content.add(index, element);
			this.notify(new EventCollectionMessage<T>(element, TypeEventEnum.ADD, index));
		}
	}

	@Override
	protected AbstractCollection<T> createCollectionType(List<T> newList, AbstractCollection<T> b) {
		AbstractCollection<T> c;
		if (b instanceof Bag)
		{
			c = new Bag<T>(newList);
		}
		else if (b instanceof Set)
		{
			c = new Set<T>(newList);
		}
		else if (b instanceof Sequence)
		{
			c = new Sequence<T>(newList);
		}
		else
		{
			c = new OrderedSet<T>(newList);
		}
		link(c, b);
		return c;
	}

	@Override
	protected AbstractCollection<T> createCollectionTypeWhenSelec(List<T> newList, Predicate<T> func) {
		AbstractCollection<T> c = new OrderedSet<T>(newList, func);
		link(c);
		return c;
	}

	@Override
	protected AbstractCollection<T> createCollectionTypeWhenApply(List<T> newList, Function<T, T> func) {
		AbstractCollection<T> c = new Sequence<T>(newList, func);
		link(c);
		return c;
	}
	
	@Override
	protected AbstractCollection<T> createCollectionTypeWhenSort(List<T> newList, Comparator<T> functionSort) {
		AbstractCollection<T> c = new OrderedSet<T>(newList);
		link(c);
		return c;
	}
}
