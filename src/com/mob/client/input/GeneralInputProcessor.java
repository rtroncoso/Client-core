package com.mob.client.input;

import com.badlogic.gdx.InputProcessor;

public class GeneralInputProcessor implements InputProcessor {
   @Override
   public boolean keyDown (int keycode) {
      return false;
   }

   @Override
   public boolean keyUp (int keycode) {
      return false;
   }

   @Override
   public boolean keyTyped (char character) {
      return false;
   }

   @Override
   public boolean touchDown (int x, int y, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchUp (int x, int y, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchDragged (int x, int y, int pointer) {
      return false;
   }

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

   @Override
   public boolean scrolled (int amount) {
      return false;
   }
}