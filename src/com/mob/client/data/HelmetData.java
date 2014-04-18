/**
 * Stores information about a helmet
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class HelmetData {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int[] mHelmetIndex;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param headIndex
	 */
	public HelmetData(int[] helmetIndex) {
		super();
		this.mHelmetIndex = helmetIndex;
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
	public int[] getHelmetIndex() {
		return mHelmetIndex;
	}

	/**
	 * @param headIndex the headIndex to set
	 */
	public void setHelmetIndex(int[] helmetIndex) {
		this.mHelmetIndex = helmetIndex;
	}
	
	public int getHelmet(int pIndex) {
		return this.mHelmetIndex[pIndex];
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}
