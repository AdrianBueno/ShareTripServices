package com.sdi.business.impl.commands.user;

import java.util.List;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.finder.UserFinder;
/**
 * Esta clase se encarga de controlar la lógica de aplicación encargada
 * de proveer una lista con los usuarios de la base de datos.
 * @author sdi-39
 * @version 1 16/06
 */
public class ListUsers implements Command {

	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(ListUsers.class);
	}

	public ListUsers() {
		log.info(Msg.getStr("info.instanced"));
	}

	@Override
	public Object execute() throws BusinessException {
		List<User> list =  UserFinder.listUsers();
		if(list != null)
			log.info("Número de usuarios: "+list.size());
		else
			log.info("No hay usuarios que listar");
		return list;
	}

}
