/**
 * Binary INIT files parsing
 * TODO :
 * 	- Fix reading of VB6 binarys, not being able to read VB6 longs and turn them into Java integers
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.loaders;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Vector;

import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mob.client.data.BodyData;
import com.mob.client.data.FxData;
import com.mob.client.data.GrhData;
import com.mob.client.data.HeadData;
import com.mob.client.data.HelmetData;
import com.mob.client.data.ShieldData;
import com.mob.client.data.WeaponData;
import com.mob.client.interfaces.IConstants;
import com.mob.client.util.Util;

public class Loader implements IConstants {
	
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private FileHandle fileHandle;

	// ===========================================================
	// Constructors
	// ===========================================================
	

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void load(String initFileName) {
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================


	// ===========================================================
	// Methods
	// ===========================================================
	public Vector<GrhData> loadGrhData(String initFileName) {
		Vector<GrhData> inits = new Vector<GrhData>();
		this.fileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		
		try {
			DataInputStream file = new DataInputStream(fileHandle.read());
			file.skipBytes(4);
			int numGrhs = Util.leShort(file.readShort());
			file.skipBytes(2);
			
			inits.setSize(numGrhs + 1);
			inits.setElementAt(new GrhData(0, 0, 0, 0, 0, 0, 0, new int[0], 0), 0);
			
			int grh = Util.leShort(file.readShort());
			file.skipBytes(2); // no es negro si nadie lo ve
			
			while(grh > 0) {
				int fileNum = 0, sX = 0, sY = 0, numFrames = 0, pixelWidth = 0, pixelHeight = 0, frames[] = new int[0];
				float speed = 0.0f, tileWidth = 0.0f, tileHeight = 0.0f;
				numFrames = Util.leShort(file.readShort());
				
				if(numFrames > 1) {
					frames = new int[numFrames];
					for(int j=0; j < numFrames; j++) {
						frames[j] = Util.leShort(file.readShort());
						file.skipBytes(2);
						if(frames[j] <= 0) throw new IOException("frames[]: " + frames[j]);
					}

					// Hardcodeamos speed (Java no lee single floating points de VB)
					file.skipBytes(4);
					speed = (numFrames * 1000) / 60;
					if(speed <= 0) throw new IOException("speed (numFrames > 1)");

					pixelWidth = inits.get(frames[0]).getPixelWidth();
					if(pixelWidth <= 0) throw new IOException("pixelWidth (numFrames > 1)");
					
					pixelHeight = inits.get(frames[0]).getPixelHeight();
					if(pixelHeight <= 0) throw new IOException("pixelHeight (numFrames > 1)");
					
					tileWidth = inits.get(frames[0]).getTileWidth();
					if(tileWidth <= 0) throw new IOException("tileWidth (numFrames > 1)");
					tileHeight = inits.get(frames[0]).getTileHeight();
					if(tileHeight <= 0) throw new IOException("tileHeight (numFrames > 1)");
				} else {
					// Read normal GRH
					fileNum = Util.leShort(file.readShort());
					file.skipBytes(2);
					
					if(fileNum <= 0) throw new IOException("fileNum");
					
					sX = Util.leShort(file.readShort());
					if(sX < 0) throw new IOException("sX (numFrames < 1)");
					
					sY = Util.leShort(file.readShort());
					if(sY < 0) throw new IOException("sY (numFrames < 1)");
					
					pixelWidth = Util.leShort(file.readShort());
					if(pixelWidth <= 0) throw new IOException("pixelWidth (numFrames < 1)");
					
					pixelHeight = Util.leShort(file.readShort());
					if(pixelHeight <= 0) throw new IOException("pixelHeight (numFrames < 1)");

					tileWidth = (float) pixelWidth / TILE_PIXEL_WIDTH;
					tileHeight = (float) pixelHeight / TILE_PIXEL_HEIGHT;
				}
				inits.setElementAt(new GrhData(sX, sY, fileNum, pixelWidth, pixelHeight, tileWidth, tileHeight, frames, speed), grh);
				grh = Util.leShort(file.readShort());
				file.skipBytes(2);
			}
			
		} catch (EOFException e) {
			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return inits;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Vector<BodyData> loadCuerpos(String initFileName) {
		Vector<BodyData> cuerpos = new Vector<BodyData>();
		this.fileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		int numCuerpos = 0;
		
		DataInputStream file = new DataInputStream(fileHandle.read());
		
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
	
	public Vector<HeadData> loadCabezas(String initFileName) {
		Vector<HeadData> heads = new Vector<HeadData>();
		this.fileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		int numHeads = 0;
		
		DataInputStream file = new DataInputStream(fileHandle.read());
		
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
	
	public Vector<HelmetData> loadCascos(String initFileName) {
		Vector<HelmetData> helmets = new Vector<HelmetData>();
		this.fileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		int numHelmets = 0;
		
		DataInputStream file = new DataInputStream(fileHandle.read());
		
		try {
			file.skipBytes(GAME_FILE_HEADER_SIZE);
			numHelmets = Util.leShort(file.readShort());
			helmets.setSize(numHelmets + 1);
			
			for(int i = 1; i <= numHelmets; i++) {
				int helmetIndex[] = new int[4];
				
				helmetIndex[Heading.NORTH.toInt()] = Util.leShort(file.readShort());
				helmetIndex[Heading.EAST.toInt()] = Util.leShort(file.readShort());
				helmetIndex[Heading.SOUTH.toInt()] = Util.leShort(file.readShort());
				helmetIndex[Heading.WEST.toInt()] = Util.leShort(file.readShort());
				
				helmets.setElementAt(new HelmetData(helmetIndex), i);
			}
			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return helmets;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Vector<FxData> loadFxs(String initFileName) {
		Vector<FxData> fxs = new Vector<FxData>();
		this.fileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		int numFxs = 0;
		
		DataInputStream file = new DataInputStream(fileHandle.read());
		
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
	
	public Vector<WeaponData> loadWeapons(String initFileName) {
		Vector<WeaponData> weapons = new Vector<WeaponData>();
		Ini iniFile = new Ini();
		Config c = new Config();
		c.setLowerCaseSection(true);
		iniFile.setConfig(c);
		
		try {
			iniFile.load(Gdx.files.internal(GAME_INIT_PATH + initFileName).file());
			
			int numArmas = Integer.parseInt(iniFile.get("init", "NumArmas"));
			weapons.setSize(numArmas + 1);
			
			for(int i = 1; i <= numArmas; i++) {
				int[] weaponIndex = new int[4];
				
				if(iniFile.get("arma" + String.valueOf(i), "Dir1") == null) { 
					weaponIndex[Heading.NORTH.toInt()] = 0;
					weaponIndex[Heading.EAST.toInt()] = 0; 
					weaponIndex[Heading.SOUTH.toInt()] = 0; 
					weaponIndex[Heading.WEST.toInt()] = 0; 
					weapons.setElementAt(new WeaponData(weaponIndex), i);
					continue; 
				}
				
				weaponIndex[Heading.NORTH.toInt()] = Integer.parseInt(iniFile.get("arma" + String.valueOf(i), "Dir1"));
				weaponIndex[Heading.EAST.toInt()] = Integer.parseInt(iniFile.get("arma" + String.valueOf(i), "Dir2"));
				weaponIndex[Heading.SOUTH.toInt()] = Integer.parseInt(iniFile.get("arma" + String.valueOf(i), "Dir3"));
				weaponIndex[Heading.WEST.toInt()] = Integer.parseInt(iniFile.get("arma" + String.valueOf(i), "Dir4"));
				
				weapons.setElementAt(new WeaponData(weaponIndex), i);
			}

			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return weapons;
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Vector<ShieldData> loadShields(String initFileName) {
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
	// Inner and Anonymous Classes
	// ===========================================================


}
