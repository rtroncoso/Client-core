/**
 * Binary INIT files parsing
 * TODO :
 * 	- Fix reading of VB6 binarys, not being able to read VB6 longs and turn them into Java integers
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.loaders;

import com.badlogic.gdx.files.FileHandle;
import com.mob.client.interfaces.Constants;

public abstract class Loader implements Constants {
	
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected FileHandle mFileHandle;

	// ===========================================================
	// Constructors
	// ===========================================================
	

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mFileHandle
	 */
	public FileHandle getFileHandle() {
		return mFileHandle;
	}

	/**
	 * @param mFileHandle the mFileHandle to set
	 */
	public void setFileHandle(FileHandle mFileHandle) {
		this.mFileHandle = mFileHandle;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}
