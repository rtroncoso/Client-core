/**
 * Stores information about a head
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class HeadData {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int[] mHeadIndex;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param headIndex
	 */
	public HeadData(int[] headIndex) {
		this.mHeadIndex = headIndex;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the headIndex
	 */
	public int[] getHeadIndex() {
		return mHeadIndex;
	}

	/**
	 * @param headIndex the headIndex to set
	 */
	public void setHeadIndex(int[] headIndex) {
		this.mHeadIndex = headIndex;
	}
	
	public int getHead(int pIndex) {
		return this.mHeadIndex[pIndex];
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
