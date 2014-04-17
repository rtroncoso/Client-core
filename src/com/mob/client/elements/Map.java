/**
 * Draws a complete map into the screen
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.elements;

import com.mob.client.Game;
import com.mob.client.data.MapBlockData;
import com.mob.client.data.MapData;
import com.mob.client.interfaces.IConstants;

public class Map implements IConstants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private Tile[][] mTiles;
	private int mMapNumber;
	private float mDeltaTime;
	private Game mGame;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param mMapNumber
	 * @param mGame
	 */
	public Map(Game pGame, int pMapNumber) {
		
		this.mMapNumber = pMapNumber;
		this.mGame = pGame;
		
		this.load();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void update(float dt) {
		
		// Vars
		MapData mapData = this.mGame.getMapHandler().get(this.mMapNumber);
		int screenMinX, screenMaxX, screenMinY, screenMaxY;
		
		// Calculate visible part of the map
		int minAreaX = ((int) (this.mGame.getCamera().position.x - ((this.mGame.getCamera().position.x + this.mGame.getCamera().viewportWidth) / 2)) / TILE_PIXEL_WIDTH) - 1;
		int maxAreaX = ((int) (this.mGame.getCamera().position.x + this.mGame.getCamera().viewportWidth) / TILE_PIXEL_WIDTH) + 1;
		int minAreaY = ((int) (this.mGame.getCamera().position.y - ((this.mGame.getCamera().position.y + this.mGame.getCamera().viewportHeight) / 2)) / TILE_PIXEL_WIDTH) - 1;
		int maxAreaY = ((int) (this.mGame.getCamera().position.y + this.mGame.getCamera().viewportHeight) / TILE_PIXEL_HEIGHT) + 1;
		
		// Make sure it is between map bounds
		if(minAreaX < MIN_MAP_SIZE_WIDTH) minAreaX = MIN_MAP_SIZE_WIDTH;
		if(maxAreaX > MAX_MAP_SIZE_WIDTH) maxAreaX = MAX_MAP_SIZE_WIDTH;
		if(minAreaY < MIN_MAP_SIZE_HEIGHT) minAreaY = MIN_MAP_SIZE_HEIGHT;
		if(maxAreaY > MAX_MAP_SIZE_HEIGHT) maxAreaY = MAX_MAP_SIZE_HEIGHT;
		
		// Start map render
		/******************************************
		 * Layer 1
		 ******************************************/
		for(int y = minAreaY; y <= maxAreaY; y++) {
			for(int x = minAreaX; x <= maxAreaX; x++) {
				
				Tile tile = this.getTile(x, y);

				this.mGame.getSpriteBatch().begin();
				//tile.getGraphic(0).setAnimationTime(tile.getGraphic(0).getAnimationTime() + dt);
				this.mGame.getSpriteBatch().draw(tile.getGraphic(0).getGraphic(), tile.getGraphic(0).getX(), tile.getGraphic(0).getY());
				this.mGame.getSpriteBatch().end();
			}
		}

		/******************************************
		 * Layer 2
		 ******************************************/
		for(int y = minAreaY; y <= maxAreaY; y++) {
			for(int x = minAreaX; x <= maxAreaX; x++) {
				
				Tile tile = this.getTile(x, y);
				if(mapData.getTile(x, y).getGraphic()[1] != 0) {
					this.mGame.getSpriteBatch().begin();
					this.mGame.getSpriteBatch().draw(tile.getGraphic(1).getGraphic(), tile.getGraphic(1).getX(), tile.getGraphic(1).getY());
					this.mGame.getSpriteBatch().end();
				}
			}
		}

		/******************************************
		 * Layer 3
		 ******************************************/
		for(int y = minAreaY; y <= maxAreaY; y++) {
			for(int x = minAreaX; x <= maxAreaX; x++) {
				
				Tile tile = this.getTile(x, y);

				if(mapData.getTile(x, y).getGraphic()[2] != 0) {
					this.mGame.getSpriteBatch().begin();
					this.mGame.getSpriteBatch().draw(tile.getGraphic(2).getGraphic(), tile.getGraphic(2).getX(), tile.getGraphic(2).getY());
					this.mGame.getSpriteBatch().end();
				}
			}
		}

		
		/******************************************
		 * Layer 4
		 ******************************************/
		for(int y = minAreaY; y <= maxAreaY; y++) {
			for(int x = minAreaX; x <= maxAreaX; x++) {
				
				Tile tile = this.getTile(x, y);

				if(mapData.getTile(x, y).getGraphic()[3] != 0) {
					this.mGame.getSpriteBatch().begin();
					this.mGame.getSpriteBatch().draw(tile.getGraphic(3).getGraphic(), tile.getGraphic(3).getX(), tile.getGraphic(3).getY());
					this.mGame.getSpriteBatch().end();
				}
			}
		}
	}
	public void reset() {}
	public void show() {}
	public void draw() {}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mTiles
	 */
	public Tile[][] getTilesArray() {
		return mTiles;
	}
	
	/**
	 * @param mTiles the mTiles to set
	 */
	public void setTilesArray(Tile[][] mTiles) {
		this.mTiles = mTiles;
	}
	
	/**
	 * @return the mMapNumber
	 */
	public int getMapNumber() {
		return mMapNumber;
	}
	/**
	 * @param mMapNumber the mMapNumber to set
	 */
	public void setMap(int mMapNumber) {
		this.mMapNumber = mMapNumber;
		this.load();
	}
	
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
	
	public Tile getTile(int pX, int pY) {
		return this.mTiles[pX][pY];
	}
	
	public void setTile(int pX, int pY, Tile pMapBlock) {
		this.mTiles[pX][pY] = pMapBlock;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * Initializes current map (Call setMap for MapChange)
	 */
	public void load() {

		MapData mapData = this.mGame.getMapHandler().get(this.mMapNumber);
		
		// Fill tiles array
		this.mTiles = new Tile[MAX_MAP_SIZE_WIDTH + 1][MAX_MAP_SIZE_HEIGHT + 1];
		for(int y = MIN_MAP_SIZE_HEIGHT; y <= MAX_MAP_SIZE_HEIGHT; y++) {
			for(int x = MIN_MAP_SIZE_WIDTH; x <= MAX_MAP_SIZE_WIDTH; x++) {
				
				MapBlockData tile = mapData.getTile(x, y);
				this.mTiles[x][y] = new Tile(this.mGame, x, y, tile.getGraphic());
			}
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
