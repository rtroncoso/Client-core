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
import com.badlogic.gdx.utils.TimeUtils;
import com.mob.client.Game;
import com.mob.client.data.BodyData;
import com.mob.client.data.FxData;
import com.mob.client.data.HeadData;
import com.mob.client.data.HelmetData;
import com.mob.client.data.ShieldData;
import com.mob.client.data.WeaponData;
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
	protected BundledTexture[] mWeaponSkin;
	protected int mWeaponGrhIndex;
	protected BundledTexture[] mHeadSkin;
	protected int mHeadGrhIndex;
	protected BundledTexture[] mHelmetSkin;
	protected int mHelmetGrhIndex;
	protected BundledTexture[] mShieldSkin;
	protected int mShieldGrhIndex;
	protected BundledTexture mFxSkin;
	protected int mFxGrhIndex;
	protected int mFxOffsetX;
	protected int mFxOffsetY;
	protected long mFxTimer;
	
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
	public CharacterSprite(Game _game, int x, int y, Heading mHeading, int bodyIndex, int weaponIndex, int headIndex, int helmetIndex, int shieldIndex, int fxIndex) {
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
		this.mWeaponGrhIndex = 0;
		this.mShieldGrhIndex = 0;
		this.mFxGrhIndex = 0;
		this.mFxOffsetX = 0;
		this.mFxOffsetY = 0;
		this.mFxTimer = 0;
		
		// Check null indexes
		if(helmetIndex == 0) helmetIndex = 2;
		if(weaponIndex == 0) weaponIndex = 2;
		if(shieldIndex == 0) shieldIndex = 2;
		
		// Load graphics
		if(bodyIndex != 0) this.loadBody(this.mGame.getBodyData().get(bodyIndex));
		if(weaponIndex != 0) this.loadWeapon(this.mGame.getWeaponData().get(weaponIndex));
		if(shieldIndex != 0) this.loadShield(this.mGame.getShieldData().get(shieldIndex));
		if(headIndex != 0) this.loadHead(this.mGame.getHeadData().get(headIndex));
		if(helmetIndex != 0) this.loadHelmet(this.mGame.getHelmetData().get(helmetIndex));
		if(fxIndex != 0) this.loadFx(this.mGame.getFxData().get(fxIndex));
		
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
		float bodyPixelOffsetX = 0, bodyPixelOffsetY = 0, weaponPixelOffsetX = 0, weaponPixelOffsetY = 0, headPixelOffsetX = 0, headPixelOffsetY = 0,
				helmetPixelOffsetX = 0, helmetPixelOffsetY = 0, fxPixelOffsetX = 0, fxPixelOffsetY = 0, shieldPixelOffsetX = 0, shieldPixelOffsetY = 0;
		Color oldColor = this.mGame.getSpriteBatch().getColor();
		
		
		// Set our sprite color
		this.mGame.getSpriteBatch().setColor(this.mColor);
		
		// Update internal timers
		this.mDeltaTime = dt;
		
		// Calculate offset to draw and update internal timers
		if(this.mBodyGrhIndex > 0) {
			this.mBodySkin[this.mHeading.toInt()].setAnimationTime(this.mBodySkin[this.getHeading()].getAnimationTime() + this.mDeltaTime);
			bodyPixelOffsetX = this.mX - (this.getBody().getRegionWidth() / 2);
			bodyPixelOffsetY = this.mY - (this.getBody().getRegionHeight());
		}
		
		if(this.mWeaponGrhIndex > 0) {
			this.mWeaponSkin[this.mHeading.toInt()].setAnimationTime(this.mWeaponSkin[this.getHeading()].getAnimationTime() + this.mDeltaTime);
			weaponPixelOffsetX = this.mX - (this.getWeapon().getRegionWidth() / 2);
			weaponPixelOffsetY = this.mY - this.getWeapon().getRegionHeight();
		}
		
		if(this.mShieldGrhIndex > 0) {
			this.mShieldSkin[this.mHeading.toInt()].setAnimationTime(this.mShieldSkin[this.getHeading()].getAnimationTime() + this.mDeltaTime);
			shieldPixelOffsetX = this.mX - (this.getShield().getRegionWidth() / 2);
			shieldPixelOffsetY = this.mY - this.getBody().getRegionHeight() - 5;
		}
		
		if(this.mHeadGrhIndex > 0) {
			headPixelOffsetX = this.mX + this.mHeadOffsetX - (this.getHead().getRegionWidth() / 4) - 4;
			headPixelOffsetY = this.mY + this.mHeadOffsetY - this.getBody().getRegionHeight() - 5;
		}
		
		if(this.mHelmetGrhIndex > 0) {
			helmetPixelOffsetX = this.mX + this.mHeadOffsetX - (this.getHead().getRegionWidth() / 4) - 4;
			helmetPixelOffsetY = this.mY + this.mHeadOffsetY - this.getBody().getRegionHeight() - OFFSET_HEAD - 4;
		}
		
		if(this.mFxGrhIndex > 0) {

			// Make fxs only last 1 second
			if(TimeUtils.timeSinceMillis(this.mFxTimer) >= 1000) { 
				this.setFx(0);
			}
			
			// Update fx data
			this.mFxSkin.setAnimationTime(this.mFxSkin.getAnimationTime() + this.mDeltaTime * 6.0f);
			fxPixelOffsetX = this.mX - (this.mFxSkin.getGraphic().getRegionWidth() / 2) - this.mFxOffsetX;
			fxPixelOffsetY = this.mY - this.mFxSkin.getGraphic().getRegionHeight() - this.mFxOffsetY;
		}
		
		// Draw our character
		if(this.mHeadGrhIndex > 0) {
			if(this.mVisible) {
				if(this.mShieldGrhIndex > 0 && this.mHeading == Heading.EAST)
					this.mGame.getSpriteBatch().draw(this.getShield(), shieldPixelOffsetX, shieldPixelOffsetY);
				
				if(this.mBodyGrhIndex > 0) 
					this.mGame.getSpriteBatch().draw(this.getBody(), bodyPixelOffsetX, bodyPixelOffsetY);
						
				if(this.mHeadGrhIndex > 0)
					this.mGame.getSpriteBatch().draw(this.getHead(), headPixelOffsetX, headPixelOffsetY);
				
				if(this.mWeaponGrhIndex > 0)
					this.mGame.getSpriteBatch().draw(this.getWeapon(), weaponPixelOffsetX, weaponPixelOffsetY);
				
				if(this.mHelmetGrhIndex > 0)
					this.mGame.getSpriteBatch().draw(this.getHelmet(), helmetPixelOffsetX, helmetPixelOffsetY);
				
				if(this.mShieldGrhIndex > 0 && this.mHeading != Heading.EAST)
					this.mGame.getSpriteBatch().draw(this.getShield(), shieldPixelOffsetX, shieldPixelOffsetY);
			}
		} else { // Draw only body
			if(this.mBodyGrhIndex > 0) 
				this.mGame.getSpriteBatch().draw(this.getBody(), bodyPixelOffsetX, bodyPixelOffsetY);
		}
		
		// Replace old color
		this.mGame.getSpriteBatch().setColor(oldColor);
		
		// Draw name
		if(this.mName.length() != 0) {
			
			// To add new lines to our name just go \nNew Line (add clan, staff, etc)
			String line = this.mName;// + "\n<Mob Staff>";
			this.mFont.drawMultiLine(this.mGame.getSpriteBatch(), line, this.mX - ((this.mName.length() * 10) / 2), this.mY - 4, this.mName.length() * 10, HAlignment.CENTER);
		}
		
		// Draw Fx
		if(this.mFxGrhIndex > 0) {
			// Draw fx
			this.mGame.getSpriteBatch().setColor(1.0f, 1.0f, 1.0f, 0.7f);
			this.mGame.getSpriteBatch().draw(this.getFx(), fxPixelOffsetX, fxPixelOffsetY);
			this.mGame.getSpriteBatch().setColor(oldColor);
		}
		
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
	
	public TextureRegion getWeapon() {
		if(this.mMoving)
			return this.mWeaponSkin[this.getHeading()].getGraphic(true);
		else
			return this.mWeaponSkin[this.getHeading()].getGraphic();
	}
	
	public TextureRegion getShield() {
		if(this.mMoving)
			return this.mShieldSkin[this.getHeading()].getGraphic(true);
		else
			return this.mShieldSkin[this.getHeading()].getGraphic();
	}
	
	public TextureRegion getHead() {
		return this.mHeadSkin[this.getHeading()].getGraphic();
	}
	
	public TextureRegion getHelmet() {
		return this.mHelmetSkin[this.getHeading()].getGraphic();
	}
	
	public TextureRegion getFx() {
		return this.mFxSkin.getGraphic(true);
	}

	public void setBody(int bodyIndex) {
		if(bodyIndex > 0) {
			loadBody(this.mGame.getBodyData().get(bodyIndex));
		} else {
			this.mBodyGrhIndex = 0;
		}
	}
	
	public void setWeapon(int weaponIndex) {
		if(weaponIndex > 0) {
			loadWeapon(this.mGame.getWeaponData().get(weaponIndex));
		} else {
			this.mWeaponGrhIndex = 0;
		}
	}
	
	public void setShield(int shieldIndex) {
		if(shieldIndex > 0) {
			loadShield(this.mGame.getShieldData().get(shieldIndex));
		} else {
			this.mShieldGrhIndex = 0;
		}
	}
	
	public void setHead(int headIndex) {
		if(headIndex > 0) {
			loadHead(this.mGame.getHeadData().get(headIndex));
		} else {
			this.mHeadGrhIndex = 0;
		}
	}
	
	public void setHelmet(int helmetIndex) {
		if(helmetIndex > 0) {
			loadHelmet(this.mGame.getHelmetData().get(helmetIndex));
		} else {
			this.mHelmetGrhIndex = 0;
		}
	}
	
	public void setFx(int fxIndex) {
		if(fxIndex > 0) {
			loadFx(this.mGame.getFxData().get(fxIndex));
			this.mFxTimer = TimeUtils.millis();
		} else {
			this.mFxGrhIndex = 0;
			this.mFxTimer = 0;
		}
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
	
	public void loadWeapon(WeaponData weaponData) {
		this.mWeaponGrhIndex = weaponData.getGraphic(Heading.NORTH.toInt());
		this.mWeaponSkin = new BundledTexture[4];
		this.mWeaponSkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, weaponData.getGraphic(Heading.NORTH.toInt()), true);
		this.mWeaponSkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, weaponData.getGraphic(Heading.SOUTH.toInt()), true);
		this.mWeaponSkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, weaponData.getGraphic(Heading.WEST.toInt()), true);
		this.mWeaponSkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, weaponData.getGraphic(Heading.EAST.toInt()), true);
	}
	
	public void loadShield(ShieldData shieldData) {
		this.mShieldGrhIndex = shieldData.getGraphic(Heading.NORTH.toInt());
		this.mShieldSkin = new BundledTexture[4];
		this.mShieldSkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, shieldData.getGraphic(Heading.NORTH.toInt()), true);
		this.mShieldSkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, shieldData.getGraphic(Heading.SOUTH.toInt()), true);
		this.mShieldSkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, shieldData.getGraphic(Heading.WEST.toInt()), true);
		this.mShieldSkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, shieldData.getGraphic(Heading.EAST.toInt()), true);
	}
	
	public void loadHead(HeadData headData) {
		this.mHeadSkin = new BundledTexture[4];
		this.mHeadGrhIndex = headData.getHead(Heading.NORTH.toInt());
		this.mHeadSkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, headData.getHead(Heading.NORTH.toInt()));
		this.mHeadSkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, headData.getHead(Heading.SOUTH.toInt()));
		this.mHeadSkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, headData.getHead(Heading.WEST.toInt()));
		this.mHeadSkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, headData.getHead(Heading.EAST.toInt()));
	}
	
	public void loadHelmet(HelmetData helmetData) {
		this.mHelmetSkin = new BundledTexture[4];
		this.mHelmetGrhIndex = helmetData.getHelmet(Heading.NORTH.toInt());
		this.mHelmetSkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmet(Heading.NORTH.toInt()));
		this.mHelmetSkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmet(Heading.SOUTH.toInt()));
		this.mHelmetSkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmet(Heading.WEST.toInt()));
		this.mHelmetSkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmet(Heading.EAST.toInt()));
	}
	
	public void loadFx(FxData fxData) {
		this.mFxSkin = new BundledTexture(this.mGame, fxData.getGraphic(), true);
		this.mFxGrhIndex = fxData.getGraphic();
		this.mFxOffsetX = fxData.getOffsetX();
		this.mFxOffsetY = fxData.getOffsetY();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
