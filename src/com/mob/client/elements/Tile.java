/**
 * Stores foreground information of a tile (particle, character, fx, etc. in it)
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.elements;

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
	private int mCharIndex;
	private boolean mBlocked;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Tile(Game _game, int x, int y, int[] pGraphic, Character pCharacter) {
		super(_game, x, y, pGraphic);
		this.setCharacter(pCharacter);
	}
	
	public Tile(Game _game, int x, int y, int[] pGraphic) {
		super(_game, x, y, pGraphic);
		this.setCharacter(null);
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

	// ===========================================================
	// Methods
	// ===========================================================
	public boolean isLegalPos() {
		if(this.mBlocked) return false;
		if(this.mCharIndex > 0) return false;
		// checkear hayagua
		return true;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
