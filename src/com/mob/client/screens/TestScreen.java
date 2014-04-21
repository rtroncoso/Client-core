/**
 * Screen for testing purposes only
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.screens;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
		this.setDefaultShader(SpriteBatch.createDefaultShader());
		this.setLightShader(new ShaderProgram(this.mVertexShader, this.mLightShaderFile));
		
		// Setup light shader
		this.getLightShader().begin();
		this.getLightShader().setUniformi("u_lightmap", 1);
		this.getLightShader().end();
		
		// Load a map
		this.map = 1;
		this.mGame.getEngine().setMap(this.map);
		this.mGame.getEngine().setTint(COLOR_NIGHT);
		
		// Plot a character
		this.mGame.getCharacterHandler().makeChar(1, 51, 50, Heading.SOUTH, 1, 13, 6, 4, 6);
		this.mGame.getCharacterHandler().getPlayer().setName("BetaTester");
		this.mGame.getCharacterHandler().getPlayer().createLight(Color.WHITE, 6, 10.0f);
		this.mGame.getCharacterHandler().getPlayer().setFocus();
		
		// Plot a npc
		this.mGame.getCharacterHandler().makeChar(2, 50, 50, Heading.SOUTH, 17, 0, 0, 0, 0);
		
		this.mInputMultiplexer = new InputMultiplexer();
		this.mInputMultiplexer.addProcessor(new InputProcessor() {

			@Override
			public boolean keyDown(int keycode) {
				switch(keycode) {
				case(Keys.SPACE):
					mGame.getCharacterHandler().getPlayer().setFx(13);
					break;
				case(Keys.NUM_1):
					mGame.getEngine().setTint(COLOR_NIGHT);
					break;
				case(Keys.NUM_2):
					mGame.getEngine().setTint(COLOR_DAWN);
					break;
				case(Keys.NUM_3):
					mGame.getEngine().setTint(COLOR_DAYLIGHT);
					break;
				case(Keys.PLUS):
					map += 1;
					mGame.getEngine().setMap(map);
					mGame.getCharacterHandler().getPlayer().updateUserPos();
					break;
				case(Keys.MINUS):
					map -= 1;
					mGame.getEngine().setMap(map);
					mGame.getCharacterHandler().getPlayer().updateUserPos();
					break;
				}
				return true;
			}

			@Override
			public boolean keyUp(int keycode) {
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {
				if(button == Input.Buttons.LEFT) {
					mGame.getEngine().getLightHandler().createLight((int) ((mGame.getCamera().position.x - (mGame.getCamera().viewportWidth / 2)) / TILE_PIXEL_WIDTH) + (screenX / TILE_PIXEL_WIDTH), 
							(int) ((mGame.getCamera().position.y  - (mGame.getCamera().viewportHeight / 2)) / TILE_PIXEL_HEIGHT) + (screenY / TILE_PIXEL_HEIGHT), Color.GREEN, 4);
				} 
				return true;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer,
					int button) {
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				return false;
			}
			
		});
		Gdx.input.setInputProcessor(this.mInputMultiplexer);
	}

	@Override
	public void update(float dt) {
		
		// Fixed step (testing)
		//dt = 1.0f/60.0f;
		
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
		
		// Update shader light according to map tint
		this.getLightShader().begin();
			this.getLightShader().setUniformf("ambientColor", this.mGame.getEngine().getTint().r, this.mGame.getEngine().getTint().g, this.mGame.getEngine().getTint().b, .8f);
		this.getLightShader().end();
		
		// Draw shaders into FBO
		this.mFbo.begin();
		
			// Set up our spritebatch
			this.mGame.getSpriteBatch().setProjectionMatrix(this.mGame.getCamera().combined);
			this.mGame.getSpriteBatch().setShader(this.getDefaultShader());
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			// Draw all shaders in it
			this.mGame.getSpriteBatch().begin();
			
				Iterator<Integer> it = this.mGame.getEngine().getLightHandler().getLights().keySet().iterator();
				while(it.hasNext()) {
					Integer i = (Integer) it.next();
					Shader s = this.mGame.getEngine().getLightHandler().getLight(i);
					s.update(dt);
				}
			this.mGame.getSpriteBatch().end();
		this.mFbo.end();
		
		this.mGame.getSpriteBatch().begin();

			// Setup spritebatch
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			Gdx.gl.glEnable(GL20.GL_BLEND);
	        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
			this.mGame.getSpriteBatch().setProjectionMatrix(this.mGame.getCamera().combined);
			this.mGame.getSpriteBatch().setShader(this.getLightShader());
			
		 	// Our shader is rendered in the second texture unit
			this.mFbo.getColorBufferTexture().bind(1);
			it = this.mGame.getEngine().getLightHandler().getLights().keySet().iterator();
			while(it.hasNext()) {
				int i = (Integer) it.next();
				Shader s = this.mGame.getEngine().getLightHandler().getLight(i);
				s.getTexture().bind(0); // This is to avoid artifacts
			}
			
			// Update our engine
        	this.mGame.getEngine().update(dt);
		this.mGame.getSpriteBatch().end();
	}
	
	@Override
	public void resize(int arg0, int arg1) {

		this.getLightShader().begin();
		this.getLightShader().setUniformf("resolution", arg0, arg1);
		this.getLightShader().end();
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