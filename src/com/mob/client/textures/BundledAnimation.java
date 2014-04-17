/**
 * Packages and loads an animation in an array of TextureRegions by a given GrhIndex
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.textures;

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
	private BundledTexture[] mFrames;
	private float mX;
	private float mY;

	// ===========================================================
	// Constructors
	// ===========================================================
	public BundledAnimation (Game _game, int grhIndex) {
		this(_game, grhIndex, true);
	}
	
	public BundledAnimation (Game _game, int grhIndex, boolean pAnimated) {
		this.mAnimationTime = 0.0f;
		
		if(!pAnimated) {
			this.mFrames = new BundledTexture[1];
			this.mFrames[0] = new BundledTexture(_game, grhIndex);
		} else {
			GrhData grh = _game.getGrhData().get(grhIndex);
			int numFrames = grh.getFrames().length;
			
			this.mFrames = new BundledTexture[numFrames];
			TextureRegion tmpFrames[] = new TextureRegion[numFrames];
			for(int i = 0; i < numFrames; i++) {
				this.mFrames[i] = new BundledTexture(_game, grh.getFrame(i));
				tmpFrames[i] = this.mFrames[i].getGraphic();
			}
			this.mAnimation = new Animation((float) grh.getSpeed() / 1000, tmpFrames);
		}
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
	public BundledTexture[] getFrames() {
		return mFrames;
	}

	/**
	 * @param mFrames the mFrames to set
	 */
	public void setFrames(BundledTexture[] mFrames) {
		this.mFrames = mFrames;
	}
	
	public TextureRegion getGraphic() {
		return this.getGraphic(0);
	}

	
	public TextureRegion getGraphic(int pIndex) {
		return this.mFrames[pIndex].getGraphic();
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
