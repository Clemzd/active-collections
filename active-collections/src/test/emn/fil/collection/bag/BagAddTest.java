package test.emn.fil.collection.bag;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import emn.fil.collection.obs.type.OInteger;
import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.mutable.impl.Bag;
import emn.fil.collection.mutable.impl.Sequence;
import emn.fil.collection.mutable.interfaces.ICollection;
import emn.fil.collection.mutable.interfaces.IOrdered;

public class BagAddTest {

	private final ICollection<OInteger> a;

	public BagAddTest() {
		this.a = new Bag<OInteger>();
	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testAdd1() {
		this.a.add(new OInteger(1));
		Assert.assertArrayEquals(new OInteger[]{new OInteger(1)}, this.a.getContent().toArray());
	}

	@Test
	public void testAddN() {
		this.a.add(new OInteger(1));
		this.a.add(new OInteger(2));
		this.a.add(new OInteger(3));
		this.a.add(new OInteger(4));
		this.a.add(new OInteger(5));
		Assert.assertArrayEquals(new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(3), new OInteger(4), new OInteger(5) }, this.a
				.getContent().toArray());
	}

	@Test
	public void testAddBag() {
		this.a.add(new OInteger(1));
		this.a.add(new OInteger(2));
		this.a.add(new OInteger(3));
		this.a.add(new OInteger(3));
		this.a.add(new OInteger(2));
		this.a.add(new OInteger(4));
		this.a.add(new OInteger(2));
		this.a.add(new OInteger(5));
		this.a.add(new OInteger(1));
		this.a.add(new OInteger(5));
		this.a.add(new OInteger(5));
		Assert.assertArrayEquals(new OInteger[] { new OInteger(1), new OInteger(2), new OInteger(3), new OInteger(3), new OInteger(2),
				new OInteger(4), new OInteger(2), new OInteger(5), new OInteger(1), new OInteger(5), new OInteger(5) }, this.a.getContent().toArray());
	}
}
