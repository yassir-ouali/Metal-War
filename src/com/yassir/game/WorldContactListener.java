package com.yassir.game;

import state.Play;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub

		heroContacts(contact);
		monsterContacts(contact);
	}
	public void heroContacts(Contact contact){
		Fixture fixA=contact.getFixtureA();
		Fixture fixB=contact.getFixtureB();
		
		if((fixA.getUserData()!=null && Hero.class.isAssignableFrom(fixA.getUserData().getClass()) && !Monster.class.isAssignableFrom(fixA.getUserData().getClass())) || (fixB.getUserData()!=null && Hero.class.isAssignableFrom(fixB.getUserData().getClass())) && !Monster.class.isAssignableFrom(fixB.getUserData().getClass())){
			
			Fixture head=fixA.getUserData()!=null && Hero.class.isAssignableFrom(fixA.getUserData().getClass())?fixA:fixB;
			Fixture object=fixA==head?fixB:fixA;

			if( object.getUserData()!=null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
				((InteractiveTileObject)object.getUserData()).onHit();
			}
			
			if( object.getUserData()!=null && Projectile.class.isAssignableFrom(object.getUserData().getClass())){
				Hero hero=(Hero) head.getUserData();
				hero.Hit();
				((Projectile)object.getUserData()).setDestroyed(true);
				Play.deadBodies.add(object.getBody());
			}
			

		}
	}
	public void monsterContacts(Contact contact){
		Fixture fixA=contact.getFixtureA();
		Fixture fixB=contact.getFixtureB();
		
		if((fixA.getUserData()!=null && Monster.class.isAssignableFrom(fixA.getUserData().getClass())) || (fixB.getUserData()!=null && Monster.class.isAssignableFrom(fixB.getUserData().getClass()))){
			
			Fixture head=fixA.getUserData()!=null && Monster.class.isAssignableFrom(fixA.getUserData().getClass())?fixA:fixB;
			Fixture object=fixA==head?fixB:fixA;

			
			if( object.getUserData()!=null && Projectile.class.isAssignableFrom(object.getUserData().getClass())){
				Monster monster=(Monster) head.getUserData();
				monster.Hit();
				((Projectile)object.getUserData()).setDestroyed(true);
				Play.deadBodies.add(object.getBody());
			}
			

		}
	}
	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
