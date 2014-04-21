/**
 * Handles created lights on the engine
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.handlers;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.mob.client.Game;
import com.mob.client.elements.Shader;
import com.mob.client.interfaces.IConstants;

public class LightHandler implements IConstants {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private HashMap<Integer, Shader> mLights;
	private Game mGame;

	// ===========================================================
	// Constructors
	// ===========================================================
	public LightHandler(Game pGame) {
		this.mGame = pGame;
		this.mLights = new HashMap<Integer, Shader>();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public int createLight(int pX, int pY, Color pColor, int pSize) {
		return this.createLight(pX, pY, pColor, pSize, 0);
	}

	public int createLight(int pX, int pY, Color pColor, int pSize, float pSpeed) {
		
		// Allocate our light on the last index
		int index = this.mLights.size() + 1;
		this.mLights.put(this.mLights.size() + 1, new Shader(this.mGame, pX, pY, GAME_SHADERS_LIGHT, pColor, pSize, pSpeed));
		return index;
	}

	public void moveLight(int pIndex, int pX, int pY) {
		this.moveLight(pIndex, (pX * TILE_PIXEL_WIDTH), (pY * TILE_PIXEL_HEIGHT));
	}
	
	public void moveLight(int pIndex, float pNewX, float pNewY) {
		this.mLights.get(pIndex).setX(pNewX);
		this.mLights.get(pIndex).setY(pNewY);
	}
	
	public void deleteLight(int pX, int pY) {
		
		// Search light
		Shader index = this.search(pX, pY);
		if(index != null) index.setActive(false);
	}
	
	public void deleteLight(int pIndex) {
		this.mLights.get(pIndex).setActive(false);
	}
	
	public Shader search(int pX, int pY) {
	    Iterator<Integer> it = this.mLights.keySet().iterator();
		while(it.hasNext()) {
			int i = it.next();
			Shader entry = this.mLights.get(i);
			if((entry.getX() * TILE_PIXEL_WIDTH) == pX && (entry.getY() * TILE_PIXEL_HEIGHT == pY)) return entry;
		}
		return null;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mLights
	 */
	public HashMap<Integer, Shader> getLights() {
		return mLights;
	}

	/**
	 * @param mLights the mLights to set
	 */
	public void setLights(HashMap<Integer, Shader> mLights) {
		this.mLights = mLights;
	}
	
	public Shader getLight(int pIndex) {
		if(this.mLights.containsKey(pIndex))
			return this.mLights.get(pIndex);
		else
			return null;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
