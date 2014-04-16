/**
 * Common screen class. Inherit for individual screen handling
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.screens;

import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.mob.client.Game;

public abstract class Screen  implements com.badlogic.gdx.Screen {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	//public List<GameSprite> _elements;
	protected Game _game;
	protected SpriteCache _spriteCache;
	protected int _spriteCacheIndex;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Screen (Game game) {
		this._game = game;
		//this._elements = new ArrayList<GameSprite>();
		this._spriteCache = new SpriteCache();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void pause () {};
	public void resume () {};
	public void dispose (){};
	public void hide (){};
	public void show (){};
	public void destroy () {};
	
	public abstract void createScreen ();
	public abstract void update (float dt);
	
	@Override
	public void render(float arg0) {
	}

	@Override
	public void resize(int arg0, int arg1) {
	}

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
