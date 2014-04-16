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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;  
import com.badlogic.gdx.utils.TimeUtils;
import com.mob.client.data.BodyData;
import com.mob.client.data.GameData;  
import com.mob.client.data.AnimationData;
import com.mob.client.data.GrhData;
import com.mob.client.data.HeadData;
import com.mob.client.data.HelmetData;
import com.mob.client.engine.TileEngine;
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
    public GameData gameData;  
    public SpriteBatch spriteBatch;  
    public int screenWidth = 0;  
    public int screenHeight = 0;  
    public Screen currentScreen;

    protected OrthographicCamera mCamera;  
    protected TileEngine mEngine;
    protected InitLoader _initLoader;
    protected HashMap<String, Screen> _screens;  
    protected Vector<GrhData> _grhData;
    protected Vector<BodyData> _bodyData;
    protected Vector<HeadData> _headData;
    protected Vector<HelmetData> _helmetData;
    protected SurfaceHandler _surfaceHandler;

	// ===========================================================
	// Constructors
	// ===========================================================


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
    public void create() {  
    	this._screens = new HashMap<String, Screen>();  
        this._grhData = new Vector<GrhData>();
        this._bodyData = new Vector<BodyData>();
        this._headData = new Vector<HeadData>();
        this._helmetData = new Vector<HelmetData>();
        this._initLoader = new InitLoader(this);
          
    }
    
    public void update (float dt) {}  
    
    public void dispose() {  
        // TODO Auto-generated method stub  
    }  
  
    public void pause() {  
        // TODO Auto-generated method stub  
    }  
  
    public void render() {  
        // TODO Auto-generated method stub  
    }  
  
    public void resize(int arg0, int arg1) {  
        // TODO Auto-generated method stub  
    }  
  
    public void resume() {  
        // TODO Auto-generated method stub  
    }  
    
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	* @return the _grhData
	*/
	public Vector<GrhData> getGrhData() {
		return _grhData;
	}
	
	/**
	* @param _grhData the _grhData to set
	*/
	public void setGrhData(Vector<GrhData> _grhData) {
		this._grhData = _grhData;
	}
	
	/**
	* @return the _bodyData
	*/
	public Vector<BodyData> getBodyData() {
		return _bodyData;
	}
	
	/**
	* @param _bodyData the _bodyData to set
	*/
	public void setBodyData(Vector<BodyData> _bodyData) {
		this._bodyData = _bodyData;
	}

	/**
	 * @return the _headData
	 */
	public Vector<HeadData> getHeadData() {
		return _headData;
	}

	/**
	 * @param _headData the _headData to set
	 */
	public void setHeadData(Vector<HeadData> _headData) {
		this._headData = _headData;
	}

	/**
	 * @return the _helmetData
	 */
	public Vector<HelmetData> getHelmetData() {
		return _helmetData;
	}

	/**
	 * @param _helmetData the _helmetData to set
	 */
	public void setHelmetData(Vector<HelmetData> _helmetData) {
		this._helmetData = _helmetData;
	}

	/**
	 * @return the _surfaceHandler
	 */
	public SurfaceHandler getSurfaceHandler() {
		return _surfaceHandler;
	}

	/**
	 * @param _surfaceHandler the _surfaceHandler to set
	 */
	public void setSurfaceHandler(SurfaceHandler _surfaceHandler) {
		this._surfaceHandler = _surfaceHandler;
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
	 * @return the mEngine
	 */
	public TileEngine getEngine() {
		return mEngine;
	}

	/**
	 * @param mEngine the mEngine to set
	 */
	public void setEngine(TileEngine mEngine) {
		this.mEngine = mEngine;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void setScreen (String screenClassName) {  
        
        screenClassName = "com.mob.client.screens."+screenClassName;  
        Screen newScreen = null;  
          
        if (this._screens.containsKey(screenClassName) == false) {  
              
            try {  
                Class<?> screenClass =  Class.forName(screenClassName);   
                Constructor<?> constructor = screenClass.getConstructor(Game.class);      
                newScreen = (Screen) constructor.newInstance(this);  
                this._screens.put(screenClassName, newScreen);  
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
            newScreen = this._screens.get(screenClassName);  
        }  
          
        if (newScreen == null) return;  
          
        if (this.currentScreen != null) {  
            //remove current screen!  
            currentScreen.destroy();  
        }  
        this.currentScreen = newScreen;  
        this.currentScreen.createScreen();  
	}  
	
	public AnimationData initGrh(int grhIndex, int started) {
	
		int tmpStarted = 0, tmpFrameCounter = 0, tmpTicksCounter = 0;
		boolean tmpLoops;
		double tmpSpeedCounter = 0.0d;
		
		if(grhIndex == 0) return null;
		
		if(started == 2) {
			if(this._grhData.get(grhIndex).getFrames().length > 1) {
				tmpStarted = 1;
			} else {
				tmpStarted = 0;
			}
		} else {
			if(this._grhData.get(grhIndex).getFrames().length == 0) tmpStarted = 0;
			tmpStarted = started;
		}
		
		if(tmpStarted >= 1) {
			tmpLoops = true;
		} else {
			tmpLoops = false;
		}
		
		tmpFrameCounter = 1;
		tmpSpeedCounter = this._grhData.get(grhIndex).getSpeed();
		tmpTicksCounter = (int) TimeUtils.millis();
		
		return new AnimationData(grhIndex, tmpFrameCounter, tmpSpeedCounter, tmpTicksCounter, tmpStarted, tmpLoops);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}  