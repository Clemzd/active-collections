package observer;

import java.util.ArrayList;
import java.util.List;

import observer.impl.AbstractCollection;
import observer.impl.Bag;

public class App {

	public static void main(String[] args) {

		AbstractCollection<Integer> a = new Bag<Integer>(new ArrayList<Integer>() {
			{
				add(1);
				add(2);
				add(3);
			}
		});
		AbstractCollection<Integer> b = new Bag<Integer>(new ArrayList<Integer>() {
			{
				add(4);
				add(5);
				add(6);
			}
		});
		AbstractCollection<Integer> d = new Bag<Integer>(new ArrayList<Integer>() {
			{
				add(7);
			}
		});
		C<Integer> c = a.union(b);
		System.out.println(c);
		a.add(1);
		// a.getC();
		System.out.println(c);
	}
}
