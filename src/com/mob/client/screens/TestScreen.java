/**
 * Screen for testing purposes only
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.mob.client.Game;
import com.mob.client.interfaces.IConstants;


public class TestScreen extends Screen implements IConstants {
	
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int map;
	
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
		
		// Load a map
		this.map = 1;
		this.mGame.getCurrentMap().setMap(this.map);
		this.mGame.getCurrentMap().setTint(COLOR_DAWN);
		
		// Plot a character
		this.mGame.getCharacterHandler().makeChar(1, 50, 50, Heading.SOUTH, 1, 6, 4);
		this.mGame.getCharacterHandler().getPlayer().setName("Froda");
		
		this.mInputMultiplexer = new InputMultiplexer();
	}

	@Override
	public void update(float dt) {
		
		Gdx.graphics.setTitle("FPS: " + String.valueOf(Gdx.graphics.getFramesPerSecond()
				+ " X: " + this.mGame.getCharacterHandler().getPlayer().getUserPosX()
				+ " Y: " + this.mGame.getCharacterHandler().getPlayer().getUserPosY()));
		
		// Input detection
		if(Gdx.input.justTouched()) {
			this.map += 1;
			this.mGame.getCurrentMap().setMap(this.map);
			this.mGame.getCharacterHandler().getPlayer().updateUserPos();
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			this.mGame.getCharacterHandler().getPlayer().moveLeft();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			this.mGame.getCharacterHandler().getPlayer().moveRight();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			this.mGame.getCharacterHandler().getPlayer().moveUp();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			this.mGame.getCharacterHandler().getPlayer().moveDown();
		}
		
		// Clean up Scene
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        // Focus camera on player
		this.mGame.getCharacterHandler().getPlayer().focusCamera();
		this.mGame.getCamera().update();
		
		// Send update to the map, it will handle the update of each element in it
		Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		this.mGame.getSpriteBatch().setProjectionMatrix(this.mGame.getCamera().combined);
		this.mGame.getSpriteBatch().begin();
        this.mGame.getCurrentMap().update(dt);
		this.mGame.getSpriteBatch().end();
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