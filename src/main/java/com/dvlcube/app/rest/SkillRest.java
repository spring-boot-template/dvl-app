package com.dvlcube.app.rest;

import static com.dvlcube.app.manager.data.e.Menu.CONFIGURATION;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.dvlcube.app.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dvlcube.app.interfaces.MenuItem;
import com.dvlcube.app.manager.data.SkillBean;
import com.dvlcube.app.manager.data.vo.MxRestResponse;
import com.dvlcube.utils.interfaces.rest.MxFilterableBeanService;

/**
 * @since 4 de jun de 2019
 * @author Ulisses Lima
 */
@RestController
@MenuItem(value = CONFIGURATION)
@RequestMapping("${dvl.rest.prefix}/skills")
public class SkillRest implements MxFilterableBeanService<SkillBean, Long> {
	@Autowired
	private SkillService skillService;

	@Override
	@GetMapping
	public Iterable<SkillBean> get(@RequestParam Map<String, String> params) {
		return skillService.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public Optional<SkillBean> get(@PathVariable Long id) {
		return skillService.findById(id);
	}

	@Override
	@PostMapping
	public MxRestResponse post(@Valid @RequestBody SkillBean body) {
		SkillBean save = skillService.save(body);
		return GenericRestResponse.ok(save.getId());
	}

	/**
	 * @param params
	 * @return List<SkillBean>
	 * @since 18 de abr de 2019
	 * @author Ulisses Lima
	 */
	@GetMapping("/filtered")
	public List<SkillBean> getFiltered(@RequestParam Map<String, String> params) {
		return this.skillService.findAllBy(params);
	}

	/**
	 * @param group
	 * @param params
	 * @return List<SkillBean>
	 * @since 18 de abr de 2019
	 * @author Ulisses Lima
	 */
	@GetMapping("/group/{group}/filtered")
	public List<SkillBean> getGroupFiltered(@PathVariable String group, @RequestParam Map<String, String> params) {
		return skillService.findAllBy(params, group);
	}

	@GetMapping("/like")
	public Iterable<SkillBean> getLike(@RequestParam(required = true) String name) {
		return skillService.findAllByNameLike(name);
	}

	@GetMapping("/name/{name}")
	public Optional<SkillBean> findByName(@PathVariable String name) {
		return skillService.findByName(name);
	}

	@GetMapping("exists/name/{name}")
	public Boolean existsByName(@PathVariable String name) {
		return skillService.existsByName(name);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.skillService.deleteById(id);
	}
}
