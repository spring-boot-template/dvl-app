package com.dvlcube.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.dvlcube.utils.BaseEntity;
import com.dvlcube.utils.interfaces.MxBean;
import com.dvlcube.utils.interfaces.Nameable;
import com.dvlcube.utils.interfaces.Presentable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * @since 3 de jun de 2019
 * @author Ulisses Lima
 */
@Getter
@Setter
@Builder
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "herobean",uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class Hero implements MxBean<Long>, Nameable, Presentable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String pic;
	private Integer stars;
	private String url;

	@ManyToOne
	private Faction faction;
	@ManyToOne
	private Job job;

	@ManyToMany
	private List<Skill> skillList;

}
