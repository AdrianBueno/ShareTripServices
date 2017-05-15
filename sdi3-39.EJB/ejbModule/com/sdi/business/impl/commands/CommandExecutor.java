package com.sdi.business.impl.commands;

import javax.persistence.PersistenceException;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.infrastructure.exception.BusinessException;

/**
 * Esta clase es parte del patrón Command que hemos integrado para el uso con
 * JPA.
 * @author sdi3-39
 * @version 3 21/06 Adaptado a la práctica 3, sin transacciones manuales.
 */
public class CommandExecutor {
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(CommandExecutor.class);
	}
	private CommandExecutor() {
	}
	public static Object execute(Command command) throws BusinessException {
		try {
			//trx = em.getTransaction();
			//trx.begin();
			Object ret = command.execute();
			//trx.commit();
			return ret;
		} catch (PersistenceException | BusinessException pex) {
			log.error(pex.getMessage());
			throw pex;
		}

	}

}
