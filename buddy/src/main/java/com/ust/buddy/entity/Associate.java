package com.ust.buddy.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tp_associate", uniqueConstraints = { @UniqueConstraint(columnNames = { "associate_id" }) })
public class Associate {

	/*
	public Associate(String id, String associate_id, String first_name, String last_name, String prev_account_name,
			Date program_start_date, Date end_date, String status, String techstack, String email_id, long phoneno,
			Buddy buddy) {
		super();
		this.id = id;
		this.associate_id = associate_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.prev_account_name = prev_account_name;
		this.program_start_date = program_start_date;
		this.end_date = end_date;
		this.status = status;
		this.techstack = techstack;
		this.email_id = email_id;
		this.phoneno = phoneno;
		this.buddy = buddy;
	}
	*/

	@Id
	private String id;
	@Column(unique = true)
	private String associate_id;
	private String first_name;
	private String last_name;
	private String prev_account_name;
	private Date program_start_date;
	private Date end_date;
	private String status;
	private String techstack;
	private String email_id;
	private long phoneno;

	@ManyToOne(cascade = CascadeType.ALL) // many associates one buddy
	private Buddy buddy;

	public Buddy getBuddy() {
		return buddy;
	}

	public void setBuddy(Buddy buddy) {
		this.buddy = buddy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssociate_id() {
		return associate_id;
	}

	public void setAssociate_id(String associate_id) {
		this.associate_id = associate_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPrev_account_name() {
		return prev_account_name;
	}

	public void setPrev_account_name(String prev_account_name) {
		this.prev_account_name = prev_account_name;
	}

	public Date getProgram_start_date() {
		return program_start_date;
	}

	public void setProgram_start_date(Date program_start_date) {
		this.program_start_date = program_start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTechstack() {
		return techstack;
	}

	public void setTechstack(String techstack) {
		this.techstack = techstack;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public long getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(associate_id, buddy, email_id, end_date, first_name, id, last_name, phoneno,
				prev_account_name, program_start_date, status, techstack);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Associate other = (Associate) obj;
		return associate_id == other.associate_id && Objects.equals(buddy, other.buddy)
				&& Objects.equals(email_id, other.email_id) && Objects.equals(end_date, other.end_date)
				&& Objects.equals(first_name, other.first_name) && Objects.equals(id, other.id)
				&& Objects.equals(last_name, other.last_name) && phoneno == other.phoneno
				&& Objects.equals(prev_account_name, other.prev_account_name)
				&& Objects.equals(program_start_date, other.program_start_date) && Objects.equals(status, other.status)
				&& Objects.equals(techstack, other.techstack);
	}

	@Override
	public String toString() {
		return "Associate [id=" + id + ", associate_id=" + associate_id + ", first_name=" + first_name + ", last_name="
				+ last_name + ", prev_account_name=" + prev_account_name + ", program_start_date=" + program_start_date
				+ ", end_date=" + end_date + ", status=" + status + ", techstack=" + techstack + ", email_id="
				+ email_id + ", phoneno=" + phoneno + ", buddy=" + buddy + "]";
	}

}
