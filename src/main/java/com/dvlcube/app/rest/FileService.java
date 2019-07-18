package com.dvlcube.app.rest;

import static com.dvlcube.utils.query.MxQuery.$;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dvlcube.utils.WebFileUtils;
import com.dvlcube.utils.interfaces.MxService;

/**
 * @since 30 de mai de 2019
 * @author Ulisses Lima
 */
@RestController
@RequestMapping("${dvl.rest.prefix}/files")
public class FileService implements MxService {
	protected Logger log = LogManager.getLogger(this.getClass());

	/**
	 * @param path
	 * @return file
	 * @since 30 de mai de 2019
	 * @author Ulisses Lima
	 * @throws IOException
	 */
	@GetMapping("download")
	public ResponseEntity<InputStreamResource> getDownload(@RequestParam String path,
			@RequestParam(required = false) Boolean zip) throws IOException {
		if ($(path).isBlank())
			throw new IllegalArgumentException("path param must not be blank");

		File file = new File(path);
		if (!file.isFile())
			throw new IllegalArgumentException("not a file: " + path);

		if (zip == null)
			zip = false;

		return WebFileUtils.resourceResponse(file, zip);
	}
}
