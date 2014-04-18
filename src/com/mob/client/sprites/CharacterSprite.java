/**
 * Holder of the Character Sprite data. Texture handling and rendering of the character goes here.
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mob.client.Game;
import com.mob.client.data.BodyData;
import com.mob.client.data.HeadData;
import com.mob.client.data.HelmetData;
import com.mob.client.interfaces.IConstants;
import com.mob.client.interfaces.ISprite;
import com.mob.client.textures.BundledTexture;

public class CharacterSprite extends MovingSprite implements ISprite, IConstants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected BundledTexture[] mBodySkin;
	protected int mBodyGrhIndex;
	protected BundledTexture[] mHeadSkin;
	protected int mHeadGrhIndex;
	protected BundledTexture[] mHelmetSkin;
	protected int mHelmetGrhIndex;
	
	protected int mUserPosX;
	protected int mUserPosY;
	
	protected float mDeltaTime;
	
	protected int mHeadOffsetX;
	protected int mHeadOffsetY;

	protected Rectangle mCharacter;
	protected Color mColor;
	
	protected String mName;
	protected BitmapFont mFont;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public CharacterSprite(Game _game, int x, int y, Heading mHeading, int bodyIndex, int headIndex, int helmetIndex) {
		super(_game, x , y);
		
		// Init class
		this.mColor = this.mGame.getEngine().getTint();
		this.mFont = this.mGame.getFont();
		this.mFont.setColor(0.0f, 0.6f, 0.0f, 1.0f);
		this.mHeading = mHeading;
		this.mDeltaTime = 0.0f;
		this.mBodyGrhIndex = 0;
		this.mHeadGrhIndex = 0;
		this.mHelmetGrhIndex = 0;
		
		// Check null indexes
		if(helmetIndex == 0) helmetIndex = 2;
		
		// Load graphics
		if(bodyIndex != 0) this.loadBody(this.mGame.getBodyData().get(bodyIndex));
		if(headIndex != 0) this.loadHead(this.mGame.getHeadData().get(headIndex));
		if(helmetIndex != 0) this.loadHelmet(this.mGame.getHelmetData().get(helmetIndex));
		
		// Calculate offsets
		this.mHeadOffsetX = this.mGame.getBodyData().get(bodyIndex).getHeadOffsetX();
		this.mHeadOffsetY = this.mGame.getBodyData().get(bodyIndex).getHeadOffsetY();
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void dispose() {
	}
	
	@Override
	public void draw() {}
	
	/**
	 * Sub CharRender()
	 */
	@Override
	public void update(float dt) {
		
		// Vars
		float bodyPixelOffsetX = 0, bodyPixelOffsetY = 0, headPixelOffsetX = 0, headPixelOffsetY = 0,
				helmetPixelOffsetX = 0, helmetPixelOffsetY = 0;
		Color oldColor = this.mGame.getSpriteBatch().getColor();
		
		
		// Set our sprite color
		this.mGame.getSpriteBatch().setColor(this.mColor);
		
		// Update internal timer
		this.mDeltaTime = dt;
		this.mBodySkin[this.mHeading.toInt()].setAnimationTime(this.mBodySkin[this.getHeading()].getAnimationTime() + this.mDeltaTime);
		
		// Calculate offset to draw
		if(this.mBodyGrhIndex != 0) {
			bodyPixelOffsetX = this.mX - (this.getBody().getRegionWidth() / 2);
			bodyPixelOffsetY = this.mY - (this.getBody().getRegionHeight());
		}
		if(this.mHeadGrhIndex != 0) {
			headPixelOffsetX = this.mX + this.mHeadOffsetX - (this.getHead().getRegionWidth() / 4) - 4;
			headPixelOffsetY = this.mY + this.mHeadOffsetY - this.getBody().getRegionHeight() - 5;
		}
		if(this.mHelmetGrhIndex != 0) {
			helmetPixelOffsetX = this.mX + this.mHeadOffsetX - (this.getHead().getRegionWidth() / 4) - 4;
			helmetPixelOffsetY = this.mY + this.mHeadOffsetY - this.getBody().getRegionHeight() - OFFSET_HEAD - 4;
		}
		
		// Draw our character
		if(this.mHeadGrhIndex != 0) {
			if(this.mVisible) {
				if(this.mBodyGrhIndex != 0) 
					this.mGame.getSpriteBatch().draw(this.getBody(), bodyPixelOffsetX, bodyPixelOffsetY);
						
				if(this.mHeadGrhIndex != 0) {
					this.mGame.getSpriteBatch().draw(this.getHead(), headPixelOffsetX, headPixelOffsetY);
				
					if(this.mHelmetGrhIndex != 0)
						this.mGame.getSpriteBatch().draw(this.getHelmet(), helmetPixelOffsetX, helmetPixelOffsetY);
				}
			}
		} else { // Draw only body
			if(this.mBodyGrhIndex != 0) 
				this.mGame.getSpriteBatch().draw(this.getBody(), bodyPixelOffsetX, bodyPixelOffsetY);
		}
		
		// Draw name over our head
		if(this.mName.length() != 0) {
			
			// To add new lines to our name just go \nNew Line (add clan, staff, etc)
			String line = this.mName;// + "\n<Mob Staff>";
			this.mFont.drawMultiLine(this.mGame.getSpriteBatch(), line, this.mX - ((this.mName.length() * 10) / 2), this.mY - 4, this.mName.length() * 10, HAlignment.CENTER);
		}
		
		// Replace old color
		this.mGame.getSpriteBatch().setColor(oldColor);
		
		// Update sprite Position
		this.place();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public TextureRegion getBody() {
		if(this.mMoving)
			return this.mBodySkin[this.getHeading()].getGraphic(true);
		else
			return this.mBodySkin[this.getHeading()].getGraphic();
	}
	
	public TextureRegion getHead() {
		return this.mHeadSkin[this.getHeading()].getGraphic();
	}
	
	public TextureRegion getHelmet() {
		return this.mHelmetSkin[this.getHeading()].getGraphic();
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
	public void setColor(Color mColor) {
		this.mColor = mColor;
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
		this.mX = (mUserPosX * TILE_PIXEL_WIDTH);
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
		this.mY = (mUserPosY * TILE_PIXEL_HEIGHT);
		this.mUserPosY = mUserPosY;
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	public void loadBody(BodyData bodyData) {
		this.mBodyGrhIndex = bodyData.getGraphic(Heading.NORTH.toInt());
		this.mBodySkin = new BundledTexture[4];
		this.mBodySkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, bodyData.getGraphic(Heading.NORTH.toInt()), true);
		this.mBodySkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, bodyData.getGraphic(Heading.SOUTH.toInt()), true);
		this.mBodySkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, bodyData.getGraphic(Heading.WEST.toInt()), true);
		this.mBodySkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, bodyData.getGraphic(Heading.EAST.toInt()), true);
	}
	
	public void loadHead(HeadData headData) {
		this.mHeadSkin = new BundledTexture[4];
		this.mHeadGrhIndex = headData.getHeadIndex()[Heading.NORTH.toInt()];
		this.mHeadSkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, headData.getHeadIndex()[Heading.NORTH.toInt()]);
		this.mHeadSkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, headData.getHeadIndex()[Heading.SOUTH.toInt()]);
		this.mHeadSkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, headData.getHeadIndex()[Heading.WEST.toInt()]);
		this.mHeadSkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, headData.getHeadIndex()[Heading.EAST.toInt()]);
	}
	
	public void loadHelmet(HelmetData helmetData) {
		this.mHelmetSkin = new BundledTexture[4];
		this.mHelmetGrhIndex = helmetData.getHelmetIndex()[Heading.NORTH.toInt()];
		this.mHelmetSkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmetIndex()[Heading.NORTH.toInt()]);
		this.mHelmetSkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmetIndex()[Heading.SOUTH.toInt()]);
		this.mHelmetSkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmetIndex()[Heading.WEST.toInt()]);
		this.mHelmetSkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmetIndex()[Heading.EAST.toInt()]);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
