/**
 * Loads Argentum Online bodys
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-06-26
 */
package com.mob.client.loaders;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.mob.client.data.BodyData;
import com.mob.client.interfaces.Loadable;
import com.mob.client.util.Util;

public class BodyLoader extends Loader implements Loadable<BodyData> {


	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================


	// ===========================================================
	// Constructors
	// ===========================================================


	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public Vector<BodyData> load(String initFileName) {
		Vector<BodyData> cuerpos = new Vector<BodyData>();
		this.mFileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		int numCuerpos = 0;
		
		DataInputStream file = new DataInputStream(mFileHandle.read());
		
		try {
			file.skipBytes(GAME_FILE_HEADER_SIZE);
			numCuerpos = Util.leShort(file.readShort());
			cuerpos.setSize(numCuerpos + 1);
			
			for(int i = 1; i <= numCuerpos; i++) {
				int grhArray[] = new int[4], headOffSetX = 0, headOffSetY = 0;;
				
				grhArray[Heading.NORTH.toInt()] = Util.leShort(file.readShort());
				grhArray[Heading.EAST.toInt()] = Util.leShort(file.readShort());
				grhArray[Heading.SOUTH.toInt()] = Util.leShort(file.readShort());
				grhArray[Heading.WEST.toInt()] = Util.leShort(file.readShort());
				
				headOffSetX = Util.leShort(file.readShort());
				headOffSetY = Util.leShort(file.readShort());
				
				cuerpos.setElementAt(new BodyData(grhArray, headOffSetX, headOffSetY), i);
			}
			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return cuerpos;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
