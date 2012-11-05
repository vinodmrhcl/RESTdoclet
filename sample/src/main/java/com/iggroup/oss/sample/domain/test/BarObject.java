package com.iggroup.oss.sample.domain.test;

import java.util.List;

/**
 * Bar
 */
public class BarObject extends BaseObject {

	/** foo attr
	 * 
	 */
	private FooObject foo;

	/**
	 * recursion
	 */
	private List<BarObject> bars;

	/**
	 * get the foo out of here
	 */
	public FooObject getFoo() {
		return foo;
	}

	/**
	 * set the foo, man
	 */
	public void setFoo(FooObject foo) {
		this.foo = foo;
	}

	/**
	 * get the bars
	 * @return list of bars
	 */
	public List<BarObject> getBars() {
		return bars;
	}

	/**
	 * Set the bars
	 * @param bars list of bars
	 */
	public void setBars(List<BarObject> bars) {
		this.bars = bars;
	}

}
