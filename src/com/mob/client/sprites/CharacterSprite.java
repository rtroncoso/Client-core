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
import com.mob.client.textures.BundledAnimation;
import com.mob.client.textures.BundledTexture;

public class CharacterSprite extends MovingSprite implements ISprite, IConstants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected BundledAnimation[] mBodySkin;
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
		
		this.mHeading = mHeading;
		
		this.mDeltaTime = 0.0f;
		
		if(helmetIndex == 0) helmetIndex = 2;
		
		this.loadBody(this.mGame.getBodyData().get(bodyIndex));
		this.loadHead(this.mGame.getHeadData().get(headIndex));
		this.loadHelmet(this.mGame.getHelmetData().get(helmetIndex));
		
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
		float pixelOffsetX, pixelOffsetY;
		
		// Update animations
		this.mDeltaTime = dt;
		this.mBodySkin[this.mHeading].setAnimationTime(this.mBodySkin[this.mHeading].getAnimationTime() + this.mDeltaTime);
		
		// Calculate offset to draw
		pixelOffsetX = this.mX - (this.getBody().getRegionWidth() / 2);
		pixelOffsetY = this.mY - (this.getBody().getRegionHeight());
		
		// Init spritebatch
		this.mGame.getSpriteBatch().begin(); 
		if(this.mHeadGrhIndex != 0) {
			if(this.mVisible) {
				if(this.mBodyGrhIndex != 0) 
					this.mGame.getSpriteBatch().draw(this.getBody(), pixelOffsetX, pixelOffsetY);
						
				if(this.mHeadGrhIndex != 0) {
					this.mGame.getSpriteBatch().draw(this.getHead(), pixelOffsetX + this.mHeadOffsetX + (this.getHead().getRegionWidth() / 2) - 4, pixelOffsetY + (this.mHeadOffsetY * 2));
				
					if(this.mHelmetGrhIndex != 0)
						this.mGame.getSpriteBatch().draw(this.getHelmet(), pixelOffsetX + this.mHeadOffsetX + (this.getHelmet().getRegionWidth() / 2) - 4, pixelOffsetY + (this.mHeadOffsetY * 2));
				}
			}
		} else { // Draw only body
			if(this.mBodyGrhIndex != 0) 
				this.mGame.getSpriteBatch().draw(this.getBody(), pixelOffsetX, pixelOffsetY);
		}
		this.mGame.getSpriteBatch().end();
		
		// Update sprite Position
		this.place();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public TextureRegion getBody() {
		if(this.mMoving)
			return this.mBodySkin[this.mHeading].getAnimation().getKeyFrame(this.mBodySkin[this.mHeading].getAnimationTime(), true);
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
		this.mBodySkin = new BundledAnimation[4];
		this.mBodySkin[WALK_NORTH] = new BundledAnimation(this.mGame, bodyData.getBodyArray()[WALK_NORTH].getGrhIndex());
		this.mBodySkin[WALK_SOUTH] = new BundledAnimation(this.mGame, bodyData.getBodyArray()[WALK_SOUTH].getGrhIndex());
		this.mBodySkin[WALK_WEST] = new BundledAnimation(this.mGame, bodyData.getBodyArray()[WALK_WEST].getGrhIndex());
		this.mBodySkin[WALK_EAST] = new BundledAnimation(this.mGame, bodyData.getBodyArray()[WALK_EAST].getGrhIndex());
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
