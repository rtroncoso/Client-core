/**
 * Descripcion
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class WeaponData {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int[] mGraphic;

	// ===========================================================
	// Constructors
	// ===========================================================
	public WeaponData(int[] pGraphic) {
		this.setGraphicArray(pGraphic);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public int getGraphic(int pIndex) {
		return this.mGraphic[pIndex];
	}
	
	public void setGraphic(int pIndex, int pGraphic) {
		this.mGraphic[pIndex] = pGraphic;
	}
	
	/**
	 * @return the mGraphic
	 */
	public int[] getGraphicArray() {
		return mGraphic;
	}

	/**
	 * @param mGraphic the mGraphic to set
	 */
	public void setGraphicArray(int[] mGraphic) {
		this.mGraphic = mGraphic;
	}


	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
