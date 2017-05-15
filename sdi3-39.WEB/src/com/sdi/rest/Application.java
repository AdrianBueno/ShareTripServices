package com.sdi.rest;

import java.util.HashSet;
import java.util.Set;

import com.sdi.rest.impl.AdminServiceRestImpl;
import com.sdi.rest.impl.AppliesServiceRestImpl;
import com.sdi.rest.impl.RatingsServiceRestImpl;
import com.sdi.rest.impl.SeatsServiceRestImpl;
import com.sdi.rest.impl.TripsServiceRestImpl;
import com.sdi.rest.impl.UsersServiceRestImpl;
/**
 * @author sdi3-39
 * @version 1 27/06
 */
public class Application extends javax.ws.rs.core.Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> res = new HashSet<>();
		res.add(TripsServiceRestImpl.class);
		res.add(UsersServiceRestImpl.class);
		res.add(SeatsServiceRestImpl.class);
		res.add(AppliesServiceRestImpl.class);
		res.add(RatingsServiceRestImpl.class);
		res.add(AdminServiceRestImpl.class);
		return res;
	}
}
