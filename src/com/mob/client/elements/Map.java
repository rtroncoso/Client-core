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
import com.mob.client.textures.BundledTexture;

public class Map implements IConstants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private Tile[][] mTiles;
	private int mMapNumber;
	private Game mGame;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Map(Game pGame) {
		this.mMapNumber = 0;
		this.mGame = pGame;
	}
	
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
		int screenMinX, screenMaxX, screenMinY, screenMaxY, minAreaX, minAreaY, maxAreaX, maxAreaY;
		MapData mapData = this.mGame.getMapHandler().get(this.mMapNumber);
		int tileBufferSize = 7; // TODO : hardcoded
		
		// Calculate visible part of the map
		screenMinX = ((int) (this.mGame.getCamera().position.x - ((this.mGame.getCamera().position.x + this.mGame.getCamera().viewportWidth) / 2)) / TILE_PIXEL_WIDTH) - 1;
		screenMaxX = ((int) (this.mGame.getCamera().position.x + this.mGame.getCamera().viewportWidth) / TILE_PIXEL_WIDTH) + 1;
		screenMinY = ((int) (this.mGame.getCamera().position.y - ((this.mGame.getCamera().position.y + this.mGame.getCamera().viewportHeight) / 2)) / TILE_PIXEL_WIDTH) - 1;
		screenMaxY = ((int) (this.mGame.getCamera().position.y + this.mGame.getCamera().viewportHeight) / TILE_PIXEL_HEIGHT) + 1;
		
		minAreaX = screenMinX - tileBufferSize;
		maxAreaX = screenMaxX + tileBufferSize;
		minAreaY = screenMinY - tileBufferSize;
		maxAreaY = screenMaxY + tileBufferSize;
		
		// Make sure it is between map bounds
		if(minAreaX < MIN_MAP_SIZE_WIDTH) minAreaX = MIN_MAP_SIZE_WIDTH;
		if(maxAreaX > MAX_MAP_SIZE_WIDTH) maxAreaX = MAX_MAP_SIZE_WIDTH;
		if(minAreaY < MIN_MAP_SIZE_HEIGHT) minAreaY = MIN_MAP_SIZE_HEIGHT;
		if(maxAreaY > MAX_MAP_SIZE_HEIGHT) maxAreaY = MAX_MAP_SIZE_HEIGHT;
		
		if(screenMinX < MIN_MAP_SIZE_WIDTH) screenMinX = MIN_MAP_SIZE_WIDTH;
		if(screenMaxX > MAX_MAP_SIZE_WIDTH) screenMaxX = MAX_MAP_SIZE_WIDTH;
		if(screenMinY < MIN_MAP_SIZE_HEIGHT) screenMinY = MIN_MAP_SIZE_HEIGHT;
		if(screenMaxY > MAX_MAP_SIZE_HEIGHT) screenMaxY = MAX_MAP_SIZE_HEIGHT;
		
		// Start map render
		/******************************************
		 * Layer 1
		 ******************************************/
		for(int y = screenMinY; y <= screenMaxY; y++) {
			for(int x = screenMinX; x <= screenMaxX; x++) {
				
				Tile tile = this.getTile(x, y);
				BundledTexture layer = tile.getGraphic(0);

				layer.setAnimationTime(layer.getAnimationTime() + dt);
				this.mGame.getSpriteBatch().draw(layer.getGraphic(true), layer.getX(), layer.getY());
			}
		}

		/******************************************
		 * Layer 2
		 ******************************************/
		for(int y = minAreaY; y <= maxAreaY; y++) {
			for(int x = minAreaX; x <= maxAreaX; x++) {
				
				Tile tile = this.getTile(x, y);
				BundledTexture layer = tile.getGraphic(1);
				
				if(mapData.getTile(x, y).getGraphic()[1] != 0) {
					layer.setAnimationTime(layer.getAnimationTime() + dt);
					this.mGame.getSpriteBatch().draw(layer.getGraphic(true), layer.getX(), layer.getY());
				}
			}
		}

		/******************************************
		 * Layer 3
		 ******************************************/
		for(int y = minAreaY; y <= maxAreaY; y++) {
			for(int x = minAreaX; x <= maxAreaX; x++) {
				
				Tile tile = this.getTile(x, y);
				BundledTexture layer = tile.getGraphic(2);

				// Layer 3
				if(mapData.getTile(x, y).getGraphic()[2] != 0) {
					layer.setAnimationTime(layer.getAnimationTime() + dt);
					this.mGame.getSpriteBatch().draw(layer.getGraphic(true), layer.getX(), layer.getY());
				}
				
				// Character layer
				if(tile.getCharIndex() != 0) {
					tile.getCharacter().update(dt);
				}
			}
		}
		
		/******************************************
		 * Layer 4
		 ******************************************/
		for(int y = minAreaY; y <= maxAreaY; y++) {
			for(int x = minAreaX; x <= maxAreaX; x++) {
				
				Tile tile = this.getTile(x, y);
				BundledTexture layer = tile.getGraphic(3);

				if(mapData.getTile(x, y).getGraphic()[3] != 0) {
					layer.setAnimationTime(layer.getAnimationTime() + dt);
					this.mGame.getSpriteBatch().draw(layer.getGraphic(true), layer.getX(), layer.getY());
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
	
	public Character getCharacter(int pX, int pY) {
		return this.mTiles[pX][pY].getCharacter();
	}
	
	public void setCharacter(int pX, int pY, Character pCharacter) {
		this.mTiles[pX][pY].setCharIndex(pCharacter.getCharIndex());
		this.mTiles[pX][pY].setCharacter(pCharacter);
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
				this.mTiles[x][y].setBlocked(tile.isBlocked());
			}
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
