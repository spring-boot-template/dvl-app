package com.dvlcube.utils;

import java.util.Date;

/**
 * Material design utils.
 * 
 * @since 16 de abr de 2019
 * @author Ulisses Lima
 */
public class UiUtils {
	public static final int INTERVAL = 5;

	/**
	 * based on the 'signal n$n' icon
	 * 
	 * @param start
	 * @param end
	 * @return bar level
	 * @since 16 de abr de 2019
	 * @author Ulisses Lima
	 */
	public static int signal(Date reference) {
		if (reference == null)
			return 0;

		long now = System.currentTimeMillis();
		long ref = reference.getTime();
		long minutes = (now - ref) / 1000 / 60;

		if (minutes <= INTERVAL)
			return 4;

		if (minutes <= Math.pow(INTERVAL, 2))
			return 3;

		if (minutes <= Math.pow(INTERVAL, 3))
			return 2;

		if (minutes <= Math.pow(INTERVAL, 4))
			return 1;

		return 0;
	}

	/**
	 * @param os
	 * @return OS icon
	 * @since 7 de mai de 2019
	 * @author Ulisses Lima
	 */
	public static String osIcon(String os) {
		if (os == null)
			return "linux";

		if (os.toLowerCase().contains("win"))
			return "win";

		if (os.toLowerCase().contains("linux"))
			return "linux";

		return "apple";
	}
}