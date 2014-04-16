/**
 * Stores position information
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

import com.mob.client.interfaces.IConstants;

public class PositionData implements IConstants {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private float mX;
	private float mY;


	// ===========================================================
	// Constructors
	// ===========================================================
	public PositionData(float mX, float mY) {
		this.setX(mX);
		this.setY(mY);
	}


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mX
	 */
	public float getX() {
		return mX;
	}


	/**
	 * @param mX the mX to set
	 */
	public void setX(float mX) {
		this.mX = mX;
	}
	
	/**
	 * @return the mY
	 */
	public float getY() {
		return mY;
	}


	/**
	 * @param mY the mY to set
	 */
	public void setY(float mY) {
		this.mY = mY;
	}


	/**
	 * @return the mX
	 */
	public float getTileX() {
		return mX * TILE_PIXEL_WIDTH;
	}


	/**
	 * @param mX the mX to set
	 */
	public void setTileX(int mX) {
		this.mX = mX * TILE_PIXEL_WIDTH;
	
	}
	
	/**
	 * @return the mY
	 */
	public float getTileY() {
		return mY * TILE_PIXEL_HEIGHT;
	}


	/**
	 * @param mY the mY to set
	 */
	public void setTileY(int mY) {
		this.mY = mY * TILE_PIXEL_HEIGHT;
	}
	
	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
