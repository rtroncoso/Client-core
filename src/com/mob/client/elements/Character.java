/**
 * Character holder. All foreground actions of a character should go here (name, dead, etc).
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
		super(_game, x * TILE_PIXEL_WIDTH, y * TILE_PIXEL_HEIGHT, mHeading, bodyIndex, headIndex, helmetIndex);
		
		this.mDead = false;
		this.mInvisible = false;
		this.mNombre = "";
		
		this.mUserPosX = x;
		this.mUserPosY = y;
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
		this.mUserPosY = mUserPosY;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public void move(byte pHeading) {
		this.mHeading = pHeading;
		switch(this.mHeading) {
			case(WALK_NORTH):
				return;
			case(WALK_SOUTH):
				return;
			case(WALK_EAST):
				return;
			case(WALK_WEST):
				return;
		}
	}
	
	public void moveUp() {
		//if(this._moving) return;
		this.mBodySkin[WALK_NORTH].setAnimationTime(0);
		this.move(WALK_NORTH);
	}
	
	public void moveDown() {
		//if(this._moving) return;
		this.mBodySkin[WALK_SOUTH].setAnimationTime(0);
		this.move(WALK_SOUTH);
	}
	
	public void moveLeft() {
		//if(this._moving) return;
		this.mBodySkin[WALK_WEST].setAnimationTime(0);
		this.move(WALK_WEST);
	}
	
	public void moveRight() {
		//if(this._moving) return;
		this.mBodySkin[WALK_EAST].setAnimationTime(0);
		this.move(WALK_EAST);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
