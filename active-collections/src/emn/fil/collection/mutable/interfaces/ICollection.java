package emn.fil.collection.mutable.interfaces;

import java.util.List;
import java.util.function.Predicate;

import emn.fil.collection.functions.FunctionApply;
import emn.fil.collection.immutable.impl.AbstractImmutableCollection;
import emn.fil.collection.mutable.impl.AbstractCollection;

public interface ICollection<T> {
	/**
	 * Union with the other collection, b.
	 * 
	 * @param b
	 *            the other collection
	 * @return the union collection
	 */
	public AbstractImmutableCollection<T> union(AbstractCollection<T> b);

	/**
	 * Intersection with the other collection, b.
	 * 
	 * @param b
	 *            the other collection
	 * @return the union collection
	 */
	public AbstractImmutableCollection<T> intersection(AbstractCollection<T> b);

	/**
	 * Difference with the other collection, b.
	 * 
	 * @param b
	 *            the other collection
	 * @return the union collection
	 */
	public AbstractImmutableCollection<T> difference(AbstractCollection<T> b);

	/**
	 * Add the element to the collection.
	 * 
	 * @param element
	 *            the element to add
	 */
	public void add(T element);

	/**
	 * Remove the element to the collection.
	 * 
	 * @param element
	 *            the element to remove
	 */
	public void remove(T element);

	/** Get the content of the collection. */
	public List<T> getContent();
	
	/**
	 * Apply the function on all the collection
	 * @param func to apply on elements
	 * @return new collection with member applied by the func
	 */
	public AbstractImmutableCollection<T> apply(FunctionApply<T> func);
	
	/**
	 * Selection element of the collection that match the function
	 * @param func
	 * @return
	 */
	public AbstractImmutableCollection<T> selection(Predicate<T> func);
	
	/**
	 * Check if this collection contains the same elements as B collection
	 * @param b
	 * @return
	 */
	public boolean exists(AbstractCollection<T> b);
	
	/**
	 * Return a collection which match the uniqueness predicate
	 * @return
	 */
	public AbstractImmutableCollection<T> toUnique();
	
	/**
	 * Return a collection of A without element of B
	 * @param b
	 * @return
	 */
	public AbstractImmutableCollection<T> reject(AbstractCollection<T> b);
	
	/**
	 * Check if the collection is empty
	 * @return true or false
	 */
	public boolean isEmpty();
	
	/**
	 * Return the number of element T contain in this collection
	 * @return
	 */
	public int size();
}
