/**
 * Sprite with capabilities to move but no rendering (usually inherited by other sprites)
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.sprites;

import com.mob.client.Game;

public class MovingSprite extends GameSprite {
	// ===========================================================
	// Constants
	// ===========================================================
	

	// ===========================================================
	// Fields
	// ===========================================================
	protected int _scrollDirectionX;
	protected int _scrollDirectionY;
	
	protected int mOffsetCounterX;
	protected int mOffsetCounterY;
	
	protected int mLastX;
	protected int mLastY;
	protected int mNextX;
	protected int mNextY;
	
	protected boolean mMoving;
	protected float mSpeed;
	
	protected byte mHeading;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public MovingSprite (Game game, float x, float y) {
		super (game, x, y);
		this._scrollDirectionX = 0;
		this._scrollDirectionY = 0;
		
		this.mOffsetCounterX = 0;
		this.mOffsetCounterY = 0;
		
		this.mNextX = 0;
		this.mNextY = 0;
		this.mLastX = 0;
		this.mLastY = 0;
		
		this.mMoving = false;
		this.mSpeed = 150.0f;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the _moving
	 */
	public boolean isMoving() {
		return mMoving;
	}

	/**
	 * @param _moving the _moving to set
	 */
	public void setMoving(boolean _moving) {
		this.mMoving = _moving;
	}

	/**
	 * @return the mHeading
	 */
	public byte getHeading() {
		return mHeading;
	}

	/**
	 * @param mHeading the mHeading to set
	 */
	public void setHeading(byte mHeading) {
		this.mHeading = mHeading;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void move(byte pHeading) {
		this.mHeading = pHeading;
	}
	
	public void place() {
		
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
