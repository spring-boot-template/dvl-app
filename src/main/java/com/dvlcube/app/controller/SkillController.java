package com.dvlcube.app.controller;

import static com.dvlcube.app.manager.data.e.Menu.CONFIGURATION;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.dvlcube.app.dto.SkillDTO;
import com.dvlcube.app.dto.filter.SkillFilterDTO;
import com.dvlcube.app.mapper.SkillMapper;
import com.dvlcube.app.rest.GenericRestResponse;
import com.dvlcube.app.service.SkillService;
import com.dvlcube.utils.GenericMapper;
import com.dvlcube.utils.GenericService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dvlcube.app.interfaces.MenuItem;
import com.dvlcube.app.model.Skill;
import com.dvlcube.app.manager.data.vo.MxRestResponse;
import com.dvlcube.utils.interfaces.rest.MxFilterableBeanService;

/**
 * @since 4 de jun de 2019
 * @author Ulisses Lima
 */
@RestController
@MenuItem(value = CONFIGURATION)
@RequestMapping("${dvl.rest.prefix}/skills")
public class SkillController implements MxFilterableBeanService<SkillDTO, Long>, ListRest<Skill, SkillMapper, SkillFilterDTO, SkillDTO, Long> {

	private SkillService service;

	@Override
	public GenericMapper<Skill, SkillDTO> getMapper() {
		return new SkillMapper();
	}

	@Override
	public SkillMapper getFilterMapper() {
		return new SkillMapper();
	}

	@Override
	public GenericService<Skill, Long> getService() {
		return service;
	}

	@Autowired
	public SkillController(SkillService service) {
		this.service = service;
	}

	@Override
	@GetMapping
	public Iterable<SkillDTO> get(@RequestParam Map<String, String> params) {
		return service.firstPage();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<SkillDTO> get(@PathVariable Long id) {
		return service.findById(id);
	}

	@Override
	@PostMapping
	public MxRestResponse post(@Valid @RequestBody SkillDTO body) {
		ResponseEntity<SkillDTO> save = service.add(body);
		return GenericRestResponse.ok(save.getBody().getId());
	}

	/**
	 * @param params
	 * @return List<Skill>
	 * @since 18 de abr de 2019
	 * @author Ulisses Lima
	 */
	@GetMapping("/filtered")
	public ResponseEntity<List<SkillDTO>> getFiltered(@RequestParam Map<String, String> params) {
		return service.findAllBy(params);
	}

	/**
	 * @param group
	 * @param params
	 * @return List<Skill>
	 * @since 18 de abr de 2019
	 * @author Ulisses Lima
	 */
	@GetMapping("/group/{group}/filtered")
	public ResponseEntity<List<SkillDTO>> getGroupFiltered(@PathVariable String group, @RequestParam Map<String, String> params) {
		return service.findAllBy(params, group);
	}

	@GetMapping("/like")
	public ResponseEntity<Iterable<SkillDTO>> getLike(@RequestParam String id) {
		return service.findAllLike(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@GetMapping("/name/{name}")
	@ApiOperation("Find Skill with name")
	public ResponseEntity findByName(@ApiParam(value = "name", required = true) @PathVariable String name){
		return service.findByName(name);
	}

	@GetMapping("/exists/name/{name}")
	@ApiOperation("Find Skill with name and return Boolean")
	public ResponseEntity findByNameAndReturnBoolean(@ApiParam(value = "name", required = true) @PathVariable String name){
		return service.findByNameReturnBool(name);
	}
}
