/**
 * Basic sprite with no texture or rendering
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mob.client.Game;
import com.mob.client.interfaces.ISprite;

public class GameSprite extends Sprite implements ISprite {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected boolean mActive;
	protected boolean mVisible;
	
	protected int mWidth;
	protected int mHeight;
	
	protected float mX;
	protected float mY;
	
	protected Game mGame;

	// ===========================================================
	// Constructors
	// ===========================================================
	public GameSprite(Game _game, float x, float y) {
		this.mGame = _game;
		
		this.mX = x;
		this.mY = y;
		
		this.mActive = true;
		this.mVisible = true;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void reset() {}
	public void update(float dt) {}
	public void show() {
		this.mVisible = true;
	}
	public void hide() {
		this.mVisible = false;
	}
	public void draw() {}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mActive
	 */
	public boolean isActive() {
		return mActive;
	}

	/**
	 * @param mActive the mActive to set
	 */
	public void setActive(boolean mActive) {
		this.mActive = mActive;
	}

	/**
	 * @return the mVisible
	 */
	public boolean isVisible() {
		return mVisible;
	}

	/**
	 * @param mVisible the mVisible to set
	 */
	public void setVisible(boolean mVisible) {
		this.mVisible = mVisible;
	}

	/**
	 * @param mHeight the mHeight to set
	 */
	public void setHeight(int mHeight) {
		this.mHeight = mHeight;
	}
	
	/**
	 * @return the _x
	 */
	public float getX() {
		return mX;
	}

	/**
	 * @param _x the _x to set
	 */
	public void setX(float _x) {
		this.mX = _x;
	}

	/**
	 * @return the _y
	 */
	public float getY() {
		return mY;
	}

	/**
	 * @param _y the _y to set
	 */
	public void setY(int _y) {
		this.mY = _y;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public float right () {
		return mX + mWidth;
	}
	
	public float left () {
		return mX;
	}
	
	public float top () {
		return mY + mHeight;
	}
	
	public float bottom () {
		return mY;
	}
	
	public Rectangle bounds () {
		return new Rectangle(mX + mWidth * 0.2f, mY + mHeight * 0.2f, mWidth * 0.8f, mHeight * 0.8f);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
