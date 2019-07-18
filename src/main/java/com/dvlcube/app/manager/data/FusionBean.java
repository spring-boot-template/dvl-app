package com.dvlcube.app.manager.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.dvlcube.utils.interfaces.MxBean;
import com.dvlcube.utils.interfaces.Nameable;
import com.dvlcube.utils.interfaces.Owned;
import com.dvlcube.utils.interfaces.Presentable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @since 3 de jun de 2019
 * @author Ulisses Lima
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "owner_id" }) })
public class FusionBean implements MxBean<Long>, Nameable, Presentable, Owned {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private UserBean owner;
	private String name;

	@OneToMany
	private List<BagHeroBean> requirementList;
	private boolean share;

	private String url;
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

	public List<BagHeroBean> getRequirementList() {
		return requirementList;
	}

	public void setRequirementList(List<BagHeroBean> requirementList) {
		this.requirementList = requirementList;
	}

	public UserBean getOwner() {
		return owner;
	}

	public void setOwner(UserBean owner) {
		this.owner = owner;
	}

	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}
}
