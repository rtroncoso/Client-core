/**
 * Packages and loads an animation in an array of TextureRegions by a given GrhIndex
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.Game;
import com.mob.client.data.GrhData;

public class BundledAnimation {
	
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private Animation mAnimation;
	private float mAnimationTime;
	private TextureRegion[] mFrames;
	private float mX;
	private float mY;

	// ===========================================================
	// Constructors
	// ===========================================================
	public BundledAnimation (Game _game, int grhIndex) {
		this.mAnimationTime = 0.0f;
		
		GrhData grh = _game.getGrhData().get(grhIndex);
		int numFrames = grh.getFrames().length;
		TextureRegion[] frames = new TextureRegion[numFrames];
		
		if(numFrames == 1) {
			Texture texture = _game.getSurfaceHandler().get(String.valueOf(grh.getFileNum()));
			frames[0] = new TextureRegion(texture, grh.getX(), grh.getY(), grh.getPixelWidth(), grh.getPixelHeight());
		} else {
			for(int i = 0; i < numFrames; i++) {
				GrhData frame = _game.getGrhData().get(grh.getFrames()[i]);
				Texture texture = _game.getSurfaceHandler().get(String.valueOf(frame.getFileNum()));
				frames[i] = new TextureRegion(texture, frame.getX(), frame.getY(), frame.getPixelWidth(), frame.getPixelHeight());
				frames[i].flip(false, true);
			}
			this.mAnimation = new Animation((float) grh.getSpeed() / 1000, frames);
		}
		
		this.setFrames(frames);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mAnimation
	 */
	public Animation getAnimation() {
		return mAnimation;
	}

	/**
	 * @param mAnimation the mAnimation to set
	 */
	public void setAnimation(Animation mAnimation) {
		this.mAnimation = mAnimation;
	}

	/**
	 * @return the mAnimationTime
	 */
	public float getAnimationTime() {
		return mAnimationTime;
	}

	/**
	 * @param mAnimationTime the mAnimationTime to set
	 */
	public void setAnimationTime(float mAnimationTime) {
		this.mAnimationTime = mAnimationTime;
	}

	/**
	 * @return the mFrames
	 */
	public TextureRegion[] getFrames() {
		return mFrames;
	}

	/**
	 * @param mFrames the mFrames to set
	 */
	public void setFrames(TextureRegion[] mFrames) {
		this.mFrames = mFrames;
	}
	
	public TextureRegion getGraphic(int pIndex) {
		return this.mFrames[pIndex];
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
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
}
