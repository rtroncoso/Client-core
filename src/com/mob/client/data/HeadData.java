/**
 * Stores information about a head
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class HeadData {

	private int[] headIndex;

	/**
	 * @param headIndex
	 */
	public HeadData(int[] headIndex) {
		this.headIndex = headIndex;
	}

	/**
	 * @return the headIndex
	 */
	public int[] getHeadIndex() {
		return headIndex;
	}

	/**
	 * @param headIndex the headIndex to set
	 */
	public void setHeadIndex(int[] headIndex) {
		this.headIndex = headIndex;
	}
}
