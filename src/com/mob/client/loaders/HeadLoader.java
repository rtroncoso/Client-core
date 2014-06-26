package com.mob.client.loaders;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.mob.client.data.HeadData;
import com.mob.client.interfaces.Loadable;
import com.mob.client.util.Util;

public class HeadLoader extends Loader implements Loadable<HeadData> {

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
	public Vector<HeadData> load(String initFileName) {
		Vector<HeadData> heads = new Vector<HeadData>();
		this.mFileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		int numHeads = 0;
		
		DataInputStream file = new DataInputStream(mFileHandle.read());
		
		try {
			file.skipBytes(GAME_FILE_HEADER_SIZE);
			numHeads = Util.leShort(file.readShort());
			heads.setSize(numHeads + 1);
			
			for(int i = 1; i <= numHeads; i++) {
				int headIndex[] = new int[4];
				
				headIndex[Heading.NORTH.toInt()] = Util.leShort(file.readShort());
				headIndex[Heading.EAST.toInt()] = Util.leShort(file.readShort());
				headIndex[Heading.SOUTH.toInt()] = Util.leShort(file.readShort());
				headIndex[Heading.WEST.toInt()] = Util.leShort(file.readShort());

				heads.setElementAt(new HeadData(headIndex), i);
			}
			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return heads;
			
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
