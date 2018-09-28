package com.choju.fpro.vo;

public class MemberVO {
	private String id;
	private String password;
	private String name;
	private String age;
	private String gender;
	private String email;
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", age=" + age + ", gender="
				+ gender + ", email=" + email + "]";
	}

	public MemberVO() {
		//이게 생성자
	}
	
	public MemberVO(String id, String password, String name, String age, String gender, String email) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public StringBuffer getEmail_domain() {
		return null;
	}
	
	
}
