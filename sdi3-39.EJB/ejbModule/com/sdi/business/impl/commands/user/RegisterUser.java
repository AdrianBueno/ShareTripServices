package com.sdi.business.impl.commands.user;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.Jpa;
import com.sdi.persistence.finder.UserFinder;

/**
 * Se encarga de controlar la lógica de aplicación asociada
 * al registro de un usuario
 * @author sdi2-39
 * @version 3 19/06
 */
public class RegisterUser implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(RegisterUser.class);
	}
	private User user;

	public RegisterUser(User user){
		this.user = user;
		log.info(Msg.getStr("info.instanced"));
	}
	
	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(user);
		assertLoginNotExist(user.getLogin());
		assertEmailNotExist(user.getEmail());
		user.initialize();
		Jpa.getManager().persist(user);
		log.info("Se ha registrado un usuario con éxito");
		return user;
	}
	private void assertLoginNotExist(String login) throws BusinessException{
		if(UserFinder.getUserByLogin(login) != null)
			throw new BusinessException("assert.login_exist");
	}
	private void assertEmailNotExist(String email) throws BusinessException{
		if(UserFinder.getUserByEmail(email) != null)
			throw new BusinessException("assert.email_exist");
	}

}
