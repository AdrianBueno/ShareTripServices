package com.sdi.business.impl.commands.user;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.finder.UserFinder;

/**
 * Se encarga de controlar la lógica de aplicación
 * en el inicio de sesión de un usuario
 * @author sdi2-39
 * @version 3 24/06
 */
public class LoginUser implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(LoginUser.class);
	}
	private String login;
	private String pass;
	private User user;
	
	public LoginUser(String login, String pass){
		this.login = login;
		this.pass = pass;
		log.debug(Msg.getStr("info.instanced_values")+ " "+login+", "+pass);
		log.info(Msg.getStr("info.instanced"));
	}
	
	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(login);
		ApplicationAsserts.assertValueNotNull(pass);
		user = UserFinder.getUserByLogin(login);
		ApplicationAsserts.assertEntityExist(user);
		user.login(pass); //Excepciones inactivo o contraseña inválida.
		log.info("Inicio de sesión válido");
		return user;
	}
}
