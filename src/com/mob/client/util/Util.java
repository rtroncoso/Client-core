/**
 * General methods. No place to put something? Get them over here
 * TODO : 
 * 	- Split into a Math class and give everything it's right place
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.util;

import com.badlogic.gdx.Gdx;

public class Util {

	public static boolean asciiValidos(String str) {
		// Function AsciiValidos(ByVal cad As String) As Boolean
		byte[] bytes = str.toLowerCase().getBytes();
		for (byte element : bytes) {
			if ((element < 97 || element > 122) && element != 255
					&& element != 32) {
				return false;
			}
		}
		return true;
	}

	public static short leShort(short n) {
		return (short) (((n & 0xff) << 8) | (((n & 0xff00) >> 8) & 0xff));
	}

	public static int leInt(int n) {
		int a = n & 0xff;
		int b = n & 0xff00;
		int c = n & 0xff0000;
		int d = n & 0xff000000;
		return ((a << 24) | (b << 16) | (c << 8) | d);
	}
	
	public static int leMix(short j, short k) {
		int a = j & 0xffff;
		int b = k & 0xffff0000;
		Gdx.app.log("Util", "a " + Integer.toBinaryString(a));
		Gdx.app.log("Util", "b " + Integer.toBinaryString(b));
		return (int) ((b << 16) | a) >> 31;
	}
	
	public static int byteArrayToInt(byte[] b) 
	{
	    int value = 0;
	    for (int i = 0; i < 4; i++) {
	        int shift = (4 - 1 - i) * 8;
	        value += (b[i] & 0x000000FF) << shift;
	    }
	    return value;
	}

	public static byte[] intToByteArray(int a)
	{
	    byte[] ret = new byte[4];
	    ret[0] = (byte) (a & 0xFF);   
	    ret[1] = (byte) ((a >> 8) & 0xFF);   
	    ret[2] = (byte) ((a >> 16) & 0xFF);   
	    ret[3] = (byte) ((a >> 24) & 0xFF);
	    return ret;
	}

	public static double distance(int x1, int y1, int x2, int y2) {
		// Encuentra la distancia entre dos puntos
		return Math.sqrt(((y1 - y2) * (y1 - y2)) + ((x1 - x2) * (x1 - x2)));
	}

	public static int porcentaje(long total, long porc) {
		return (int) Math.round((total * porc) / 100.0);
	}

	public static int Azar(int min, int max) {
		int valor = (int) ((Math.random() * (max - min + 1)) + min);
		return (valor < min) ? min : valor;
	}

	public static short Min(short a, short b) {
		return a < b ? a : b;
	}

	public static int Min(int a, int b) {
		return a < b ? a : b;
	}

	public static long Min(long a, long b) {
		return a < b ? a : b;
	}

	public static double Min(double a, double b) {
		return a < b ? a : b;
	}

	public static short Max(short a, short b) {
		return a > b ? a : b;
	}

	public static int Max(int a, int b) {
		return a > b ? a : b;
	}

	public static long Max(long a, long b) {
		return a > b ? a : b;
	}

	public static double Max(double a, double b) {
		return a > b ? a : b;
	}
}
