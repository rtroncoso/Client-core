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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mob.client.Game;
import com.mob.client.data.BodyData;
import com.mob.client.data.AnimationData;
import com.mob.client.data.GrhData;
import com.mob.client.data.HeadData;
import com.mob.client.data.HelmetData;
import com.mob.client.interfaces.IConstants;
import com.mob.client.util.Util;

public class InitLoader implements IConstants {
	
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private FileHandle fileHandle;
	private Game _game;

	// ===========================================================
	// Constructors
	// ===========================================================
	public InitLoader(Game game) {
		this._game = game;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================


	// ===========================================================
	// Methods
	// ===========================================================
	public Vector<GrhData> loadGrhData(String initFileName) {
		Vector<GrhData> inits = new Vector<GrhData>();
		this.fileHandle = Gdx.files.internal("data/init/" + initFileName);
		
		try {
			DataInputStream file = new DataInputStream(fileHandle.read());
			file.skipBytes(4);
			int numGrhs = Util.leShort(file.readShort());
			inits.setSize(numGrhs + 1);
			inits.setElementAt(new GrhData(0, 0, 0, 0, 0, 0, 0, new int[0], 0), 0);
			file.skipBytes(2);
			int grh = Util.leShort(file.readShort());
			file.skipBytes(2); // no es negro si nadie lo ve
			
			while(grh > 0) {
				int fileNum = 0, sX = 0, sY = 0, numFrames = 0, pixelWidth = 0, pixelHeight = 0, frames[] = new int[0];
				double speed = 0.0d, tileWidth = 0.0d, tileHeight = 0.0d;
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

					tileWidth = (double) pixelWidth / TILE_PIXEL_WIDTH;
					tileHeight = (double) pixelHeight / TILE_PIXEL_HEIGHT;
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
		this.fileHandle = Gdx.files.internal("data/init/" + initFileName);
		int numCuerpos = 0, grhArray[] = new int[4], headOffSetX = 0, headOffSetY = 0;
		
		DataInputStream file = new DataInputStream(fileHandle.read());
		
		try {
			file.skipBytes(GAME_FILE_HEADER_SIZE);
			numCuerpos = Util.leShort(file.readShort());
			cuerpos.setSize(numCuerpos + 1);
			
			for(int i = 1; i <= numCuerpos; i++) {
				AnimationData[] tmpCuerpo = new AnimationData[4];
				
				grhArray[WALK_NORTH] = Util.leShort(file.readShort());
				tmpCuerpo[WALK_NORTH] = this._game.initGrh(grhArray[WALK_NORTH], 0);
				grhArray[WALK_EAST] = Util.leShort(file.readShort());
				tmpCuerpo[WALK_EAST] = this._game.initGrh(grhArray[WALK_EAST], 0);
				grhArray[WALK_SOUTH] = Util.leShort(file.readShort());
				tmpCuerpo[WALK_SOUTH] = this._game.initGrh(grhArray[WALK_SOUTH], 0);
				grhArray[WALK_WEST] = Util.leShort(file.readShort());
				tmpCuerpo[WALK_WEST] = this._game.initGrh(grhArray[WALK_WEST], 0);
				
				headOffSetX = Util.leShort(file.readShort());
				headOffSetY = Util.leShort(file.readShort());
				
				cuerpos.setElementAt(new BodyData(tmpCuerpo, headOffSetX, headOffSetY), i);
				//Gdx.app.log("InitData", "Cargue un cuerpo, van: " + cuerpos.size());
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
		this.fileHandle = Gdx.files.internal("data/init/" + initFileName);
		int numHeads = 0;
		
		DataInputStream file = new DataInputStream(fileHandle.read());
		
		try {
			file.skipBytes(GAME_FILE_HEADER_SIZE);
			numHeads = Util.leShort(file.readShort());
			heads.setSize(numHeads + 1);
			
			for(int i = 1; i <= numHeads; i++) {
				int headIndex[] = new int[4];
				
				headIndex[WALK_NORTH] = Util.leShort(file.readShort());
				headIndex[WALK_EAST] = Util.leShort(file.readShort());
				headIndex[WALK_SOUTH] = Util.leShort(file.readShort());
				headIndex[WALK_WEST] = Util.leShort(file.readShort());

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
		this.fileHandle = Gdx.files.internal("data/init/" + initFileName);
		int numHelmets = 0;
		
		DataInputStream file = new DataInputStream(fileHandle.read());
		
		try {
			file.skipBytes(GAME_FILE_HEADER_SIZE);
			numHelmets = Util.leShort(file.readShort());
			helmets.setSize(numHelmets + 1);
			
			for(int i = 1; i <= numHelmets; i++) {
				int helmetIndex[] = new int[4];
				
				helmetIndex[WALK_NORTH] = Util.leShort(file.readShort());
				helmetIndex[WALK_EAST] = Util.leShort(file.readShort());
				helmetIndex[WALK_SOUTH] = Util.leShort(file.readShort());
				helmetIndex[WALK_WEST] = Util.leShort(file.readShort());
				
				helmets.setElementAt(new HelmetData(helmetIndex), i);
			}
			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return helmets;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}
