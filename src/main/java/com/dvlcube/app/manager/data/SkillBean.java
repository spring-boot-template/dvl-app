package com.dvlcube.app.manager.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.dvlcube.utils.interfaces.MxBean;
import com.dvlcube.utils.interfaces.Nameable;
import com.dvlcube.utils.interfaces.Presentable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @since 3 de jun de 2019
 * @author Ulisses Lima
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class SkillBean implements Nameable, MxBean<Long>, Presentable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private String pic;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
}
