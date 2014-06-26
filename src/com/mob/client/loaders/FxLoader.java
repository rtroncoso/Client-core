package com.mob.client.loaders;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.mob.client.data.FxData;
import com.mob.client.interfaces.Loadable;
import com.mob.client.util.Util;

public class FxLoader extends Loader implements Loadable<FxData> {


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
	public Vector<FxData> load(String initFileName) {
		Vector<FxData> fxs = new Vector<FxData>();
		this.mFileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		int numFxs = 0;
		
		DataInputStream file = new DataInputStream(mFileHandle.read());
		
		try {
			file.skipBytes(GAME_FILE_HEADER_SIZE);
			numFxs = Util.leShort(file.readShort());
			fxs.setSize(numFxs + 1);
			
			for(int i = 1; i <= numFxs; i++) {
				int offsetX = 0, offsetY = 0, fxIndex = 0;
				
				fxIndex = Util.leShort(file.readShort());
				offsetX = Util.leShort(file.readShort());
				offsetY = Util.leShort(file.readShort());
				
				fxs.setElementAt(new FxData(fxIndex, offsetX, offsetY), i);
			}
			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return fxs;
			
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
