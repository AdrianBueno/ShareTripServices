package com.sdi.infrastructure.util.console;

import java.io.PrintStream;

/**
 * Métodos de utilidad para escribir cosas en pantalla de forma controlada.
 * Aqu� irian todas las decoraciones pertinentes
 * 
 * @author alb
 */
public class Printer {
	private static PrintStream con = System.out;
	
	public static void printHeading(String string) {
		con.println(string);
	}

	/**
	 * Avisa de error l�gico en la ejecuci�n, muy probablemente por 
	 * equivocacion del usuario o por circunstancias que han cambiado 
	 * durante el "think time" del usuario (control optimista y eso...)
	 * 
	 * @param e
	 */
	public static void printBusinessException(Exception e) {
		
		con.println("Ha ocurrido un problema procesando su opcion:");
		con.println("\t- " + e.getLocalizedMessage());
	}

	/**
	 * Avisa de error irrecuperable en la interfaz del usuario
	 * @param string
	 * @param e
	 */
	public static void printRuntimeException(RuntimeException e) {
		con.println("Ha ocurrido un error interno no recuperable, " +
				"el programa debe terminar.\n" +
				"A continuación se muestra una traza del error.\n" +
				"[la traza no sería visible por el usuario en una alicación final]");
		
	}

	public static void print(String msg) {
		con.print(msg);
	}
	
	public static void println(String msg) {
		con.println(msg);
	}
	

	public static void printException(String string, Exception e) {
		con.println(string);
		con.println("\t- " + e.getLocalizedMessage());
	}
	
	
}
