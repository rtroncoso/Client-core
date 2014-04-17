/**
 * Game constants and enums
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.interfaces;

public interface IConstants {
    
    public static final int GAME_SCREEN_WIDTH = 1280;
    public static final int GAME_SCREEN_HEIGHT = 720;
	public static final float GAME_SCREEN_ZOOM = 1.0f;
	public static final boolean GAME_FULL_SCREEN = false;
	
	public static final String GAME_GRAPHICS_PATH = "data/graficos";
	public static final String GAME_GRAPHICS_EXTENSION = ".png";
	public static final String GAME_MAPS_PATH = "data/mapas";
    
    public static final int GAME_FILE_HEADER_SIZE = 263; 

    public static final int GAME_STATE_PLAY = 0;  
    public static final int GAME_STATE_PAUSE = 1;  
    public static final int GAME_STATE_ANIMATE = 2;
    
    public static final int TILE_PIXEL_WIDTH = 32;
    public static final int TILE_PIXEL_HEIGHT = 32;
    
    public static final int MAX_MAP_SIZE_WIDTH = 100;
    public static final int MIN_MAP_SIZE_WIDTH = 1;
    public static final int MAX_MAP_SIZE_HEIGHT = 100;
    public static final int MIN_MAP_SIZE_HEIGHT = 1;
    public static final int MAX_MAPS = 1000; // TODO : fixme
    
    public static final int OFFSET_HEAD = -34;
    
    public static final int INFINITE_LOOPS = -1;
    
    public static final byte WALK_NORTH = 0;
    public static final byte WALK_EAST = 1;
    public static final byte WALK_SOUTH = 2;
    public static final byte WALK_WEST = 3;
    
}
