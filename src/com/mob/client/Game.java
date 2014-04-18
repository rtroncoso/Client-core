/**
 * Game's main class. 
 * @author Rodrigo Troncoso
 * @version 0.1
 */
package com.mob.client;

import java.lang.reflect.Constructor;  
import java.lang.reflect.InvocationTargetException;  
import java.util.HashMap;  
  



import java.util.Vector;

import com.badlogic.gdx.ApplicationListener;  
import com.badlogic.gdx.graphics.OrthographicCamera;  
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;  
import com.mob.client.data.BodyData;
import com.mob.client.data.GrhData;
import com.mob.client.data.HeadData;
import com.mob.client.data.HelmetData;
import com.mob.client.elements.Map;
import com.mob.client.handlers.CharacterHandler;
import com.mob.client.handlers.MapHandler;
import com.mob.client.handlers.SurfaceHandler;
import com.mob.client.interfaces.IConstants;
import com.mob.client.loaders.InitLoader;
import com.mob.client.screens.Screen;  
  
public class Game implements ApplicationListener, IConstants {  

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
    protected OrthographicCamera mCamera;  
    protected HashMap<String, Screen> mScreens;  
    protected SpriteBatch mSpriteBatch;  
    
    protected InitLoader mInitLoader;
    
    protected Vector<GrhData> mGrhData;
    protected Vector<BodyData> mBodyData;
    protected Vector<HeadData> mHeadData;
    protected Vector<HelmetData> mHelmetData;

	protected MapHandler mMapHandler;
    protected SurfaceHandler mSurfaceHandler;
    protected CharacterHandler mCharacterHandler;

	protected Map mCurrentMap;
    protected Screen mCurrentScreen;
    
    protected BitmapFont mFont;

	// ===========================================================
	// Constructors
	// ===========================================================
    

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
    public void create() {  
    	this.mScreens = new HashMap<String, Screen>();  
        this.mGrhData = new Vector<GrhData>();
        this.mBodyData = new Vector<BodyData>();
        this.mHeadData = new Vector<HeadData>();
        this.mHelmetData = new Vector<HelmetData>();
        this.mInitLoader = new InitLoader();
        this.mMapHandler = new MapHandler(this);
        this.mCurrentMap = new Map(this);
        this.mCharacterHandler = new CharacterHandler(this);
        this.mFont = new BitmapFont();
    }
    
    public void update (float dt) {}  
    public void dispose() {}
    public void pause() {}
    public void render() {}
    public void resize(int arg0, int arg1) {}  
    public void resume() {}  
    
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	* @return the _grhData
	*/
	public Vector<GrhData> getGrhData() {
		return mGrhData;
	}
	
	/**
	* @param _grhData the _grhData to set
	*/
	public void setGrhData(Vector<GrhData> _grhData) {
		this.mGrhData = _grhData;
	}
	
	/**
	* @return the _bodyData
	*/
	public Vector<BodyData> getBodyData() {
		return mBodyData;
	}
	
	/**
	* @param _bodyData the _bodyData to set
	*/
	public void setBodyData(Vector<BodyData> _bodyData) {
		this.mBodyData = _bodyData;
	}

	/**
	 * @return the _headData
	 */
	public Vector<HeadData> getHeadData() {
		return mHeadData;
	}

	/**
	 * @param _headData the _headData to set
	 */
	public void setHeadData(Vector<HeadData> _headData) {
		this.mHeadData = _headData;
	}

	/**
	 * @return the _helmetData
	 */
	public Vector<HelmetData> getHelmetData() {
		return mHelmetData;
	}

	/**
	 * @param _helmetData the _helmetData to set
	 */
	public void setHelmetData(Vector<HelmetData> _helmetData) {
		this.mHelmetData = _helmetData;
	}

	/**
	 * @return the _surfaceHandler
	 */
	public SurfaceHandler getSurfaceHandler() {
		return mSurfaceHandler;
	}

	/**
	 * @param _surfaceHandler the _surfaceHandler to set
	 */
	public void setSurfaceHandler(SurfaceHandler _surfaceHandler) {
		this.mSurfaceHandler = _surfaceHandler;
	}

	/**
	 * @return the mCamera
	 */
	public OrthographicCamera getCamera() {
		return mCamera;
	}

	/**
	 * @param mCamera the mCamera to set
	 */
	public void setCamera(OrthographicCamera mCamera) {
		this.mCamera = mCamera;
	}

    /**
	 * @return the mMapHandler
	 */
	public MapHandler getMapHandler() {
		return mMapHandler;
	}

	/**
	 * @param mMapHandler the mMapHandler to set
	 */
	public void setMapHandler(MapHandler mMapHandler) {
		this.mMapHandler = mMapHandler;
	}

	/**
	 * @return the mSpriteBatch
	 */
	public SpriteBatch getSpriteBatch() {
		return mSpriteBatch;
	}

	/**
	 * @param mSpriteBatch the mSpriteBatch to set
	 */
	public void setSpriteBatch(SpriteBatch mSpriteBatch) {
		this.mSpriteBatch = mSpriteBatch;
	}
	
	/**
	 * @return the mCurrentMap
	 */
	public Map getCurrentMap() {
		return mCurrentMap;
	}

	/**
	 * @param mCurrentMap the mCurrentMap to set
	 */
	public void setCurrentMap(Map mCurrentMap) {
		this.mCurrentMap = mCurrentMap;
	}    
	
	/**
	 * @return the mCharacterHandler
	 */
	public CharacterHandler getCharacterHandler() {
		return mCharacterHandler;
	}

	/**
	 * @param mCharacterHandler the mCharacterHandler to set
	 */
	public void setCharacterHandler(CharacterHandler mCharacterHandler) {
		this.mCharacterHandler = mCharacterHandler;
	}

	/**
	 * @return the mFont
	 */
	public BitmapFont getFont() {
		return mFont;
	}

	/**
	 * @param mFont the mFont to set
	 */
	public void setFont(BitmapFont mFont) {
		this.mFont = mFont;
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	public void setScreen (String screenClassName) {  
        
        screenClassName = "com.mob.client.screens."+screenClassName;  
        Screen newScreen = null;  
          
        if (this.mScreens.containsKey(screenClassName) == false) {  
              
            try {  
                Class<?> screenClass =  Class.forName(screenClassName);   
                Constructor<?> constructor = screenClass.getConstructor(Game.class);      
                newScreen = (Screen) constructor.newInstance(this);  
                this.mScreens.put(screenClassName, newScreen);  
            } catch ( InvocationTargetException ex ){  
                System.err.println( ex + " Screen with Wrong args in Constructor.");  
            } catch ( NoSuchMethodException ex ){  
            } catch ( ClassNotFoundException ex ){  
              System.err.println( ex + " Screen Class Not Found.");  
            } catch( InstantiationException ex ){  
              System.err.println( ex + " Screen Must be a concrete class.");  
            } catch( IllegalAccessException ex ){  
              System.err.println( ex + " Screen with Wrong number of args.");  
            }  
        } else {  
            newScreen = this.mScreens.get(screenClassName);  
        }  
          
        if (newScreen == null) return;  
          
        if (this.mCurrentScreen != null) {  
            //remove current screen!  
            mCurrentScreen.destroy();  
        }  
        this.mCurrentScreen = newScreen;  
        this.mCurrentScreen.createScreen();  
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}  