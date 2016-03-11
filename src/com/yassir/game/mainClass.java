package com.yassir.game;

import state.Loading;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class mainClass extends Game {
	public SpriteBatch batch;
	public AssetManager assets;
	@Override
	public void create () {
		batch = new SpriteBatch();
		assets=new AssetManager();
		setScreen(new Loading(this));
	}

	@Override
	public void render () {
	super.render();
	}
}
