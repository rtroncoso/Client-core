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
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.mob.client.Game;
import com.mob.client.elements.Shader;
import com.mob.client.interfaces.IConstants;


public class TestScreen extends Screen implements IConstants {
	
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private ShaderProgram mDefaultShader;
	private ShaderProgram mLightShader;
	private FrameBuffer mFbo;
	private int map;
	
	// Encapsulate this
	final String mDefaultShaderFile = new FileHandle("data/shaders/defaultPixelShader.glsl").readString();
	final String mLightShaderFile =  new FileHandle("data/shaders/pixelShader.glsl").readString();
	final String mVertexShader = new FileHandle("data/shaders/vertexShader.glsl").readString();
	
	
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
		// Load shaders
		ShaderProgram.pedantic = false;
		this.mFbo = new FrameBuffer(Format.RGBA8888, (int) this.mGame.getCamera().viewportWidth, (int) this.mGame.getCamera().viewportHeight, false);
		this.mDefaultShader = new ShaderProgram(this.mVertexShader, this.mDefaultShaderFile);
		this.mLightShader = new ShaderProgram(this.mVertexShader, this.mLightShaderFile);
		
		// Setup light shader
		this.mLightShader.begin();
		this.mLightShader.setUniformi("u_lightmap", 1);
		this.mLightShader.end();
		
		// Load a map
		this.map = 1;
		this.mGame.getEngine().setMap(this.map);
		this.mGame.getEngine().setTint(COLOR_DAWN);
		this.mGame.getEngine().createLight(50, 50, Color.WHITE, 6, 15.0f);
		
		// Plot a character
		this.mGame.getCharacterHandler().makeChar(1, 51, 50, Heading.SOUTH, 1, 13, 6, 4, 6);
		this.mGame.getCharacterHandler().getPlayer().setName("Froda");
		
		// Plot a npc
		this.mGame.getCharacterHandler().makeChar(2, 50, 50, Heading.SOUTH, 17, 0, 0, 0, 0);
		
		this.mInputMultiplexer = new InputMultiplexer();
		this.mInputMultiplexer.addProcessor(new InputProcessor() {

			@Override
			public boolean keyDown(int keycode) {
				if(keycode == Keys.SPACE) mGame.getCharacterHandler().getPlayer().setFx(13);
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {
				if(button == Input.Buttons.LEFT) map += 1;
				else if(button == Input.Buttons.RIGHT) map -= 1;
				mGame.getEngine().setMap(map);
				mGame.getCharacterHandler().getPlayer().updateUserPos();
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer,
					int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		Gdx.input.setInputProcessor(this.mInputMultiplexer);
	}

	@Override
	public void update(float dt) {
		
		Gdx.graphics.setTitle("FPS: " + String.valueOf(Gdx.graphics.getFramesPerSecond()
				+ " Map: " + map
				+ " X: " + this.mGame.getCharacterHandler().getPlayer().getUserPosX()
				+ " Y: " + this.mGame.getCharacterHandler().getPlayer().getUserPosY()));
		
		// Input detection
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			this.mGame.getCharacterHandler().getPlayer().moveUp();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			this.mGame.getCharacterHandler().getPlayer().moveLeft();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			this.mGame.getCharacterHandler().getPlayer().moveDown();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			this.mGame.getCharacterHandler().getPlayer().moveRight();
		}
		
		// Clean up Scene
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Update shader light according to map tint
		this.mLightShader.begin();
			this.mLightShader.setUniformf("ambientColor", this.mGame.getEngine().getTint().r, this.mGame.getEngine().getTint().g, this.mGame.getEngine().getTint().b, .9f);
		this.mLightShader.end();
		
		// Draw shaders into FBO
		this.mFbo.begin();
		
			// Set up our spritebatch
			this.mGame.getSpriteBatch().setProjectionMatrix(this.mGame.getCamera().combined);
			this.mGame.getSpriteBatch().setShader(this.mDefaultShader);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			// Draw all shaders in it
			this.mGame.getSpriteBatch().begin();
			for(Shader t : this.mGame.getEngine().getLights()) {
				if(t.isActive()) t.update(dt);
			}
			this.mGame.getSpriteBatch().end();
		this.mFbo.end();
		
		
		// Send update to the map, it will handle the update of each element in it
		Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		this.mGame.getSpriteBatch().setProjectionMatrix(this.mGame.getCamera().combined);
		this.mGame.getSpriteBatch().setShader(this.mLightShader);
		
		this.mGame.getSpriteBatch().begin();
		
		 	// Our shader is rendered in the second texture unit
			this.mFbo.getColorBufferTexture().bind(1);
			for(Shader t : this.mGame.getEngine().getLights()) {
				if(t.isActive()) t.getTexture().bind(0); // This is to avoid artifacts
			}
			
			// Update our engine
        	this.mGame.getEngine().update(dt);
		this.mGame.getSpriteBatch().end();
		
        // Focus camera on player
		this.mGame.getCharacterHandler().getPlayer().focusCamera();
	}
	
	@Override
	public void resize(int arg0, int arg1) {

		this.mLightShader.begin();
		this.mLightShader.setUniformf("resolution", arg0, arg1);
		this.mLightShader.end();
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