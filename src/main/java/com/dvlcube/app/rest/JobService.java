package com.dvlcube.app.rest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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
import com.dvlcube.app.jpa.repo.JobRepository;
import com.dvlcube.app.manager.data.JobBean;
import com.dvlcube.app.manager.data.e.Menu;
import com.dvlcube.app.manager.data.vo.MxRestResponse;
import com.dvlcube.utils.interfaces.rest.MxFilterableBeanService;

/**
 * @since 4 de jun de 2019
 * @author Ulisses Lima
 */
@RestController
@MenuItem(value = Menu.JOBS)
@RequestMapping("${dvl.rest.prefix}/jobs")
public class JobService implements MxFilterableBeanService<JobBean, Long> {

	@Autowired
	private JobRepository repo;

	@Override
	@GetMapping
	public Iterable<JobBean> get(@RequestParam Map<String, String> params) {
		return repo.firstPage();
	}

	@Override
	@GetMapping("/{id}")
	public Optional<JobBean> get(@PathVariable Long id) {
		return repo.findById(id);
	}

	@Override
	@PostMapping
	public MxRestResponse post(@Valid @RequestBody JobBean body) {
		JobBean save = repo.save(body);
		return GenericRestResponse.ok(save.getId());
	}

	/**
	 * @param params
	 * @return List<JobBean>
	 * @since 18 de abr de 2019
	 * @author Ulisses Lima
	 */
	@GetMapping("/filtered")
	public List<JobBean> getFiltered(@RequestParam Map<String, String> params) {
		return repo.findAllBy(params);
	}

	/**
	 * @param group
	 * @param params
	 * @return List<JobBean>
	 * @since 18 de abr de 2019
	 * @author Ulisses Lima
	 */
	@GetMapping("/group/{group}/filtered")
	public List<JobBean> getGroupFiltered(@PathVariable String group, @RequestParam Map<String, String> params) {
		return repo.findAllBy(params, group);
	}

	@GetMapping("/like")
	public Iterable<JobBean> getLike(@RequestParam(required = true) String id) {
		return repo.findAllLike(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}

}
