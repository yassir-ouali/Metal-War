package com.yassir.game;

import java.util.ArrayList;

import state.Play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Monster extends Hero {
	public ArrayList<Projectile> firesMonster;
	private Hero hero;
	private static TextureRegion textureRegionFire;
	private static Texture textureFire;
	
	public Monster(Play s, float x, float y, Hero hero) {
		super(s,x,y);
		firesMonster = new ArrayList<Projectile>();
		this.hero = hero;
		health=conf.healthMonster;
		textureFire=s.getGame().assets.get("hero/Blue (1).png",Texture.class);
		//textureFire = new Texture(Gdx.files.internal("hero/Blue (1).png"));
		textureRegionFire= new TextureRegion(textureFire);
	}
	
	@Override 
	public void moveRight(float dt) {
		if (!isCrouching && !isThrowing) {
			b2body.setLinearVelocity(conf.speedXMonster * dt, b2body.getLinearVelocity().y);
		}
		isRunning = true;
		isRight = true;
	}
	@Override
	public void moveLeft(float dt) {
		if (!isCrouching && !isThrowing) {
			b2body.setLinearVelocity(-conf.speedXMonster * dt, b2body.getLinearVelocity().y);
		}

		isRunning = true;
		isRight = false;
	}
	
	@Override 
	public void fire() {
		fires.add(new Projectile(screen, getX(), getY() + getHeight() / 2, getWidth(), isRight, newAngle()*25 , textureFire()));
		isShooting = true;
	}
	
	private float heroDown(){
		if(this.getBY()>hero.getBY()){
			return -(hero.getBX()-this.getBX())/(hero.getBY()-this.getBY());
		}
		
		return (float) ((hero.getBX()-this.getBX())/(hero.getBY()-this.getBY())/Math.PI);
	}
	
	private float angle(){
		System.out.println("iunfeoirgvnzoivnezipubgvneirupbh "+(float) ((hero.getBX()-this.getBX())/(hero.getBY()-this.getBY())/Math.PI)*90);
		if(hero.getBX()>this.getBX()){
			return (float) (Math.atan((hero.getBY()-this.getBY())/(hero.getBX()-this.getBX()))/Math.PI);
		}
		return (float) -(Math.atan((hero.getBY()-this.getBY())/(hero.getBX()-this.getBX()))/Math.PI);
	}
	
	
	private float monsterFireX;
	private float monsterFireY;
	private float heroSGX;
	private float heroSGY;
	
	
	
	
//	INDICE DE ROTATION
	private float newAngle(){
		monsterFireX = this.getBX()+this.getWidth();
		monsterFireY = this.getBY()+this.getHeight()/2;
		heroSGX = hero.getBX()+hero.getWidth()/2;
		heroSGY = hero.getBY()+hero.getHeight()/2;
		
		
		if(hero.getBX()>this.getBX()){
			return (float) (Math.atan((heroSGY-monsterFireY)/(heroSGX-monsterFireX))/Math.PI);
		}
		return (float) -(Math.atan((heroSGY-monsterFireY)/(heroSGX-monsterFireX))/Math.PI);
		
	}
	
	private TextureRegion textureFire(){
		
		return this.textureRegionFire;
	}
	
	
	
	
}	