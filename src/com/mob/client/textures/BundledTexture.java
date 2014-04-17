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
	private float mX;
	private float mY;

	// ===========================================================
	// Constructors
	// ===========================================================
	public BundledTexture(Game _game, int grhIndex) {
		GrhData grh = _game.getGrhData().get(grhIndex);
		
		this.mTextureRegion = new TextureRegion(_game.getSurfaceHandler().get(
										String.valueOf(grh.getFileNum())), grh.getX(), grh.getY(), grh.getPixelWidth(), grh.getPixelHeight());
		this.mTextureRegion.flip(false, true);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mTextureRegion
	 */
	public TextureRegion getGraphic() {
		return mTextureRegion;
	}

	/**
	 * @param mTextureRegion the mTextureRegion to set
	 */
	public void setGraphic(TextureRegion mTextureRegion) {
		this.mTextureRegion = mTextureRegion;
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
