/**
 * Contains Box2D methods and main loop of the engine 
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-22
 */
package com.mob.client.engine;

import box2dLight.DirectionalLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mob.client.Game;
import com.mob.client.handlers.LightHandler;
import com.mob.client.interfaces.IConstants;

public class Box2DEngine implements IConstants {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private Game mGame;
	private World mWorld;
	private Box2DDebugRenderer mDebugRenderer;
	private RayHandler mRayHandler;
	private LightHandler mLightHandler;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Box2DEngine(Game pGame) {
		this.mGame = pGame;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void initEngine() {
		
		// Init box2d world
		this.mWorld = new World(new Vector2(), true);
		this.mDebugRenderer = new Box2DDebugRenderer();
		this.mDebugRenderer.setDrawBodies(false);
		
		// RayHandler setup
		RayHandler.setGammaCorrection(true);
		RayHandler.useDiffuseLight(true);
		this.mRayHandler = new RayHandler(this.mWorld);
		this.mRayHandler.setCulling(true);
		this.mRayHandler.setBlurNum(5);
		this.mGame.getCamera().update(true);
		
		// LightHandler setup
		this.setLightHandler(new LightHandler(this.mGame));
	}
	
	public void dispose() {
		this.mRayHandler.dispose();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void update(float dt) {
		// Render box2d world
		this.mWorld.step(1/45f, 6, 2);
		this.mDebugRenderer.render(this.mWorld, this.mGame.getCamera().combined);

		// Render lights
		this.mRayHandler.setAmbientLight(this.mGame.getEngine().getTint().r, this.mGame.getEngine().getTint().g, 
				this.mGame.getEngine().getTint().b, this.mGame.getEngine().getTint().a);
		this.mRayHandler.setCombinedMatrix(this.mGame.getCamera().combined, this.mGame.getCamera().position.x, 
				this.mGame.getCamera().position.y, this.mGame.getCamera().viewportWidth,
				this.mGame.getCamera().viewportHeight);
		this.mRayHandler.updateAndRender();
	}
	
	public void createObject(int x, int y, float width, float height) {
		
		// Calculate half tile
		float halfBodyWidth = TILE_PIXEL_WIDTH / 2f, halfBodyHeight = TILE_PIXEL_HEIGHT /2f;
		
		// Create polygonShap
		PolygonShape tileShape = new PolygonShape();
		tileShape.setAsBox(halfBodyWidth, halfBodyHeight);
		
		// Make this body static
		BodyDef tileBodyDef = new BodyDef();
		tileBodyDef.type = BodyType.StaticBody;
		
		// Hold fixturedef
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = tileShape;
		fixtureDef.filter.groupIndex = 0;

		// Create box2d bodies for all layer 3 objects
	    float bodyX = x * TILE_PIXEL_WIDTH - 2;// + halfBodyWidth / 2f;
	    float bodyY = y * TILE_PIXEL_HEIGHT - halfBodyHeight - 1;
	    tileBodyDef.position.set(bodyX, bodyY);
	    Body tileBody = this.mWorld.createBody(tileBodyDef);
	    tileBody.createFixture(fixtureDef);
	    
	    tileShape.dispose();
	}
	
	public void reset() {
		Array<Body> tmpBodies = new Array<Body>();
		this.mWorld.getBodies(tmpBodies);
		for(int i = 0; i < tmpBodies.size; i++) {
			tmpBodies.get(i).setActive(false);
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mWorld
	 */
	public World getWorld() {
		return mWorld;
	}

	/**
	 * @param mWorld the mWorld to set
	 */
	public void setWorld(World mWorld) {
		this.mWorld = mWorld;
	}

	/**
	 * @return the mLightHandler
	 */
	public LightHandler getLightHandler() {
		return mLightHandler;
	}

	/**
	 * @param mLightHandler the mLightHandler to set
	 */
	public void setLightHandler(LightHandler mLightHandler) {
		this.mLightHandler = mLightHandler;
	}

	/**
	 * @return the mRayHandler
	 */
	public RayHandler getRayHandler() {
		return mRayHandler;
	}

	/**
	 * @param mRayHandler the mRayHandler to set
	 */
	public void setRayHandler(RayHandler mRayHandler) {
		this.mRayHandler = mRayHandler;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
