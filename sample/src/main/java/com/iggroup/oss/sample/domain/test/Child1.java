package com.iggroup.oss.sample.domain.test;


/**
 * Child 1
 */
public class Child1 {
	/**
	 * nick
	 */
	private String nickname;
	/**
	 * age
	 */
	private Integer age;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Ignore
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
