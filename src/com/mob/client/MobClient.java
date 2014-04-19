/**
 * This is our main class. It gets called by the launcher. 
 * We load all resources and init everything here.
 * @author Rodrigo Troncoso
 * @version 0.1
 */
package com.mob.client;
import com.badlogic.gdx.Gdx;  
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch; 
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.handlers.SurfaceHandler;
import com.mob.client.interfaces.IConstants;
  
  
public class MobClient extends Game implements IConstants {  

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================


	// ===========================================================
	// Constructors
	// ===========================================================


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
    @Override  
    public void create() {  
          
        super.create();  
        
        // We render using YDown instead of YUp
        this.mCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
        this.mCamera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
        this.mCamera.position.set(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.5f, 0);  
        
        // Calculate camera zoom according to aspect ratio
		this.mCamera.zoom = (Gdx.graphics.getWidth() / Gdx.graphics.getHeight()) * (GAME_SCREEN_ZOOM / ((Gdx.graphics.getWidth() / Gdx.graphics.getHeight()))); 
        
        this.setScreen("LoadingScreen");  

        // Load INIT files
        this.mGrhData = this.mInitLoader.loadGrhData("Graficos.ind");
        this.mBodyData = this.mInitLoader.loadCuerpos("Personajes.ind");
        this.mWeaponData = this.mInitLoader.loadWeapons("Armas.dat");
        this.mShieldData = this.mInitLoader.loadShields("Escudos.dat");
        this.mHeadData = this.mInitLoader.loadCabezas("Cabezas.ind");
        this.mHelmetData = this.mInitLoader.loadCascos("Cascos.ind");
        this.mFxData = this.mInitLoader.loadFxs("Fxs.ind");
        
        // Init texture engine
        this.setSurfaceHandler(new SurfaceHandler(this));
        this.getSurfaceHandler().setGraphicsPath(GAME_GRAPHICS_PATH);
          
        // Init spritebatch
        this.mSpriteBatch = new SpriteBatch();
        
        // Load font
        Texture text = new Texture(Gdx.files.internal(GAME_FONTS_PATH + "tahoma-bold.png"));
        this.mFont = new BitmapFont(Gdx.files.internal("data/fonts/tahoma-bold.fnt"), new TextureRegion(text), true);
          
        // Load testing screen
        this.setScreen("TestScreen");  
          
    }  
    
    @Override  
    public void dispose() {  
        if (mCurrentScreen != null) mCurrentScreen.dispose();  
    }  
  
    @Override  
    public void pause() {  
        if (mCurrentScreen != null) mCurrentScreen.pause();  
    }  
  
    @Override  
    public void render() {  
        if (mCurrentScreen != null) {  
            mCurrentScreen.update(Gdx.graphics.getRawDeltaTime());  
        } else {  
              
        	Gdx.gl.glClearColor(0, 0, 0, 1);  
        	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
        }  
    }  
  
    @Override  
    public void resize(int arg0, int arg1) {  
          if(mCurrentScreen != null) mCurrentScreen.resize(arg0, arg1);
    }  
  
    @Override  
    public void resume() {  
        if (mCurrentScreen != null) mCurrentScreen.resume();  
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