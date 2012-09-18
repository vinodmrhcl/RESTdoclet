package com.iggroup.oss.sample.domain.test;

/**
 * Foo
 * 
 * Sometimes,
 *      comments like to span
 *     multiple rows, and include things like lists:
 * <ul>
 * <li>item 1</li>
 * <li>item 2</li>
 * </ul>
 */
public class FooObject extends BaseObject {

	/**
	 * doo attr
	 */
	private DooObject doo;

	/**
	 * get the Doo
	 * @return the doo
	 */
	public DooObject getDoo() {
		return doo;
	}

	public void setDoo(DooObject doo) {
		this.doo = doo;
	}
}
