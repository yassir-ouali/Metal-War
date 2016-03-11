package com.yassir.game;

import state.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Projectile extends Sprite{
	private World world;
	private Body b2body;
	private TextureRegion region;
	private Play screen;
	private boolean fireRight;
	private float coorX;
	private float width;
	private float indiceOrientation;
	private boolean destroyed;
	
	public Projectile(Play s,float x,float y,float width, boolean isRight, float indiceOrientation, TextureRegion texture){
		
		super(texture);
		
		world=s.getWorld();
		this.screen=s;
		this.fireRight=isRight;
		this.world=screen.getWorld();
		this.width=width;
		this.indiceOrientation = indiceOrientation;
		destroyed=false;
		setBounds(x,y,30/conf.PPM,22/conf.PPM);
		defineFireBody();
		
		
		
		
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public Projectile(Play s,float x,float y,float width, boolean isRight){
		super(s.getGame().assets.get("hero/Blue (1).png",Texture.class));
		world=s.getWorld();
		this.screen=s;
		this.fireRight=isRight;
		this.world=screen.getWorld();
		this.width=width;
		setBounds(x,y,30/conf.PPM,22/conf.PPM);
		defineFireBody();
	}

	private void defineFireBody() {
		BodyDef bdef=new BodyDef();
		if(fireRight){
			bdef.position.set(getX()+width*3/2,getY());
		}else{
			bdef.position.set(getX()-width*1/3,getY());
			setFlip(true,false);
		}
		
		bdef.type=BodyDef.BodyType.KinematicBody;
		b2body=world.createBody(bdef);
		FixtureDef fdef=new FixtureDef();
		PolygonShape ps=new PolygonShape();
		ps.setAsBox(8/conf.PPM,8/conf.PPM);
		
		fdef.shape=ps;
		fdef.isSensor=true;

		
		b2body.createFixture(fdef).setUserData(this);
		
		//b2body.setTransform(10, 0,0);
		b2body.setLinearVelocity(fireRight?conf.fireSpeed/conf.PPM:-conf.fireSpeed/conf.PPM, indiceOrientation);
	}
	
	public boolean isVisible(){
		if(getX()-coorX>Gdx.graphics.getWidth()||-getX()+coorX>Gdx.graphics.getWidth()){
			return false;
		}
		return true;
	}

	public void update(float dt){
		setPosition((b2body.getPosition().x-getWidth()/2),( b2body.getPosition().y-getHeight()/2));
		
		
	}
	
	
	
	
}
