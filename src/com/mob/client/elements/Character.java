/**
 * Character holder. All foreground actions of a character should go here (name, dead, move, etc).
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.elements;

import com.badlogic.gdx.graphics.Color;
import com.mob.client.Game;
import com.mob.client.interfaces.IConstants;
import com.mob.client.sprites.CharacterSprite;

public class Character extends CharacterSprite implements IConstants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private boolean mDead;
	private boolean mInvisible;
	private boolean mSeesRoof;
	private boolean mFocused;
	private int mLastUserPosX;
	private int mLastUserPosY;
	private int mCharIndex;
	private int mLightIndex;

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
		this.mSeesRoof = false;
		this.mFocused = false;
		this.mLightIndex = 0;
		this.mName = "";
		
		// Update user position
		this.setUserPos(x, y);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void draw() {
		
	}
	
	@Override
	public void update(float dt) {

		// Update internal timers
		this.mDeltaTime = dt;
		
		// Turn off light if under roof
		if(this.isUnderRoof() && this.mLightIndex > 0) 
			this.mGame.getEngine().getLightHandler().getLight(this.mLightIndex).setActive(false);
		else if(!this.isUnderRoof() && this.mLightIndex > 0)
			this.mGame.getEngine().getLightHandler().getLight(this.mLightIndex).setActive(true);

		// Render sprite
		super.update(dt);
		
		// Update sprite Position
		this.place();
	}
	
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
		
		// Calculate next user position and finish momvement if we reached destination
		if(this.mNextX != 0) {

			// Calculate moving offset
			offsetCounterX = this.mNextX * this.mSpeed * this.mDeltaTime; 
			this.mX += offsetCounterX;

			// Check if we reached destination
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
			
			// Calculate moving offset
			offsetCounterY = this.mNextY * this.mSpeed * this.mDeltaTime; 
			this.mY += offsetCounterY;
			
			// Check if we reached destination
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
		
		// Update light position
		this.updateLight(this.mX, this.mY);

		// If moving and focused we set camera position to ourselves
		if(this.mFocused && this.mMoving) this.focusCamera();
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

	/**
	 * @return the mFocused
	 */
	public boolean isFocused() {
		return mFocused;
	}

	/**
	 * @param mFocused the mFocused to set
	 */
	public void setFocused(boolean mFocused) {
		
		// Set our focus
		this.mFocused = mFocused;
		
		// Fix for new focused characters not drawing (wasn't in old camera offset)
		if(this.mFocused) this.focusCamera();
	}
	
	/**
	 * @return the mLightIndex
	 */
	public int getLight() {
		return mLightIndex;
	}

	/**
	 * @param mLightIndex the mLightIndex to set
	 */
	public void setLight(int mLightIndex) {
		this.mLightIndex = mLightIndex;
	}

	/**
	 * Set focus to this character
	 */
	public void setFocus() {
		this.setFocused(true);
	}
	
	/**
	 * Unset focus
	 */
	public void unsetFocus() {
		this.mFocused = false;
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
		
		// Vars
		float halfWindowWidth = 0, halfWindowHeight = 0, newPosX = 0, newPosY = 0;
		
		// Fill vars
		halfWindowWidth = this.mGame.getCamera().viewportWidth / 2;
		halfWindowHeight = this.mGame.getCamera().viewportHeight / 2;
		
		// Do not move camera if in map bounds
		if(this.mX - halfWindowWidth < TILE_PIXEL_WIDTH || this.mX + halfWindowWidth > MAX_MAP_SIZE_WIDTH * TILE_PIXEL_WIDTH)
			newPosX = this.mGame.getCamera().position.x;
		else
			newPosX = this.mX;
		
		if(this.mY - halfWindowHeight < TILE_PIXEL_HEIGHT || this.mY + halfWindowHeight > MAX_MAP_SIZE_HEIGHT * TILE_PIXEL_HEIGHT)
			newPosY = this.mGame.getCamera().position.y;
		else
			newPosY = this.mY;
		
		//Update position
		this.mGame.getCamera().position.set(newPosX, newPosY, 0);
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
	
	public void createLight(Color pColor, int pSize, float pSpeed) {
		this.mLightIndex = this.mGame.getEngine().getLightHandler().createLight(this.mUserPosY, this.mUserPosX, pColor, pSize, pSpeed);
	}
	
	public void updateLight(float pX, float pY) {
		if(this.mLightIndex != 0) this.mGame.getEngine().getLightHandler().moveLight(this.mLightIndex, pX, pY);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
