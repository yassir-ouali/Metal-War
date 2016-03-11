package com.yassir.game;

import state.Play;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
public class Coin extends InteractiveTileObject {
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;

    public Coin(Play screen, MapObject object,MapParser parser){
        super(screen, object,parser);
        
        fixture.setUserData(this);
        
    }

   @Override
    public void onHit() {
       getCell(2).setTile(null);
       Play.deadBodies.add(body);
       //setCategoryFilter(conf.BIT_DESTROYED);
      // body.getWorld().destroyBody(body);
    }
}