package com.dvlcube.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.dvlcube.utils.BaseEntity;
import com.dvlcube.utils.ex.AuthProvider;
import com.dvlcube.utils.interfaces.MxBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

	@UpdateTimestamp
	@CreationTimestamp
	private Date lastActivity;

	private Integer requests;

	@OneToMany(mappedBy = "owner")
	private List<Bag> bags;

	@OneToMany(mappedBy = "owner")
	private List<BagHero> bagHeroes;

	@OneToMany(mappedBy = "owner")
	private List<Fusion> fusions;

	/**
	 * @since 5 de jun de 2019
	 * @author Ulisses Lima
	 */
	public void incRequests() {
		this.requests++;
	}
}