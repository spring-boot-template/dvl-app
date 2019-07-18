package com.dvlcube.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dvlcube.app.rest.vo.SystemVo;
import com.dvlcube.utils.interfaces.MxService;

/**
 * @since 8 de fev de 2019
 * @author Ulisses Lima
 */
@RestController
@RequestMapping("env")
public class EnvService implements MxService {
	@Value("${dvl.project.version}")
	public String version;

	@Autowired
	private Environment env;

	/**
	 * @param var
	 * @return var value
	 * @since 14 de fev de 2019
	 * @author Ulisses Lima
	 */
	@GetMapping
	public String get(@RequestParam("var") String var) {
		return env.getProperty(var);
	}

	/**
	 * @return current version
	 * @since 9 de abr de 2019
	 * @author Ulisses Lima
	 */
	@GetMapping("/version")
	public String getVersion() {
		return version;
	}

	/**
	 * @return current version
	 * @since 9 de abr de 2019
	 * @author Ulisses Lima
	 */
	@GetMapping("/info")
	public SystemVo getInfo() {
		SystemVo vo = new SystemVo();
		vo.setVersion(version);
		return vo;
	}
}
