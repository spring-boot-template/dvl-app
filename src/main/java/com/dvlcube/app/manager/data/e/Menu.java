package com.dvlcube.app.manager.data.e;

import static com.dvlcube.utils.query.MxQuery.$;

/**
 * Tipos de menu. A ordem do enum define a ordem de exibição na tela.
 * 
 * @since 25 de mar de 2019
 * @author Ulisses Lima
 */
public enum Menu {
	MAIN, //
	CONFIGURATION, //
	STATS, //
	MONITORING, //
	MISC, //
	;
	private Menu() {
		this.label = $(name()).decapitalize().replace("_", " ").o;
	}

	private Menu(String label) {
		this.label = label;
	}

	public final String label;
}
