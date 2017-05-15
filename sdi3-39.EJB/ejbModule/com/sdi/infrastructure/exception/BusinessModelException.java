package com.sdi.infrastructure.exception;

/**
 * Aquí manejo las excepciones de lógica de Negocio (que no de aplicación)
 * Solo son lanzadas desde el modelo.
 * @author sdi-39
 * @version 1
 */
public class BusinessModelException extends BusinessException {

	private static final long serialVersionUID = 7074917369376642984L;

	public BusinessModelException(String str) {
		super(str);
	}
}
