/**
 * Holder of the Character Sprite data. Texture handling and rendering of the character goes here.
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.sprites;

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
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public CharacterSprite(Game _game, int x, int y, byte mHeading, int bodyIndex, int headIndex, int helmetIndex) {
		super(_game, x , y);
		
		// Init class
		this.mHeading = mHeading;
		this.mDeltaTime = 0.0f;
		
		// Check null indexes
		if(helmetIndex == 0) helmetIndex = 2;
		
		// Load graphics
		this.loadBody(this.mGame.getBodyData().get(bodyIndex));
		this.loadHead(this.mGame.getHeadData().get(headIndex));
		this.loadHelmet(this.mGame.getHelmetData().get(helmetIndex));
		
		// Calculate offsets
		this.mHeadOffsetX = this.mGame.getBodyData().get(bodyIndex).getHeadOffsetX();
		this.mHeadOffsetY = this.mGame.getBodyData().get(bodyIndex).getHeadOffsetY();
		
		// Update user pos
		this.setUserPosX(x);
		this.setUserPosY(y);
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void draw() {
	}
	
	/**
	 * Sub CharRender()
	 */
	@Override
	public void update(float dt) {
		
		// Vars
		float bodyPixelOffsetX, bodyPixelOffsetY, headPixelOffsetX, headPixelOffsetY;
		
		// Update internal timer
		this.mDeltaTime = dt;
		this.mBodySkin[this.mHeading].setAnimationTime(this.mBodySkin[this.mHeading].getAnimationTime() + this.mDeltaTime);
		
		// Calculate offset to draw
		bodyPixelOffsetX = this.mX - (this.getBody().getRegionWidth() / 2);
		bodyPixelOffsetY = this.mY - (this.getBody().getRegionHeight());
		headPixelOffsetX = this.mX + this.mHeadOffsetX - (this.getHead().getRegionWidth() / 4) - 4;
		headPixelOffsetY = this.mY + this.mHeadOffsetY - this.getBody().getRegionHeight() - 4;
		
		// Draw our character
		if(this.mHeadGrhIndex != 0) {
			if(this.mVisible) {
				if(this.mBodyGrhIndex != 0) 
					this.mGame.getSpriteBatch().draw(this.getBody(), bodyPixelOffsetX, bodyPixelOffsetY);
						
				if(this.mHeadGrhIndex != 0) {
					this.mGame.getSpriteBatch().draw(this.getHead(), headPixelOffsetX, headPixelOffsetY);
				
					if(this.mHelmetGrhIndex != 0)
						this.mGame.getSpriteBatch().draw(this.getHelmet(), headPixelOffsetX, headPixelOffsetY);
				}
			}
		} else { // Draw only body
			if(this.mBodyGrhIndex != 0) 
				this.mGame.getSpriteBatch().draw(this.getBody(), bodyPixelOffsetX, bodyPixelOffsetY);
		}
		
		// Update sprite Position
		this.place();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public TextureRegion getBody() {
		if(this.mMoving)
			return this.mBodySkin[this.mHeading].getGraphic(true);
		else
			return this.mBodySkin[this.mHeading].getGraphic();
	}
	
	public TextureRegion getHead() {
		return this.mHeadSkin[this.mHeading].getGraphic();
	}
	
	public TextureRegion getHelmet() {
		return this.mHelmetSkin[this.mHeading].getGraphic();
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
		this.mBodyGrhIndex = bodyData.getBodyArray()[WALK_NORTH].getGrhIndex();
		this.mBodySkin = new BundledTexture[4];
		this.mBodySkin[WALK_NORTH] = new BundledTexture(this.mGame, bodyData.getBodyArray()[WALK_NORTH].getGrhIndex(), true);
		this.mBodySkin[WALK_SOUTH] = new BundledTexture(this.mGame, bodyData.getBodyArray()[WALK_SOUTH].getGrhIndex(), true);
		this.mBodySkin[WALK_WEST] = new BundledTexture(this.mGame, bodyData.getBodyArray()[WALK_WEST].getGrhIndex(), true);
		this.mBodySkin[WALK_EAST] = new BundledTexture(this.mGame, bodyData.getBodyArray()[WALK_EAST].getGrhIndex(), true);
	}
	
	public void loadHead(HeadData headData) {
		this.mHeadSkin = new BundledTexture[4];
		this.mHeadGrhIndex = headData.getHeadIndex()[WALK_NORTH];
		this.mHeadSkin[WALK_NORTH] = new BundledTexture(this.mGame, headData.getHeadIndex()[WALK_NORTH]);
		this.mHeadSkin[WALK_SOUTH] = new BundledTexture(this.mGame, headData.getHeadIndex()[WALK_SOUTH]);
		this.mHeadSkin[WALK_WEST] = new BundledTexture(this.mGame, headData.getHeadIndex()[WALK_WEST]);
		this.mHeadSkin[WALK_EAST] = new BundledTexture(this.mGame, headData.getHeadIndex()[WALK_EAST]);
	}
	
	public void loadHelmet(HelmetData helmetData) {
		this.mHelmetSkin = new BundledTexture[4];
		this.mHelmetGrhIndex = helmetData.getHelmetIndex()[WALK_NORTH];
		this.mHelmetSkin[WALK_NORTH] = new BundledTexture(this.mGame, helmetData.getHelmetIndex()[WALK_NORTH]);
		this.mHelmetSkin[WALK_SOUTH] = new BundledTexture(this.mGame, helmetData.getHelmetIndex()[WALK_SOUTH]);
		this.mHelmetSkin[WALK_WEST] = new BundledTexture(this.mGame, helmetData.getHelmetIndex()[WALK_WEST]);
		this.mHelmetSkin[WALK_EAST] = new BundledTexture(this.mGame, helmetData.getHelmetIndex()[WALK_EAST]);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
