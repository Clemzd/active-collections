package emn.fil.collection.impl;

import java.util.List;

public class OrderedSet<T> extends AbstractCollection<T> {

	private Set<T> set;
	private Sequence<T> sequuence;

	public OrderedSet(List<T> content) {
		super(content);
	}

	@Override
	public void add(T element) {
		// return false;
	}

	@Override
	protected boolean add(List<T> newList, T element) {
		return false;
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
	}
}