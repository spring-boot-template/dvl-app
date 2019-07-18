package com.dvlcube.app.manager.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class HeroBean implements MxBean<Long>, Nameable, Presentable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int stars;

	@ManyToMany
	private List<SkillBean> skillList;
	private String url;
	private String pic;

	@ManyToOne
	private FactionBean faction;

	@ManyToOne
	private JobBean job;

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

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public List<SkillBean> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<SkillBean> skillList) {
		this.skillList = skillList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public FactionBean getFaction() {
		return faction;
	}

	public void setFaction(FactionBean faction) {
		this.faction = faction;
	}

	public JobBean getJob() {
		return job;
	}

	public void setJob(JobBean job) {
		this.job = job;
	}
}
