package com.dvlcube.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import com.dvlcube.utils.BaseEntity;
import com.dvlcube.utils.ex.AuthProvider;
import com.dvlcube.utils.interfaces.MxBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * @see AuthProvider
 * @since 23 de mai de 2019
 * @author Ulisses Lima
 */
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "userbean",uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class User implements MxBean<Long>, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * @since 5 de jun de 2019
	 */
	public User() {
	}

	/**
	 * @param email
	 * @since 5 de jun de 2019
	 */
	public User(@Email String email) {
		this.email = email;
	}

	@Id
	@GeneratedValue
	private Long id;

	@Email
	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String name;

	private String pic;

	@Column(nullable = false)
	private Boolean verified = false;

	@JsonIgnore
	private String password;

	private Date lastActivity;

	private Integer requests;

	/**
	 * @since 5 de jun de 2019
	 * @author Ulisses Lima
	 */
	public void incRequests() {
		this.requests++;
	}
}