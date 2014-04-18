/**
 * Store information about an FX
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class FxData {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int mGrhIndex;
	private int mOffsetX;
	private int mOffsetY;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param mGrhIndex
	 * @param mOffsetX
	 * @param mOffsetY
	 */
	public FxData(int mGrhIndex, int mOffsetX, int mOffsetY) {
		this.mGrhIndex = mGrhIndex;
		this.mOffsetX = mOffsetX;
		this.mOffsetY = mOffsetY;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mGrhIndex
	 */
	public int getGraphic() {
		return mGrhIndex;
	}
	/**
	 * @param mGrhIndex the mGrhIndex to set
	 */
	public void setGraphic(int mGrhIndex) {
		this.mGrhIndex = mGrhIndex;
	}
	
	/**
	 * @return the mOffsetX
	 */
	public int getOffsetX() {
		return mOffsetX;
	}
	/**
	 * @param mOffsetX the mOffsetX to set
	 */
	public void setOffsetX(int mOffsetX) {
		this.mOffsetX = mOffsetX;
	}
	/**
	 * @return the mOffsetY
	 */
	public int getOffsetY() {
		return mOffsetY;
	}
	/**
	 * @param mOffsetY the mOffsetY to set
	 */
	public void setOffsetY(int mOffsetY) {
		this.mOffsetY = mOffsetY;
	}


	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
