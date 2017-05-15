package com.sdi.client;

import com.sdi.client.actions.RemoteDeleteRatings;
import com.sdi.client.actions.RemoteListLastMonthRatings;
import com.sdi.client.actions.RemoteDisableUser;
import com.sdi.client.actions.RemoteHardReset;
import com.sdi.client.actions.RemoteListUsers;
import com.sdi.menu.BaseMenu;

public class Main extends BaseMenu {

	public Main() {
		menuOptions = new Object[][] {
				{ "Listar usuarios del sistema", RemoteListUsers.class },
				{ "Deshabilitar Usuario", RemoteDisableUser.class },
				{ "Ver puntuaciones del último mes", RemoteListLastMonthRatings.class },
				{ "Borrar comentario", RemoteDeleteRatings.class },
				{ "Hard reset", RemoteHardReset.class}};
	}

	public static void main(String[] args) {
		new Main().execute();
	}

}
