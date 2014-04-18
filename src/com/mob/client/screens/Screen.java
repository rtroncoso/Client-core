/**
 * Common screen class. Inherit for individual screen handling
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.screens;

import com.badlogic.gdx.InputMultiplexer;
import com.mob.client.Game;
import com.mob.client.elements.Character;
import com.mob.client.engine.Engine;

public abstract class Screen  implements com.badlogic.gdx.Screen {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected InputMultiplexer mInputMultiplexer;
	protected Game mGame;
	protected Engine mMap;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Screen (Game game) {
		this.mGame = game;
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
