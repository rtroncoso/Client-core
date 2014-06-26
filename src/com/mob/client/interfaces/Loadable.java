package com.mob.client.interfaces;

import java.util.Vector;

public interface Loadable<T> {
	public Vector<T> load(String initFileName);
}
