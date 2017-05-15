package com.sdi.business.impl.commands;

import com.sdi.infrastructure.exception.BusinessException;

public interface Command {
	public Object execute() throws BusinessException;
}
