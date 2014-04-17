/**
 * Stores data about a position in the world
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class WorldPositionData {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int mMap;
	private int mPosX;
	private int mPosY;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param mMap
	 * @param mPosX
	 * @param mPosY
	 */
	public WorldPositionData(int mMap, int mPosX, int mPosY) {
		super();
		this.mMap = mMap;
		this.setPosX(mPosX);
		this.setPosY(mPosY);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================


	/**
	 * @return the mMap
	 */
	public int getMap() {
		return mMap;
	}

	/**
	 * @param mMap the mMap to set
	 */
	public void setMap(int mMap) {
		this.mMap = mMap;
	}

	/**
	 * @return the mPosX
	 */
	public int getPosX() {
		return mPosX;
	}

	/**
	 * @param mPosX the mPosX to set
	 */
	public void setPosX(int mPosX) {
		this.mPosX = mPosX;
	}

	/**
	 * @return the mPosY
	 */
	public int getPosY() {
		return mPosY;
	}

	/**
	 * @param mPosY the mPosY to set
	 */
	public void setPosY(int mPosY) {
		this.mPosY = mPosY;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
