/**
 * Holds a drawable tile information 
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.sprites;

import com.mob.client.Game;
import com.mob.client.interfaces.IConstants;
import com.mob.client.textures.BundledAnimation;

public class TileSprite extends GameSprite implements IConstants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected BundledAnimation[] mGraphic;
	protected float mDeltaTime;

	// ===========================================================
	// Constructors
	// ===========================================================
	public TileSprite(Game _game, int x, int y, int[] pGraphic) {
		super(_game, x, y);
		
		this.mGraphic = new BundledAnimation[4];
		
		for(int i = 0; i < 4; i++) {
			if(_game.getGrhData().get(pGraphic[i]).getSpeed() != 0) 
				this.mGraphic[i] = new BundledAnimation(_game, pGraphic[i]);
			else
				this.mGraphic[i] = new BundledAnimation(_game, pGraphic[i], false);
			
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
	public BundledAnimation[] getGraphicArray() {
		return mGraphic;
	}

	/**
	 * @param mGraphic the mGraphic to set
	 */
	public void setGraphicArray(BundledAnimation[] mGraphic) {
		this.mGraphic = mGraphic;
	}
	
	public BundledAnimation getGraphic(int pIndex) {
		return this.mGraphic[pIndex];
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
