package com.dvlcube.app.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvlcube.app.Enum.Menu;
import com.dvlcube.app.interfaces.MenuItem;
import com.dvlcube.app.interfaces.MxService;
import com.dvlcube.app.rest.vo.MenuGroupVo;
import com.dvlcube.app.rest.vo.MenuVo;

/**
 * @since 8 de fev de 2019
 * @author Ulisses Lima
 */
@RestController
@RequestMapping("${dvl.rest.prefix}/menus")
public class MenuService implements MxService {
	private Logger log = LogManager.getLogger(this.getClass());

	public static final String REST_PREFIX_VAR = "${dvl.rest.prefix}/";

	/**
	 * @since 14 de fev de 2019
	 * @author Ulisses Lima
	 */
	@GetMapping
	public List<MenuGroupVo> get() {
		final Map<Menu, MenuGroupVo> map = new HashMap<>();
		final List<MenuGroupVo> menuGroups = new ArrayList<>();

		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(MenuItem.class));

		scanner.findCandidateComponents(this.getClass().getName()).forEach(definition -> {
			String[] strings = definition.getBeanClassName().split("\\.");
			String bean = strings[strings.length - 1];

			String simpleName = bean.replace("Service", "");
			String path = "";
			boolean readOnly = false;
			Menu group = Menu.MISC;
			try {
				Class<?> c = Class.forName(definition.getBeanClassName());
				MenuItem menuDefinition = c.getAnnotation(MenuItem.class);
				RequestMapping mapping = c.getAnnotation(RequestMapping.class);

				if (menuDefinition != null) {
					group = menuDefinition.value();
					readOnly = menuDefinition.readOnly();
				}

				if (mapping != null) {
					path = mapping.value()[0].replace(REST_PREFIX_VAR, "");
				}
			} catch (ClassNotFoundException e) {
				log.error("creating menu", e);
			}

			MenuGroupVo menuGroup = map.get(group);
			if (menuGroup == null) {
				menuGroup = new MenuGroupVo(group);
				map.put(group, menuGroup);
				menuGroups.add(menuGroup);
			}

			menuGroup.addMenu(new MenuVo(simpleName + (readOnly ? " View" : " Management"),
					(readOnly ? "See " : "Manage ") + simpleName + " definitions.", !readOnly, path));
		});

		menuGroups.sort(Comparator.comparing(MenuGroupVo::getCategory));
		return menuGroups;
	}
}
