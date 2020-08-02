package com.dvlcube.app.rest.vo;

import java.util.Set;
import java.util.TreeSet;

import com.dvlcube.app.manager.data.e.Menu;

/**
 * Menu group object.
 * 
 * @since 18 de abr de 2019
 * @author Ulisses Lima
 */
public class MenuGroupVo extends MenuVo {
	private Menu category;
	private Set<MenuVo> menus = new TreeSet<>();

	public MenuGroupVo(Menu category) {
		this.category = category;
	}

	public Menu getCategory() {
		return category;
	}

	public void setCategory(Menu category) {
		this.category = category;
	}

	public Set<MenuVo> getMenus() {
		return menus;
	}

	public void setMenus(Set<MenuVo> menus) {
		this.menus = menus;
	}

	public void addMenu(MenuVo menu) {
		menus.add(menu);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
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
		MenuGroupVo other = (MenuGroupVo) obj;
		if (category != other.category)
			return false;
		return true;
	}
}
