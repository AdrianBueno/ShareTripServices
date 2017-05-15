package com.sdi.presentation.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;
import org.primefaces.component.password.Password;

import com.sdi.infrastructure.io.Msg;


@FacesValidator("vPasswordEqual")
public class EqualPasswordValidator implements Validator {
	private static final Logger log;
	
	static{
		log = Log4jLogger.getLogger(EqualPasswordValidator.class);
	}
	
	private FacesMessage msg;

	
	@Override
	public void validate(FacesContext fc, UIComponent comp, Object value)
			throws ValidatorException {
		
		log.info("Validando que las contraseñas sean iguales");
		String password1 = (String) value;
		Password pass = (Password)comp.findComponent("registerpass1");
		String password2 = (String) pass.getValue();
		
		if(password1 != null && password2 != null && password1.equals(password2))
			log.info("Las contraseñas tienen valores válidos");
		else{
			msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setDetail(Msg.getMessage("msgs","assert.equal_password" ));
			throw new ValidatorException(msg);
		}
	}
	
	
	

}
