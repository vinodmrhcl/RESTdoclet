package com.iggroup.oss.sample.domain.test;

import java.util.List;

/**
 * Composite thing
 */
public class CompositeObject {

	/**
	 * child1list
	 */
	private List<Child1> child1list;
	/**
	 * child2list
	 */
	private List<Child2> child2list;

	public List<Child1> getChild1list() {
		return child1list;
	}

	public void setChild1list(List<Child1> child1list) {
		this.child1list = child1list;
	}

	public List<Child2> getChild2list() {
		return child2list;
	}

	public void setChild2list(List<Child2> child2list) {
		this.child2list = child2list;
	}

}
