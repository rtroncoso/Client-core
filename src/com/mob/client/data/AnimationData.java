/**
 * Stores info of an animation
 * TODO : 
 *  - Clean up this class and keep only grhIndex (we use libGdx animation methods for animations)
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class AnimationData {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int grhIndex;
	private int frameCounter;
	private double speedCounter;
	private long ticksCounter;
	private boolean loop;
	private int started;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param grhIndex
	 * @param frameCounter
	 * @param speedCounter
	 * @param ticksCounter
	 * @param started
	 * @param loops 
	 */
	public AnimationData(int grhIndex, int frameCounter, double speedCounter,
			long ticksCounter, int started, boolean loops) {
		super();
		this.grhIndex = grhIndex;
		this.frameCounter = frameCounter;
		this.speedCounter = speedCounter;
		this.ticksCounter = ticksCounter;
		this.started = started;
		this.loop = loops;
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
	public int getGrhIndex() {
		return grhIndex;
	}
	/**
	 * @param grhIndex the grhIndex to set
	 */
	public void setGrhIndex(int grhIndex) {
		this.grhIndex = grhIndex;
	}
	/**
	 * @return the frameCounter
	 */
	public int getFrameCounter() {
		return frameCounter;
	}
	/**
	 * @param frameCounter the frameCounter to set
	 */
	public void setFrameCounter(int frameCounter) {
		this.frameCounter = frameCounter;
	}
	/**
	 * @return the speedCounter
	 */
	public double getSpeedCounter() {
		return speedCounter;
	}
	/**
	 * @param speedCounter the speedCounter to set
	 */
	public void setSpeedCounter(double speedCounter) {
		this.speedCounter = speedCounter;
	}
	/**
	 * @return the ticksCounter
	 */
	public long getTicksCounter() {
		return ticksCounter;
	}
	/**
	 * @param ticksCounter the ticksCounter to set
	 */
	public void setTicksCounter(long ticksCounter) {
		this.ticksCounter = ticksCounter;
	}
	/**
	 * @return the started
	 */
	public int getStarted() {
		return started;
	}
	/**
	 * @param started the started to set
	 */
	public void setStarted(int started) {
		this.started = started;
	}

	/**
	 * @return the loops
	 */
	public boolean isLoop() {
		return loop;
	}

	/**
	 * @param loops the loops to set
	 */
	public void setLoop(boolean loops) {
		this.loop = loops;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
