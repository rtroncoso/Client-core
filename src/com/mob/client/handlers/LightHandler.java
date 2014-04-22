/**
 * Handles created lights on the engine
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.handlers;

import java.util.HashMap;
import java.util.Iterator;

import box2dLight.Light;
import box2dLight.PointLight;

import com.badlogic.gdx.graphics.Color;
import com.mob.client.Game;
import com.mob.client.interfaces.IConstants;

public class LightHandler implements IConstants {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private HashMap<Integer, Light> mLights;
	private Game mGame;

	// ===========================================================
	// Constructors
	// ===========================================================
	public LightHandler(Game pGame) {
		this.mGame = pGame;
		this.mLights = new HashMap<Integer, Light>();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public int createLight(int pX, int pY, Color pColor, float pSize) {
		return this.createLight(pX, pY, pColor, pSize, 0);
	}

	public int createLight(int pX, int pY, Color pColor, float pSize, float pSpeed) {
		
		// Allocate our light on the last index
		int index = this.mLights.size() + 1;
		this.mLights.put(index, new PointLight(this.mGame.getBox2DEngine().getRayHandler(), DEFAULT_NUM_RAYS, 
				new Color(pColor.r, pColor.g, pColor.b, ALPHA_LIGHTS), pSize, 
				(pX * TILE_PIXEL_WIDTH) - (TILE_PIXEL_WIDTH / 2f), 
				(pY * TILE_PIXEL_HEIGHT) - (TILE_PIXEL_HEIGHT / 2f)));
		return index;
	}

	public int createLight(float x, float y, Color pColor, float pSize) {
		int index = this.mLights.size() + 1;
		this.mLights.put(index, new PointLight(this.mGame.getBox2DEngine().getRayHandler(), DEFAULT_NUM_RAYS, pColor, pSize, 
				x - (TILE_PIXEL_WIDTH / 2f), y - (TILE_PIXEL_HEIGHT / 2f)));
		return index;
	}

	public void moveLight(int pIndex, int pX, int pY) {
		this.moveLight(pIndex, (pX * TILE_PIXEL_WIDTH), (pY * TILE_PIXEL_HEIGHT));
	}
	
	public void moveLight(int pIndex, float pNewX, float pNewY) {
		if(!this.mLights.containsKey(pIndex)) return;
		Light light = this.mLights.get(pIndex);
		light.setPosition(pNewX - (TILE_PIXEL_WIDTH / 2f), pNewY - (TILE_PIXEL_HEIGHT / 2f));;
	}
	
	public void deleteLight(int pX, int pY) {
		
		// Search light
		Light index = this.search(pX, pY);
		if(index != null) index.setActive(false);
	}
	
	public void deleteLight(int pIndex) {
		this.mLights.get(pIndex).setActive(false);
	}
	
	public Light search(int pX, int pY) {
	    Iterator<Integer> it = this.mLights.keySet().iterator();
		while(it.hasNext()) {
			int i = it.next();
			Light entry = this.mLights.get(i);
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
	public HashMap<Integer, Light> getLights() {
		return mLights;
	}

	/**
	 * @param mLights the mLights to set
	 */
	public void setLights(HashMap<Integer, Light> mLights) {
		this.mLights = mLights;
	}
	
	public Light getLight(int pIndex) {
		if(this.mLights.containsKey(pIndex))
			return this.mLights.get(pIndex);
		else
			return null;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
