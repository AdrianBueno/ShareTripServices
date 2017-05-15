package com.sdi.business.impl.commands.user;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.User;
import com.sdi.persistence.Jpa;
/**
 * Esta clase se encarga de controlar la lógicad e aplicación
 * que maneja las acciones entre el modelo y la base de datos.
 * Desactiva un usuario previamente activo
 * @author nardi
 *
 */
public class DisableUser implements Command {

	private Long userId;

	public DisableUser(Long userId){
		this.userId = userId;
	}
	
	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(userId);
		User user = Jpa.getManager().find(User.class, userId);
		ApplicationAsserts.assertEntityExist(user);
		user.disableUser();
		Jpa.getManager().merge(user);
		return user;
	}

}
