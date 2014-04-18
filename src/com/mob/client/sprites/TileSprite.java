/**
 * Holds a drawable tile information 
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.sprites;

import com.mob.client.Game;
import com.mob.client.interfaces.IConstants;
import com.mob.client.textures.BundledTexture;

public class TileSprite extends GameSprite implements IConstants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected BundledTexture[] mGraphic;
	protected int[] mGrhIndex;
	protected float mDeltaTime;
	protected float mAlphaBlend;

	// ===========================================================
	// Constructors
	// ===========================================================
	public TileSprite(Game _game, int x, int y, int[] pGraphic) {
		super(_game, x, y);
		
		this.mGraphic = new BundledTexture[4];
		this.mGrhIndex = pGraphic;
		this.mAlphaBlend = 1.0f;
		
		for(int i = 0; i < 4; i++) {
			if(_game.getGrhData().get(pGraphic[i]).getFrames().length != 0) 
				this.mGraphic[i] = new BundledTexture(_game, pGraphic[i], true);
			else
				this.mGraphic[i] = new BundledTexture(_game, pGraphic[i]);
			
			// Correctly plot graphic position
			this.mGraphic[i].setX((x * TILE_PIXEL_WIDTH) - (this.mGraphic[i].getGraphic().getRegionWidth() / 2));
			this.mGraphic[i].setY((y * TILE_PIXEL_HEIGHT) - this.mGraphic[i].getGraphic().getRegionHeight());
		}
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mGraphic
	 */
	public BundledTexture[] getGraphicArray() {
		return mGraphic;
	}

	/**
	 * @param mGraphic the mGraphic to set
	 */
	public void setGraphicArray(BundledTexture[] mGraphic) {
		this.mGraphic = mGraphic;
	}
	
	public BundledTexture getGraphic(int pIndex) {
		return this.mGraphic[pIndex];
	}
	
	public int getGrhIndex(int pIndex) {
		return this.mGrhIndex[pIndex];
	}

	/**
	 * @return the mAlphaBlend
	 */
	public float getAlphaBlend() {
		return mAlphaBlend;
	}

	/**
	 * @param mAlphaBlend the mAlphaBlend to set
	 */
	public void setAlphaBlend(float mAlphaBlend) {
		this.mAlphaBlend = mAlphaBlend;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
