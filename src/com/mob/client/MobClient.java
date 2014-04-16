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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;  
import com.mob.client.data.GameData;  
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
        
        this.mCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
        this.mCamera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
        this.mCamera.position.set(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.5f, 0);  
        
        this.setScreen("LoadingScreen");  

        // Cargamos inits
        this._grhData = this._initLoader.loadGrhData("Graficos.ind");
        this._bodyData = this._initLoader.loadCuerpos("Personajes.ind");
        this._headData = this._initLoader.loadCabezas("Cabezas.ind");
        this._helmetData = this._initLoader.loadCascos("Cascos.ind");
        
        // Iniciamos engine de Texturas
        this.setSurfaceHandler(new SurfaceHandler(this));
        this.getSurfaceHandler().setGraphicsPath(GAME_GRAPHICS_PATH);
        this.getSurfaceHandler().loadAllTextures();
          
        this.gameData = new GameData(this);  
        this.spriteBatch = new SpriteBatch();  
          
        this.setScreen("TestScreen");  
          
    }  
    
    @Override  
    public void dispose() {  
        if (currentScreen != null) currentScreen.dispose();  
    }  
  
    @Override  
    public void pause() {  
        if (currentScreen != null) currentScreen.pause();  
    }  
  
    @Override  
    public void render() {  
        if (currentScreen != null) {  
            currentScreen.update(Gdx.graphics.getDeltaTime());  
        } else {  
              
        	Gdx.gl.glClearColor(0, 0, 0, 1);  
        	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
        }  
    }  
  
    @Override  
    public void resize(int arg0, int arg1) {  
          
    }  
  
    @Override  
    public void resume() {  
        if (currentScreen != null) currentScreen.resume();  
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