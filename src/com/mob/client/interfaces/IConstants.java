/**
 * Game constants and enums
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.interfaces;

import com.badlogic.gdx.graphics.Color;

public interface IConstants {
    
    public static final int GAME_SCREEN_WIDTH = 1366;
    public static final int GAME_SCREEN_HEIGHT = 768;
	public static final float GAME_SCREEN_ZOOM = 1.0f;
	public static final boolean GAME_FULL_SCREEN = false;
	public static final String GAME_GRAPHICS_PATH = "data/graficos";
	public static final String GAME_GRAPHICS_EXTENSION = ".png";
	public static final String GAME_MAPS_PATH = "data/mapas";
	public static final String GAME_FONTS_PATH = "data/fonts/";
    public static final int GAME_FILE_HEADER_SIZE = 263; 
    public static final int GAME_STATE_PLAY = 0;  
    public static final int GAME_STATE_PAUSE = 1;  
    public static final int GAME_STATE_ANIMATE = 2;
    
    public static final int TILE_PIXEL_WIDTH = 32;
    public static final int TILE_PIXEL_HEIGHT = 32;
    public static final int TILE_BUFFER_SIZE = 8;
    
    public static final int MAX_MAP_SIZE_WIDTH = 100;
    public static final int MIN_MAP_SIZE_WIDTH = 1;
    public static final int MAX_MAP_SIZE_HEIGHT = 100;
    public static final int MIN_MAP_SIZE_HEIGHT = 1;
    
    public static final int OFFSET_HEAD = 12;
    
    public static final Color COLOR_DAYLIGHT = new Color(0.9f, 0.9f, 0.9f, 1.0f);
    public static final Color COLOR_DAWN = new Color(0.7f, 0.6f, 0.6f, 1.0f);
    public static final Color COLOR_NIGHT = new Color(0.4f, 0.8f, 0.8f, 1.0f);
    
    public static final float ALPHA_TREES = 1.0f;
    
    public enum Heading {
    	NORTH(0), EAST(1), SOUTH(2), WEST(3);
    	
    	final int mHeading;
    	
    	Heading(int pHeading) {
    		this.mHeading = pHeading;
    	}
    	
    	public int toInt() {
    		return this.mHeading;
    	}
    }
    
}
