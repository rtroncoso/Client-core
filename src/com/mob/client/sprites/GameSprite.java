/**
 * Basic sprite with no texture or rendering
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mob.client.Game;
import com.mob.client.interfaces.ISprite;

public class GameSprite extends Sprite implements ISprite {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected boolean _active;
	protected boolean _visible;
	
	protected int _width;
	protected int _height;
	
	protected float _x;
	protected float _y;
	
	protected Game _game;

	// ===========================================================
	// Constructors
	// ===========================================================
	public GameSprite(Game _game, float x, float y) {
		this._game = _game;
		
		this._x = x;
		this._y = y;
		
		this._active = true;
		this._visible = true;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void reset() {}
	public void update(float dt) {}
	public void show() {}
	public void hide() {}
	public void draw() {}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mActive
	 */
	public boolean isActive() {
		return _active;
	}

	/**
	 * @param mActive the mActive to set
	 */
	public void setActive(boolean mActive) {
		this._active = mActive;
	}

	/**
	 * @return the mVisible
	 */
	public boolean isVisible() {
		return _visible;
	}

	/**
	 * @param mVisible the mVisible to set
	 */
	public void setVisible(boolean mVisible) {
		this._visible = mVisible;
	}

	/**
	 * @param mHeight the mHeight to set
	 */
	public void setHeight(int mHeight) {
		this._height = mHeight;
	}
	// ===========================================================
	// Methods
	// ===========================================================
	public float right () {
		return _x + _width;
	}
	
	public float left () {
		return _x;
	}
	
	public float top () {
		return _y + _height;
	}
	
	public float bottom () {
		return _y;
	}
	
	public Rectangle bounds () {
		return new Rectangle(_x + _width * 0.2f, _y + _height * 0.2f, _width * 0.8f, _height * 0.8f);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
