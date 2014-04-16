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
	private int[] helmetIndex;

	// ===========================================================
	// Constructors
	// ===========================================================


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @param headIndex
	 */
	public HelmetData(int[] helmetIndex) {
		super();
		this.helmetIndex = helmetIndex;
	}

	/**
	 * @return the headIndex
	 */
	public int[] getHelmetIndex() {
		return helmetIndex;
	}

	/**
	 * @param headIndex the headIndex to set
	 */
	public void setHelmetIndex(int[] helmetIndex) {
		this.helmetIndex = helmetIndex;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}
