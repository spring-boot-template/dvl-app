package com.dvlcube.app.rest.vo;

import java.util.Comparator;

/**
 * Menu object.
 * 
 * @since 20 de fev de 2019
 * @author Ulisses Lima
 */
public class MenuVo implements Comparable<MenuVo> {
	private String title;
	private String description;
	private String image;
	private String link;
	private boolean insertable;
	private String path;

	public MenuVo() {
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isInsertable() {
		return insertable;
	}

	public void setInsertable(boolean insertable) {
		this.insertable = insertable;
	}

	public MenuVo(String title, String description, boolean insertable, String path) {
		this.title = title;
		this.description = description;
		this.insertable = insertable;
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public int compareTo(MenuVo o) {
		return Comparator.comparing(MenuVo::getTitle).compare(this, o);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuVo other = (MenuVo) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
