package com.sdi.client;

import com.sdi.client.actions.DeleteRatings;
import com.sdi.client.actions.DisableUser;
import com.sdi.client.actions.HardReset;
import com.sdi.client.actions.ListLastMonthRatings;
import com.sdi.client.actions.ListUsers;
import com.sdi.menu.BaseMenu;

public class Main extends BaseMenu {

	public Main() {
		menuOptions = new Object[][] {
				{ "Listar usuarios del sistema", ListUsers.class },
				{ "Deshabilitar Usuario", DisableUser.class },
				{ "Ver puntuaciones del último mes", ListLastMonthRatings.class },
				{ "Borrar comentario", DeleteRatings.class },
				{ "Hard reset", HardReset.class}};
	}

	public static void main(String[] args) {
		new Main().execute();
	}

}
