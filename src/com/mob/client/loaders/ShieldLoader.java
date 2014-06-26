package com.mob.client.loaders;

import java.io.IOException;
import java.util.Vector;

import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import com.badlogic.gdx.Gdx;
import com.mob.client.data.ShieldData;
import com.mob.client.interfaces.Loadable;

public class ShieldLoader extends Loader implements Loadable<ShieldData> {

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
	public Vector<ShieldData> load(String initFileName) {
		Vector<ShieldData> shields = new Vector<ShieldData>();
		Ini iniFile = new Ini();
		Config c = new Config();
		c.setLowerCaseSection(true);
		iniFile.setConfig(c);
		
		try {
			iniFile.load(Gdx.files.internal(GAME_INIT_PATH + initFileName).file());
			
			int numShields = Integer.parseInt(iniFile.get("init", "NumEscudos"));
			shields.setSize(numShields + 1);
			
			for(int i = 1; i <= numShields; i++) {
				int[] shieldIndex = new int[4];
				
				if(iniFile.get("esc" + String.valueOf(i), "Dir1") == null) { 
					shieldIndex[Heading.NORTH.toInt()] = 0;
					shieldIndex[Heading.EAST.toInt()] = 0; 
					shieldIndex[Heading.SOUTH.toInt()] = 0; 
					shieldIndex[Heading.WEST.toInt()] = 0; 
					shields.setElementAt(new ShieldData(shieldIndex), i);
					continue; 
				}
				
				shieldIndex[Heading.NORTH.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir1"));
				shieldIndex[Heading.EAST.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir2"));
				shieldIndex[Heading.SOUTH.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir3"));
				shieldIndex[Heading.WEST.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir4"));
				
				shields.setElementAt(new ShieldData(shieldIndex), i);
			}

			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return shields;
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
			return null;
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
