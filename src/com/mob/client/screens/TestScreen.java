/**
 * Screen for testing purposes only
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.mob.client.Game;
import com.mob.client.elements.Character;
import com.mob.client.interfaces.IConstants;


public class TestScreen extends Screen implements IConstants {
	
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private Character mTest;

	// ===========================================================
	// Constructors
	// ===========================================================
	public TestScreen (Game game) {
		super(game);
	}


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void createScreen() {
		this.mTest = new Character(_game, 1, 1, WALK_SOUTH, 1, 4, 0);
	}

	@Override
	public void update(float dt) {
		// Input detection
		if(Gdx.input.justTouched()) {
			this.mTest.setMoving(!this.mTest.isMoving());
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			this.mTest.moveLeft();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			this.mTest.moveRight();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			this.mTest.moveUp();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			this.mTest.moveDown();
		}
		
		// Clean up Scene
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        // Update our Camera
		_game.getCamera().update();
		
		// Draw each component of the scene
		Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        this.mTest.update(dt);
		
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