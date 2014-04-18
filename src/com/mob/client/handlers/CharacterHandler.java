/**
 * Stores all characters in the game
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.handlers;

import java.util.HashMap;

import com.mob.client.Game;
import com.mob.client.elements.Character;
import com.mob.client.interfaces.IConstants.Heading;

public class CharacterHandler {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private HashMap<Integer, Character> mCharacterMap; 
	private Game mGame;

	// ===========================================================
	// Constructors
	// ===========================================================
	public CharacterHandler(Game pGame) {
		
		this.mCharacterMap = new HashMap<Integer, Character>();
		this.mGame = pGame;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mCharacterMap
	 */
	public HashMap<Integer, Character> getCharacterMap() {
		return mCharacterMap;
	}
	/**
	 * @param mCharacterMap the mCharacterMap to set
	 */
	public void setCharacterMap(HashMap<Integer, Character> mCharacterMap) {
		this.mCharacterMap = mCharacterMap;
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

	/**
	 * @param key Index in map
	 * @param pCharacter character to add
	 */
	 public void add(final int key, final Character pCharacter) {
		 if (this.mCharacterMap.containsKey(key)) {
			 return;
		 }
	   
	 	this.mCharacterMap.put(key, pCharacter);
	 }

	/**
	 * Gets a Character from the map
	 * @param key Index in map
	 * @return Character inside the map
	 */
	 public Character get(final int key) {
		 //if(!this.mCharacterMap.containsKey(key)) this.loadTexture(key);
		 return this.mCharacterMap.get(key);
	 }

	/**
	 * Gets the application player (always index 1)
	 * @return the player
	 */
	 public Character getPlayer() {
		 return get(1);
	 }
	  
	 /**
	  * Unloads a Character from the map and memory
	  * @param key Index in map
	  */
	public void dispose(final int key) {
		if (!this.mCharacterMap.containsKey(key)) {
			return;
		}
	   
		final Character t = this.mCharacterMap.get(key);
		t.dispose();
		this.mCharacterMap.remove(key);
	}
	  
	public void disposeAll() {
		for (final Character t : this.mCharacterMap.values()) {
			t.dispose();
		}
   
		this.mCharacterMap.clear();
	 }

	// ===========================================================
	// Methods
	// ===========================================================
	public void makeChar(int charIndex, int x, int y, Heading heading, int bodyIndex, int headIndex, int helmetIndex) {
		
		// Make character
		Character character = new Character(this.mGame, charIndex, x, y, heading, bodyIndex, headIndex, helmetIndex);
		
		// Add to hashmap
		this.add(charIndex, character);
		
		// Plot on map
		this.mGame.getEngine().setCharacter(x, y, character);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
