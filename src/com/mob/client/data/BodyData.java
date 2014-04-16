/**
 * Stores information about a body
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class BodyData {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private AnimationData[] grhCuerpo;
	private int headOffsetX;
	private int headOffsetY;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param grhIndex
	 * @param headOffsetX
	 * @param headOffsetY
	 */
	public BodyData(AnimationData[] grhIndex, int headOffsetX, int headOffsetY) {
		super();
		this.grhCuerpo = grhIndex;
		this.headOffsetX = headOffsetX;
		this.headOffsetY = headOffsetY;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the grhIndex
	 */
	public AnimationData[] getBodyArray() {
		return grhCuerpo;
	}

	/**
	 * @param grhIndex the grhIndex to set
	 */
	public void setBodyArray(AnimationData[] grhCuerpo) {
		this.grhCuerpo = grhCuerpo;
	}

	/**
	 * @return the headOffsetX
	 */
	public int getHeadOffsetX() {
		return headOffsetX;
	}

	/**
	 * @param headOffsetX the headOffsetX to set
	 */
	public void setHeadOffsetX(int headOffsetX) {
		this.headOffsetX = headOffsetX;
	}

	/**
	 * @return the headOffsetY
	 */
	public int getHeadOffsetY() {
		return headOffsetY;
	}

	/**
	 * @param headOffsetY the headOffsetY to set
	 */
	public void setHeadOffsetY(int headOffsetY) {
		this.headOffsetY = headOffsetY;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
}
