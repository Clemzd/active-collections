package emn.fil.collection.immutable.impl;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.type.OAbstract;

public class ImmutableOrderedSet<T extends OAbstract> extends ImmutableSet<T> {

	private ImmutableSequence<T> sequence;

	public ImmutableOrderedSet(List<T> content) {
		super(content);
	}

	public ImmutableOrderedSet(List<T> content, Function<T, T> func) {
		super(content, func);
	}

	public ImmutableOrderedSet(List<T> content, Predicate<T> func) {
		super(content, func);
	}
	
	public ImmutableOrderedSet(List<T> content, Comparator<T> functionSort) {
		super(content, functionSort);
	}
}