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
	
	protected float mAnimationTime;
	protected float mDeltaTime;
	
	protected int mHeadOffsetX;
	protected int mHeadOffsetY;

	protected Rectangle mCharacter;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public CharacterSprite(Game _game, float x, float y, byte mHeading, int bodyIndex, int headIndex, int helmetIndex) {
		super(_game, x , y);
		
		this.mHeading = mHeading;
		
		this.mDeltaTime = 0.0f;
		this.mAnimationTime = 0.0f;
		
		if(helmetIndex == 0) helmetIndex = 2;
		
		this.loadBody(this._game.getBodyData().get(bodyIndex));
		this.loadHead(this._game.getHeadData().get(headIndex));
		this.loadHelmet(this._game.getHelmetData().get(helmetIndex));
		
		this.mHeadOffsetX = this._game.getBodyData().get(bodyIndex).getHeadOffsetX();
		this.mHeadOffsetY = this._game.getBodyData().get(bodyIndex).getHeadOffsetY();
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
		
		// Update animations
		this.mDeltaTime = dt;
		this.mAnimationTime = this.mBodySkin[this.mHeading].getAnimationTime() + this.mDeltaTime;
		this.mBodySkin[this.mHeading].setAnimationTime(this.mAnimationTime);
		
		// Init spritebatch
		this._game.spriteBatch.begin(); 
		if(this.mHeadGrhIndex != 0) {
			if(this._visible) {
				if(this.mBodyGrhIndex != 0) 
					this._game.spriteBatch.draw(this.getBody(), this._x, this._y);
						
				if(this.mHeadGrhIndex != 0) {
					this._game.spriteBatch.draw(this.getHead(), this._x + this.mHeadOffsetX + 4, this._y + (this.mHeadOffsetY * 2));
				
					if(this.mHelmetGrhIndex != 0)
						this._game.spriteBatch.draw(this.getHelmet(), this._x + this.mHeadOffsetX + 4, this._y + (this.mHeadOffsetY * 2) + OFFSET_HEAD);
				}
			}
		} else { // Draw only body
			if(this.mBodyGrhIndex != 0) 
					this._game.spriteBatch.draw(this.getBody(), this._x, this._y);
		}
		this._game.spriteBatch.end();
		
		// Update sprite Position
		this.place();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public TextureRegion getBody() {
		if(this.mMoving)
			return this.mBodySkin[this.mHeading].getAnimation().getKeyFrame(this.mAnimationTime, true);
		else
			return this.mBodySkin[this.mHeading].getFrames()[0];
	}
	
	public TextureRegion getHead() {
		return this.mHeadSkin[this.mHeading].getTextureRegion();
	}
	
	public TextureRegion getHelmet() {
		return this.mHelmetSkin[this.mHeading].getTextureRegion();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	public void loadBody(BodyData bodyData) {
		this.mBodyGrhIndex = bodyData.getBodyArray()[WALK_NORTH].getGrhIndex();
		this.mBodySkin = new BundledAnimation[4];
		this.mBodySkin[WALK_NORTH] = new BundledAnimation(this._game, bodyData.getBodyArray()[WALK_NORTH].getGrhIndex());
		this.mBodySkin[WALK_SOUTH] = new BundledAnimation(this._game, bodyData.getBodyArray()[WALK_SOUTH].getGrhIndex());
		this.mBodySkin[WALK_WEST] = new BundledAnimation(this._game, bodyData.getBodyArray()[WALK_WEST].getGrhIndex());
		this.mBodySkin[WALK_EAST] = new BundledAnimation(this._game, bodyData.getBodyArray()[WALK_EAST].getGrhIndex());
	}
	
	public void loadHead(HeadData headData) {
		this.mHeadSkin = new BundledTexture[4];
		this.mHeadGrhIndex = headData.getHeadIndex()[WALK_NORTH];
		this.mHeadSkin[WALK_NORTH] = new BundledTexture(this._game, headData.getHeadIndex()[WALK_NORTH]);
		this.mHeadSkin[WALK_SOUTH] = new BundledTexture(this._game, headData.getHeadIndex()[WALK_SOUTH]);
		this.mHeadSkin[WALK_WEST] = new BundledTexture(this._game, headData.getHeadIndex()[WALK_WEST]);
		this.mHeadSkin[WALK_EAST] = new BundledTexture(this._game, headData.getHeadIndex()[WALK_EAST]);
	}
	
	public void loadHelmet(HelmetData helmetData) {
		this.mHelmetSkin = new BundledTexture[4];
		this.mHelmetGrhIndex = helmetData.getHelmetIndex()[WALK_NORTH];
		this.mHelmetSkin[WALK_NORTH] = new BundledTexture(this._game, helmetData.getHelmetIndex()[WALK_NORTH]);
		this.mHelmetSkin[WALK_SOUTH] = new BundledTexture(this._game, helmetData.getHelmetIndex()[WALK_SOUTH]);
		this.mHelmetSkin[WALK_WEST] = new BundledTexture(this._game, helmetData.getHelmetIndex()[WALK_WEST]);
		this.mHelmetSkin[WALK_EAST] = new BundledTexture(this._game, helmetData.getHelmetIndex()[WALK_EAST]);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
