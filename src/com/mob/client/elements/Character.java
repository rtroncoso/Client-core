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
	private int mLastUserPosX;
	private int mLastUserPosY;
	private int mCharIndex;
	private boolean mDead;
	private boolean mInvisible;
	private boolean mSeesRoof;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Character(Game _game, int charIndex, int x, int y, Heading mHeading, int bodyIndex, int weaponIndex, int headIndex) {
		this(_game, charIndex, x, y, mHeading, bodyIndex, weaponIndex, headIndex, 0, 0);
	}
	
	public Character(Game _game, int charIndex, int x, int y, Heading mHeading, int bodyIndex, int weaponIndex, int headIndex, int helmetIndex, int shieldIndex) {
		this(_game, charIndex, x, y, mHeading, bodyIndex, weaponIndex, headIndex, helmetIndex, shieldIndex, 0);
	}
	public Character(Game _game, int charIndex, int x, int y, Heading mHeading, int bodyIndex, int weaponIndex, int headIndex, int helmetIndex, int shieldIndex, int fxIndex) {
		super(_game, x, y, mHeading, bodyIndex, weaponIndex, headIndex, helmetIndex, shieldIndex, fxIndex);
		
		
		// Init object
		this.mLastUserPosX = x;
		this.mLastUserPosY = y;
		this.mCharIndex = charIndex;
		this.mDead = false;
		this.mInvisible = false;
		this.mName = "";
		this.mSeesRoof = false;
		
		// Update user position
		this.setUserPos(x, y);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void draw() {
		
	}
	
	//@Override
	public void move(Heading pHeading) {
		
		// Vars
		int nextX = 0, nextY = 0;
		if(this.mMoving) return; // Check if we are already moving
		
		// Set Heading
		this.mHeading = pHeading;
		
		// Set up NextY (let place() handle)
		switch(this.mHeading) {
			case NORTH:
				nextY = -1;
				break;
			case EAST:
				nextX = 1;
				break;
			case SOUTH:
				nextY = 1;
				break;
			case WEST:
				nextX = -1;
				break;
		}
		
		// Check if legal pos
		if(!this.mGame.getEngine().getTile(this.mUserPosX + nextX, this.mUserPosY + nextY).isLegalPos()) return;
		
		// Fill destination
		this.mNextX = nextX;
		this.mNextY = nextY;
		
		// Backup old position
		this.mLastUserPosX = this.mUserPosX;
		this.mLastUserPosY = this.mUserPosY;
		
		// Change position
		this.mUserPosX += this.mNextX;
		this.mUserPosY += this.mNextY;
		
		// Set moving
		this.mMoving = true;
	}
	
	@Override
	public void place() {
		
		// Vars
		float offsetCounterX = 0.0f, offsetCounterY = 0.0f;
		
		// This routine calculates user position if they are moving then plots it on the screen
		if(this.mNextX != 0) {
			offsetCounterX += this.mNextX * this.mSpeed * this.mDeltaTime; 
			this.mX += offsetCounterX;
			if(this.mNextX == 1) {
				if(this.mX >= (this.mUserPosX) * TILE_PIXEL_WIDTH) {
					this.updateUserPos();
					this.mMoving = false;
					this.mNextX = 0;
				}
			} else if(this.mNextX == -1) {
				if(this.mX <= (this.mUserPosX) * TILE_PIXEL_WIDTH) {
					this.updateUserPos();
					this.mMoving = false;
					this.mNextX = 0;
				}
			}
		}
		
		if(this.mNextY != 0) {
			offsetCounterY += this.mNextY * this.mSpeed * this.mDeltaTime; 
			this.mY += offsetCounterY;
			if(this.mNextY == 1) {
				if(this.mY >= (this.mUserPosY) * TILE_PIXEL_HEIGHT) {
					this.updateUserPos();
					this.mMoving = false;
					this.mNextY = 0;
				}
			} else if(this.mNextY == -1) {
				if(this.mY <= (this.mUserPosY) * TILE_PIXEL_HEIGHT) {
					this.updateUserPos();
					this.mMoving = false;
					this.mNextY = 0;
				}
			}
		}
		
		// Update position
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mNombre
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * @param mNombre the mNombre to set
	 */
	public void setName(String mName) {
		this.mName = mName;
	}

	/**
	 * @return the mCharIndex
	 */
	public int getCharIndex() {
		return mCharIndex;
	}

	/**
	 * @param mCharIndex the mCharIndex to set
	 */
	public void setCharIndex(int mCharIndex) {
		this.mCharIndex = mCharIndex;
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

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * IMPORTANT: CALL THIS TO CHANGE USER POSITION
	 */
	public void setUserPos(int x, int y) {
		
		// Delete old player position from map
		this.mGame.getEngine().getTile(this.mLastUserPosX, this.mLastUserPosY).setCharacter(null);;
		
		// Change our graphic position
		this.setUserPosX(x);
		this.setUserPosY(y);
		
		// Plot on map
		this.mGame.getEngine().setCharacter(this.mUserPosX, this.mUserPosY, this);
		
		// Check if we see a roof
		if(this.mGame.getEngine().getTile(this.mUserPosX, this.mUserPosY).isRoof())
			this.mSeesRoof = true;
		else
			this.mSeesRoof = false;
	}
	
	/**
	 * Probably going to use it on refresh position (player lagged)
	 */
	public void updateUserPos() {
		this.setUserPos(this.mUserPosX, this.mUserPosY);
	}
	
	public void focusCamera() {
		this.mGame.getCamera().position.set(this.mX, this.mY, 0);
		this.mGame.getCamera().update();
	}
	
	public void moveUp() {
		this.move(Heading.NORTH);
	}
	
	public void moveDown() {
		this.move(Heading.SOUTH);
	}
	
	public void moveLeft() {
		this.move(Heading.WEST);
	}
	
	public void moveRight() {
		this.move(Heading.EAST);
	}

	/**
	 * @return the mSeesRoof
	 */
	public boolean isUnderRoof() {
		return mSeesRoof;
	}

	/**
	 * @param mSeesRoof the mSeesRoof to set
	 */
	public void setSeesRoof(boolean mSeesRoof) {
		this.mSeesRoof = mSeesRoof;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
