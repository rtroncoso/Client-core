/**
 * Graphic engine adapted from Argentum Online
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mob.client.Game;

public class TileEngine {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private Game mGame;
	private SpriteBatch mSpriteBatch;

	// ===========================================================
	// Constructors
	// ===========================================================
	public TileEngine(Game game) {
		this.setGame(game);
		this.setSpriteBatch(new SpriteBatch());
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void reset() {}
	
	public void update(float dt) {
		
	}
	public void show() {}
	
	public void hide() {}
	
	public void draw() {}

	// ===========================================================
	// Getter & Setter
	// ===========================================================


	// ===========================================================
	// Methods
	// ===========================================================


	/**
	 * @return the mGame
	 */
	public Game getGame() {
		return mGame;
	}


	/**
	 * @param mGame the mGame to set
	 */
	public void setGame(Game mGame) {
		this.mGame = mGame;
	}


	/**
	 * @return the mSpriteBatch
	 */
	public SpriteBatch getSpriteBatch() {
		return mSpriteBatch;
	}


	/**
	 * @param mSpriteBatch the mSpriteBatch to set
	 */
	public void setSpriteBatch(SpriteBatch mSpriteBatch) {
		this.mSpriteBatch = mSpriteBatch;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
