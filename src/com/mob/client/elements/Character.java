/**
 * Character holder. All foreground actions of a character should go here (name, dead, move, etc).
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.elements;

import com.mob.client.Game;
import com.mob.client.sprites.CharacterSprite;

public class Character extends CharacterSprite {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private boolean mDead;
	private boolean mInvisible;
	private String mNombre;
	
	private int mUserPosX;
	private int mUserPosY;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Character(Game _game, int x, int y, byte mHeading, int bodyIndex, int headIndex, int helmetIndex) {
		super(_game, x, y, mHeading, bodyIndex, headIndex, helmetIndex);
		
		this.mDead = false;
		this.mInvisible = false;
		this.mNombre = "";
		
		this.setUserPosX(x);
		this.setUserPosY(y);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void draw() {
		
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mNombre
	 */
	public String getNombre() {
		return mNombre;
	}
	
	/**
	 * @param mNombre the mNombre to set
	 */
	public void setNombre(String mNombre) {
		this.mNombre = mNombre;
	}

	/**
	 * @return the mInvisible
	 */
	public boolean isInvisible() {
		return mInvisible;
	}

	/**
	 * @param mInvisible the mInvisible to set
	 */
	public void setInvisible(boolean mInvisible) {
		this.mInvisible = mInvisible;
	}

	/**
	 * @return the mDead
	 */
	public boolean isDead() {
		return mDead;
	}

	/**
	 * @param mDead the mDead to set
	 */
	public void setDead(boolean mDead) {
		this.mDead = mDead;
	}

	/**
	 * @return the mUserPosX
	 */
	public int getUserPosX() {
		return mUserPosX;
	}

	/**
	 * @param mUserPosX the mUserPosX to set
	 */
	public void setUserPosX(int mUserPosX) {
		this._x = mUserPosX * TILE_PIXEL_WIDTH;
		this.mUserPosX = mUserPosX;
	}

	/**
	 * @return the mUserPosY
	 */
	public int getUserPosY() {
		return mUserPosY;
	}

	/**
	 * @param mUserPosY the mUserPosY to set
	 */
	public void setUserPosY(int mUserPosY) {
		this._y = mUserPosY * TILE_PIXEL_HEIGHT;// * (TILE_MAP_HEIGHT * TILE_PIXEL_HEIGHT); // Adapt position to Ydown
		this.mUserPosY = mUserPosY;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void focusCamera() {
		this._game.getCamera().position.set(this._x, this._y, 0);
		this._game.getCamera().update();
	}
	
	@Override
	public void move(byte pHeading) {
		if(this.mMoving) return; // Check if we are already moving
		
		// Set moving
		this.mHeading = pHeading;
		this.mMoving = true;
		
		// Set up NextY (let place() handle)
		switch(this.mHeading) {
			case(WALK_NORTH):
				this.mNextY = -1;
				break;
			case(WALK_EAST):
				this.mNextX = 1;
				break;
			case(WALK_SOUTH):
				this.mNextY = 1;
				break;
			case(WALK_WEST):
				this.mNextX = -1;
				break;
		}
		
		// Update our position
		this.mUserPosX += this.mNextX;
		this.mUserPosY += this.mNextY;
	}
	
	@Override
	public void place() {
		float offsetCounterX = 0.0f, offsetCounterY = 0.0f;
		
		if(this.mNextX != 0) {
			offsetCounterX += this.mNextX * this.mSpeed * this.mDeltaTime; 
			this._x += offsetCounterX;
			if(this.mNextX == 1) {
				if(this._x >= (this.mUserPosX) * TILE_PIXEL_HEIGHT) {
					this.mNextX = 0;
					this.mMoving = false;
				}
			} else if(this.mNextX == -1) {
				if(this._x <= (this.mUserPosX) * TILE_PIXEL_HEIGHT) {
					this.mNextX = 0;
					this.mMoving = false;
				}
			}
		}
		if(this.mNextY != 0) {
			offsetCounterY += this.mNextY * this.mSpeed * this.mDeltaTime; 
			this._y += offsetCounterY;
			if(this.mNextY == 1) {
				if(this._y >= (this.mUserPosY) * TILE_PIXEL_HEIGHT) {
					this.mNextY = 0;
					this.mMoving = false;
				}
			} else if(this.mNextY == -1) {
				if(this._y <= (this.mUserPosY) * TILE_PIXEL_HEIGHT) {
					this.mNextY = 0;
					this.mMoving = false;
				}
			}
		}
	}
	
	public void moveUp() {
		this.move(WALK_NORTH);
	}
	
	public void moveDown() {
		this.move(WALK_SOUTH);
	}
	
	public void moveLeft() {
		this.move(WALK_WEST);
	}
	
	public void moveRight() {
		this.move(WALK_EAST);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
