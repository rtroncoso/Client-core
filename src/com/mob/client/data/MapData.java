/**
 * Temporal storage for maps info until I make a Map element (to get maps rendered easier)
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

import com.mob.client.interfaces.Constants;

public class MapData implements Constants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected MapBlockData mTile[][];
	protected boolean mLoaded;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param mTile
	 */
	public MapData() {
		this.mTile = new MapBlockData[MAX_MAP_SIZE_WIDTH + 1][MAX_MAP_SIZE_HEIGHT + 1];
		this.mLoaded = false;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mTile
	 */
	public MapBlockData[][] getTileArray() {
		return mTile;
	}

	/**
	 * @param mTile the mTile to set
	 */
	public void setTileArray(MapBlockData mTile[][]) {
		this.mTile = mTile;
	}
	
	public MapBlockData getTile(int pX, int pY) {
		return this.mTile[pX][pY];
	}
	
	public void setTile(int pX, int pY, MapBlockData pMapBlock) {
		this.mTile[pX][pY] = pMapBlock;
	}

	public boolean isLoaded() {
		return mLoaded;
	}

	public void setLoaded(boolean pLoaded) {
		this.mLoaded = pLoaded;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
