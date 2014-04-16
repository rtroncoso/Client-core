/**
 * Basic sprite interface
 * TODO : 
 * 	- Linchar esto
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.interfaces;

public interface ISprite {

	public void reset();
	public abstract void update(float dt);
	public void show();
	public void hide();
	public abstract void draw();
}
