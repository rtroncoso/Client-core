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
	private Game _game;

	// ===========================================================
	// Constructors
	// ===========================================================
	public BundledAnimation (Game _game, int grhIndex) {
		this._game = _game;
		this.mAnimationTime = 0.0f;
		
		GrhData grh = this._game.getGrhData().get(grhIndex);
		int numFrames = grh.getFrames().length;
		TextureRegion[] frames = new TextureRegion[numFrames];
		
		for(int i = 0; i < numFrames; i++) {
			GrhData frame = this._game.getGrhData().get(grh.getFrames()[i]);
			Texture texture = this._game.getSurfaceHandler().get(String.valueOf(frame.getFileNum()));
			frames[i] = new TextureRegion(texture, frame.getX(), frame.getY(), frame.getPixelWidth(), frame.getPixelHeight());
			//frames[i].flip(false, true);
		}
		
		this.setFrames(frames);
		this.mAnimation = new Animation((float) grh.getSpeed() / 1000, frames);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	/*public TextureRegion getKeyFrame (float stateTime, boolean loop) {
		int frameNumber = (int)(stateTime / this.mAnimframeDuration);

		if (!loop) {
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
			if (frameNumber == keyFrames.length - 1) {
				sendEvent();
			}
		} else {
			frameNumber = frameNumber % keyFrames.length;
		}
		
		return keyFrames[frameNumber];
	}*/

	// ===========================================================
	// Inner and Anonymous Classes
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
}
