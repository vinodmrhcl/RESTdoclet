package com.iggroup.oss.sample.domain.test;

/**
 * Bar
 */
public class BarObject extends BaseObject {

	/** foo attr
	 * 
	 */
	private FooObject foo;

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

}
