/**
 * Packages and loads a Texture into a TextureRegion by a given GrhIndex
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.textures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.Game;
import com.mob.client.data.GrhData;
import com.mob.client.interfaces.IConstants;

public class BundledTexture implements IConstants {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private TextureRegion mTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================
	public BundledTexture(Game _game, int grhIndex) {
		GrhData grh = _game.getGrhData().get(grhIndex);
		
		this.mTextureRegion = new TextureRegion(_game.getSurfaceHandler().get(
										String.valueOf(grh.getFileNum())), grh.getX(), grh.getY(), grh.getPixelWidth(), grh.getPixelHeight());
		this.mTextureRegion.flip(false, true);
	}

	/**
	 * @return the mTextureRegion
	 */
	public TextureRegion getTextureRegion() {
		return mTextureRegion;
	}

	/**
	 * @param mTextureRegion the mTextureRegion to set
	 */
	public void setTextureRegion(TextureRegion mTextureRegion) {
		this.mTextureRegion = mTextureRegion;
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


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


	  

}
