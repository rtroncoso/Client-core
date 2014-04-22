/**
 * Stores foreground information of a tile (particle, character, fx, etc. in it)
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.elements;

import com.badlogic.gdx.graphics.Color;
import com.mob.client.Game;
import com.mob.client.sprites.TileSprite;

public class Tile extends TileSprite {


	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private Character mCharacter;
	private boolean mBlocked;
	private boolean mHasTree;
	private boolean mHasWater;
	private int mLightIndex;
	private int mCharIndex;
	private int mTrigger;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Tile(Game _game, int x, int y, int[] pGraphic, Character pCharacter) {
		super(_game, x, y, pGraphic);
		
		// Plot a character in this tile
		this.setCharacter(pCharacter);
		
		// Check if there is a tree
		if(this.getGrhIndex(2) == 735 || (this.getGrhIndex(2) >= 6994 && this.getGrhIndex(2) <= 7002)) {
			this.mHasTree = true;
		}
		
		// Check if it has water
		if((this.getGrhIndex(0) >= 1505 && this.getGrhIndex(0) <= 1520) || 
		   (this.getGrhIndex(0) >= 5665 && this.getGrhIndex(0) <= 5680) ||
		   (this.getGrhIndex(0) >= 13547 && this.getGrhIndex(0) <= 13562)) {
			if(this.getGrhIndex(1) == 0) this.mHasWater = true;
		}
		
		// Create static bodys for layer 3
		if(this.getGrhIndex(2) > 0) {
			if(!this.hasTree())
				this.mGame.getBox2DEngine().createObject(x, y, this.getGraphic(2).getGraphic().getRegionWidth(), this.getGraphic(2).getGraphic().getRegionHeight());
			else
				this.mGame.getBox2DEngine().createObject(x, y, TILE_PIXEL_WIDTH, TILE_PIXEL_HEIGHT);
		}
		
		// Null light
		this.mLightIndex = 0;
	}
	
	public Tile(Game _game, int x, int y, int[] pGraphic) {
		this(_game, x, y, pGraphic, null);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mCharacter
	 */
	public Character getCharacter() {
		return mCharacter;
	}

	/**
	 * @param mCharacter the mCharacter to set
	 */
	public void setCharacter(Character mCharacter) {
		this.mCharacter = mCharacter;
		if(this.mCharacter != null) 
			this.mCharIndex = this.mCharacter.getCharIndex();
		else
			this.mCharIndex = 0;
	}

	/**
	 * @return the mCharIndex
	 */
	public int getCharIndex() {
		return mCharIndex;
	}

	/**
	 * @param mCharIndex the mCharIndex to set
	 */
	public void setCharIndex(int mCharIndex) {
		this.mCharIndex = mCharIndex;
	}

	/**
	 * @return the mBlocked
	 */
	public boolean isBlocked() {
		return mBlocked;
	}

	/**
	 * @param mBlocked the mBlocked to set
	 */
	public void setBlocked(boolean mBlocked) {
		this.mBlocked = mBlocked;
	}

	/**
	 * @return the mHasTree
	 */
	public boolean hasTree() {
		return mHasTree;
	}

	/**
	 * @param mHasTree the mHasTree to set
	 */
	public void setHasTree(boolean mHasTree) {
		this.mHasTree = mHasTree;
	}

	/**
	 * @return the mTrigger
	 */
	public int getTrigger() {
		return mTrigger;
	}

	/**
	 * @param mTrigger the mTrigger to set
	 */
	public void setTrigger(int mTrigger) {
		this.mTrigger = mTrigger;
	}
	
	/**
	 * @return the mLightIndex
	 */
	public int getLightIndex() {
		return mLightIndex;
	}

	/**
	 * @param mLightIndex the mLightIndex to set
	 */
	public void setLightIndex(int mLightIndex) {
		this.mLightIndex = mLightIndex;
	}

	/**
	 * @return the mHasWater
	 */
	public boolean hasWater() {
		return mHasWater;
	}

	/**
	 * @param mHasWater the mHasWater to set
	 */
	public void setWater(boolean mHasWater) {
		this.mHasWater = mHasWater;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public boolean isLegalPos() {
		if(this.mBlocked) return false;
		if(this.mCharIndex > 0) return false;
		if(this.mHasWater) return false;
		return true;
	}

	/**
	 * @return returns if we are a roof
	 */
	public boolean isRoof() {
		if(this.mTrigger == 1 || this.mTrigger == 2 || this.mTrigger == 4 || this.mTrigger == 21) return true;
		return false;
	}
	
	/**
	 * Creates a new light in this tile
	 * @return the new light index
	 */
	public int createLight(Color pColor, float pSize, float pSpeed) {
		return this.mGame.getBox2DEngine().getLightHandler().createLight(this.getX(), this.getY(), pColor, pSize);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
