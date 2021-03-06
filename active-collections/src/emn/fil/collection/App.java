package emn.fil.collection;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

import test.emn.fil.collection.object.OPersonne;
import emn.fil.collection.mutable.impl.Bag;
import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.obs.type.OInteger;

public class App {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		// branch
		ICollection<OInteger> a = new Bag<OInteger>(new ArrayList<OInteger>() {
			{
				add(new OInteger(1));
				add(new OInteger(2));
				add(new OInteger(3));
			}
		});
		ICollection<OInteger> b = new Bag<OInteger>(new ArrayList<OInteger>() {
			{
				add(new OInteger(4));
				add(new OInteger(5));
				add(new OInteger(6));
			}
		});
		ICollection<OInteger> d = new Bag<OInteger>(new ArrayList<OInteger>() {
			{
				add(new OInteger(5));
				add(new OInteger(6));
				add(new OInteger(7));
			}
		});
		ICollection<OInteger> c = a.union(b);
		System.out.println(c);
		a.add(new OInteger(1));
		a.remove(new OInteger(1));
		System.out.println(c);

		ICollection<OInteger> e = b.intersection(d);
		System.out.println(e);

		ICollection<OInteger> f = a.difference(b);
		System.out.println("A\\B=" + f);

		ICollection<OInteger> g = b.difference(d);
		System.out.println("B\\D=" + g);

		ICollection<OPersonne> test = new Bag<OPersonne>(new ArrayList<OPersonne>() {
			{
				add(new OPersonne(18, "Clement", 12345));
				add(new OPersonne(22, "Benjamin", 666));
				add(new OPersonne(53, "Mamadou", 69));
			}
		});

		// Test Apply
		Function<OPersonne, OPersonne> func = (OPersonne element) -> {
			return new OPersonne(5, element.getName(), element.getNumero());
		};
		ICollection<OPersonne> e1 = test.apply(func);
		System.out.println(test);
		System.out.println(e1);

		// Test Selec
		Predicate<OPersonne> func2 = (OPersonne element) -> {
			return element.getAge() > 18;
		};
		ICollection<OPersonne> e2 = test.selection(func2);
		System.out.println("ALLOOOOOOOOOOOOOOOOO \n" + test);
		System.out.println(e2);

		// Test exists
		ICollection<OPersonne> testExist = new Bag<OPersonne>(new ArrayList<OPersonne>() {
			{
				add(new OPersonne(18, "Clement", 12345));
				add(new OPersonne(53, "Mamadou", 69));
			}
		});
		System.out.println(test.exists(testExist));

		// test toUnique
		ICollection<OPersonne> testUnique = new Bag<OPersonne>(new ArrayList<OPersonne>() {
			{
				add(new OPersonne(18, "Clement", 12345));
				add(new OPersonne(22, "Benjamin", 666));
				add(new OPersonne(22, "Benjamin", 666));
				add(new OPersonne(22, "Benjamin", 666));
				add(new OPersonne(22, "Benjamin", 666));
				add(new OPersonne(53, "Mamadou", 69));
				add(new OPersonne(53, "Mamadou", 69));
			}
		});
		ICollection<OPersonne> e3 = testUnique.toUnique();
		System.out.println(testUnique);
		System.out.println(e3);

		ICollection<OPersonne> e4 = testUnique.reject(test);
		System.out.println("BIS \n" + e4);

	}
}
