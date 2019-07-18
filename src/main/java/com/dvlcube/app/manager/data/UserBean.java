package com.dvlcube.app.manager.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import com.dvlcube.utils.ex.AuthProvider;
import com.dvlcube.utils.interfaces.MxBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @see AuthProvider
 * @since 23 de mai de 2019
 * @author Ulisses Lima
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class UserBean implements MxBean<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * @since 5 de jun de 2019
	 */
	public UserBean() {
	}

	/**
	 * @param email
	 * @since 5 de jun de 2019
	 */
	public UserBean(@Email String email) {
		this.email = email;
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Email
	@Column(nullable = false)
	private String email;

	private String pic;

	@Column(nullable = false)
	private Boolean verified = false;

	@JsonIgnore
	private String password;

	private Date lastActivity = new Date();

	private int requests;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}

	/**
	 * @since 5 de jun de 2019
	 * @author Ulisses Lima
	 */
	public void incRequests() {
		this.requests++;
	}
}