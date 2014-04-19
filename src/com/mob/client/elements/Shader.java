/**
 * Stores information about a shader
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.mob.client.Game;
import com.mob.client.interfaces.IConstants;

public class Shader implements IConstants {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private Texture mTexture;
	private boolean mActive;
	private float mAngle;
	private float mSpeed;
	private Color mColor;
	private Game mGame;
	private float mSize;
	private float mX;
	private float mY;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Shader(Game mGame, int mX, int mY, String mTexture, Color mColor) {
		this(mGame, mX, mY, mTexture, mColor, 3);
	}
	
	public Shader(Game mGame, int mX, int mY, String mTexture, Color mColor, int pSize) {
		this(mGame, mX, mY, mTexture, mColor, pSize, 0);
	}
	
	/**
	 * @param mX
	 * @param mY
	 * @param mTexture
	 * @param mColor
	 * @param mAngle
	 * @param mSpeed
	 */
	public Shader(Game mGame, int mX, int mY, String mTexture, Color mColor, int pSize, float mSpeed) {
		super();
		this.mGame = mGame;
		this.mTexture = new Texture(GAME_SHADERS_PATH + mTexture);
		this.mX = mX * TILE_PIXEL_WIDTH;
		this.mY = mY * TILE_PIXEL_HEIGHT;
		this.mColor = mColor;
		this.mSize = pSize * 100.0f;
		this.mSpeed = mSpeed;
		this.mActive = true;
		this.mAngle = 0.0f;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mAngle
	 */
	public float getAngle() {
		return mAngle;
	}
	
	/**
	 * @param mAngle the mAngle to set
	 */
	public void setAngle(float mAngle) {
		this.mAngle = mAngle;
	}
	
	/**
	 * @return the mSpeed
	 */
	public float getSpeed() {
		return mSpeed;
	}
	
	/**
	 * @param mSpeed the mSpeed to set
	 */
	public void setSpeed(float mSpeed) {
		this.mSpeed = mSpeed;
	}
	
	/**
	 * @return the mX
	 */
	public float getX() {
		return mX;
	}
	
	/**
	 * @param mX the mX to set
	 */
	public void setX(float mX) {
		this.mX = mX;
	}
	
	/**
	 * @return the mY
	 */
	public float getY() {
		return mY;
	}
	
	/**
	 * @param mY the mY to set
	 */
	public void setY(float mY) {
		this.mY = mY;
	}

	/**
	 * @return the mColor
	 */
	public Color getColor() {
		return mColor;
	}
	
	/**
	 * @param mColor the mColor to set
	 */
	public Color setColor(Color mColor) {
		return this.mColor = mColor;
	}
	
	/**
	 * @return the mTexture
	 */
	public Texture getTexture() {
		return mTexture;
	}

	/**
	 * @param mTexture the mTexture to set
	 */
	public void setTexture(Texture mTexture) {
		this.mTexture = mTexture;
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	public void update(float dt) {
		
		// Store our drawing size
		float shaderSize = this.mSize;
		
		 // Animate shader size
		if(this.mSpeed > 0) {
			this.mAngle += dt * this.mSpeed;
			while(this.mAngle > PI2)
				this.mAngle -= PI2;
			shaderSize = ((shaderSize - 15.0f) + 15.0f * (float)Math.sin(this.mAngle) + .2f * MathUtils.random());
		}
		
		// Render our shader into the lightmap
		Color oldColor = this.mGame.getSpriteBatch().getColor();
		this.mGame.getSpriteBatch().setColor(this.mColor);
		this.mGame.getSpriteBatch().draw(this.mTexture, this.mX - (shaderSize * 0.5f), this.mY - (shaderSize * 0.5f), shaderSize, shaderSize);
		this.mGame.getSpriteBatch().setColor(oldColor);
	}

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

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
