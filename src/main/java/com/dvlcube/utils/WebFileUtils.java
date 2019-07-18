package com.dvlcube.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.dvlcube.utils.ex.Zip;

/**
 * @since 30 de mai de 2019
 * @author Ulisses Lima
 */
public class WebFileUtils {
	/**
	 * @param file
	 * @param zip  zip file before returning
	 * @return resource
	 * @since 30 de mai de 2019
	 * @author Ulisses Lima
	 * @throws IOException
	 */
	public static InputStreamResource resource(File file, boolean zip) throws IOException {
		if (zip) {
			return new InputStreamResource(new ByteArrayInputStream(new Zip(file).bytes()));
		} else {
			return new InputStreamResource(new FileInputStream(file));
		}
	}

	/**
	 * @param file
	 * @return resource
	 * @since 30 de mai de 2019
	 * @author Ulisses Lima
	 * @throws IOException
	 */
	public static InputStreamResource resource(File file) throws IOException {
		return resource(file, false);
	}

	/**
	 * @param file
	 * @return resource
	 * @since 30 de mai de 2019
	 * @author Ulisses Lima
	 * @throws IOException
	 */
	public static ResponseEntity<InputStreamResource> webResource(File file) throws IOException {
		return resourceResponse(file, false);
	}

	/**
	 * @param file
	 * @param zip
	 * @return
	 * @since 30 de mai de 2019
	 * @author Ulisses Lima
	 * @throws IOException
	 */
	public static ResponseEntity<InputStreamResource> resourceResponse(File file, boolean zip) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

		// TODO verificar por que como ZIP não está funcionando
		InputStreamResource resource = resource(file, zip);
		return ResponseEntity.ok() //
				.headers(headers) //
//				.contentLength(resource.contentLength()) // illegal state. and incorrect length truncates file
				.contentType(MediaType.parseMediaType("application/octet-stream")) //
				.body(resource);
	}
}
