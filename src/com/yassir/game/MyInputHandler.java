package com.yassir.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MyInputHandler {

	private long lastFire;
	private MapParser map;

	public MyInputHandler(MapParser map) {
		this.map=map;
	}
	public void update(float dt)
	{
		 if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && map.getHero().b2body.getLinearVelocity().y==0){
         	map.getHero().jump(dt);
         }
		 
         if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
         	map.getHero().moveRight(dt);
         	
         }
         
         
         if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
         	map.getHero().moveLeft(dt);
			}
         if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
         	if(System.currentTimeMillis() - lastFire>conf.fireDephasage){
         		map.getHero().fire();
         		lastFire = System.currentTimeMillis();
         	}
         }else{
         	map.getHero().stopShooting();
         }
         if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
         	map.getHero().crouch();
         	
         }else{
         	map.getHero().stand();
         }
         if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
         	map.getHero().thrown();
         }else{
         	map.getHero().stopThrowing();
         }
         if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& !Gdx.input.isKeyPressed(Input.Keys.LEFT) /*&& m1.b2body.getLinearVelocity().x !=0*/){
         	map.getHero().stopX();
         	
         }
	}
	
}
