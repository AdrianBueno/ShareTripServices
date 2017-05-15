package com.sdi.client.infrastructure.exceptions;

/**
 * En esta clase se abarca las excepciones de aplicación, es decir
 * la lógica de la capa de aplicación.
 * @author sdi-39
 * @version 1
 *
 */
public class ApplicationException extends BusinessException {

	public ApplicationException(String string) {
		super(string);
	}

	private static final long serialVersionUID = 4529229976752162068L;

}
