package com.ust.buddy.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "buddy_table",uniqueConstraints = {@UniqueConstraint(columnNames = {"buddy_id"})})
public class Buddy {
	
	@Id
	private String id;
	@Column(unique = true)
	private String buddy_id;
	private String buddy_name;
	private String account_name;
	private String email;
	private long phno;
	private String techstack;
	private int no_associates_assigned;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy = "buddy")
	private List<Associate> associate= new ArrayList<Associate>();
	
	public List<Associate> getAssociate() {
		return associate;
	}
	public void setAssociate(List<Associate> associate) {
		this.associate = associate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuddy_id() {
		return buddy_id;
	}
	public void setBuddy_id(String buddy_id) {
		this.buddy_id = buddy_id;
	}
	public String getBuddy_name() {
		return buddy_name;
	}
	public void setBuddy_name(String buddy_name) {
		this.buddy_name = buddy_name;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhno() {
		return phno;
	}
	public void setPhno(long phno) {
		this.phno = phno;
	}
	public String getTechstack() {
		return techstack;
	}
	public void setTechstack(String techstack) {
		this.techstack = techstack;
	}
	public int getNo_associates_assigned() {
		return no_associates_assigned;
	}
	public void setNo_associates_assigned(int no_associates_assigned) {
		this.no_associates_assigned = no_associates_assigned;
	}
	@Override
	public int hashCode() {
		return Objects.hash(account_name, associate, buddy_id, buddy_name, email, id, no_associates_assigned, phno,
				techstack);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buddy other = (Buddy) obj;
		return Objects.equals(account_name, other.account_name) && Objects.equals(associate, other.associate)
				&& buddy_id == other.buddy_id && Objects.equals(buddy_name, other.buddy_name)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& no_associates_assigned == other.no_associates_assigned && phno == other.phno
				&& Objects.equals(techstack, other.techstack);
	}
	@Override
	public String toString() {
		return "Buddy [id=" + id + ", buddy_id=" + buddy_id + ", buddy_name=" + buddy_name + ", account_name="
				+ account_name + ", email=" + email + ", phno=" + phno + ", techstack=" + techstack
				+ ", no_associates_assigned=" + no_associates_assigned + ", associate=" + associate + "]";
	}
	
	
	

}
