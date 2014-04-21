/**
 * Handles all active textures loaded from the graphics path.
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.mob.client.Game;
import com.mob.client.interfaces.IConstants;

public class SurfaceHandler implements IConstants {
	
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private HashMap<String, Texture> mDict;
	private String mGraphicsPath;
	
	protected Game mGame;

	// ===========================================================
	// Constructors
	// ===========================================================
	public SurfaceHandler(Game game) {
		this.mGame = game;
		this.mDict = new HashMap<String, Texture>();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mGraphicsPath
	 */
	public String getGraphicsPath() {
		return mGraphicsPath;
	}

	/**
	 * @param mGraphicsPath the mGraphicsPath to set
	 */
	public void setGraphicsPath(String mGraphicsPath) {
		this.mGraphicsPath = mGraphicsPath;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * Loads into memory ALL the textures in the graphics path
	 */
	public void loadAllTextures() {
		FileHandle file = Gdx.app.getFiles().internal(this.mGraphicsPath);
		if(file.isDirectory()) {
			for(FileHandle tmp : file.list()) {
				if(tmp.extension() == GAME_GRAPHICS_EXTENSION) {
					Gdx.app.log(this.getClass().getSimpleName(), "Cargando " + tmp.name());
					this.loadTexture(tmp.nameWithoutExtension());
				}
			}
		}
	}
	
	/**
	 * @param fileName Name of the file found in the graphics folder
	 */
	public void loadTexture(String fileName) {
		Texture texture = new Texture(this.mGraphicsPath + fileName + GAME_GRAPHICS_EXTENSION);
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		this.add(fileName, texture);
	}

	/**
	 * @param key Index in map
	 * @param texture Texture to add
	 */
	 public void add(final String key, final Texture texture) {
		 if (this.mDict.containsKey(key)) {
			 return;
		 }
	   
	 	this.mDict.put(key, texture);
	 }

	/**
	 * Gets a Texture from the map
	 * @param key Index in map
	 * @return BundledTexture inside the map
	 */
	 public Texture get(final String key) {
		 if(!this.mDict.containsKey(key)) this.loadTexture(key);
		 return this.mDict.get(key);
	 }
	  
	 /**
	  * Unloads a Texture from the map and memory
	  * @param key Index in map
	  */
	public void dispose(final String key) {
		if (!this.mDict.containsKey(key)) {
			return;
		}
	   
		final Texture t = this.mDict.get(key);
		t.dispose();
		this.mDict.remove(key);
	}
	  
	public void disposeAll() {
		for (final Texture t : this.mDict.values()) {
			t.dispose();
		}
   
		this.mDict.clear();
	 }

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}
