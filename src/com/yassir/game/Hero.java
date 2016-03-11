package com.yassir.game;

import java.util.ArrayList;

import state.Play;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Hero extends Sprite {

	public World world;
	public Body b2body;
	private TextureRegion region;
	private float stateTimer;
	protected boolean isJumping;
	protected boolean isShooting;
	protected boolean isCrouching;
	protected boolean isStanding;
	protected boolean isRunning;
	protected boolean isThrowing;
	protected boolean isDie;
	protected boolean isRight;
	protected Play screen;
	public ArrayList<Projectile> fires;
	protected int health;
	
	private enum State {
		stand, run, shoot, thrown, jump, die, fall, runShoot, jumpShoot, crouch, crouchShoot, crouchThrow
	};

	public State currentState;
	private State previousState;
	private Animation MonsterRun;
	private Animation MonsterShoot;
	private Animation MonsterThrow;
	private Animation MonsterStand;
	private Animation MonsterDie;
	private Animation MonsterFall;
	private Animation MonsterRunShoot;
	private Animation MonsterCrouch;
	private Animation MonsterCrouchShoot;
	private Animation MonsterCrouchThrow;
	private TextureRegion MonsterJumpShoot;
	private TextureRegion MonsterJump;
	
	public Hero(Play s, float x, float y) {
		super(s.getTa().findRegion("Aim (1)"));
		world = s.getWorld();
		screen = s;
		region = new TextureRegion(getTexture(), 0, 0, 722, 667);
		setBounds(0, 0, 100 / conf.PPM, 100 / conf.PPM);
		setRegion(region);
		fires = new ArrayList<Projectile>();
		
		defineMonster(x, y);

		isRight = true;
		isStanding = true;
		currentState = State.stand;
		previousState = State.stand;
		stateTimer = 0;
		health=conf.healthHero;
		// Set Animations
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for (int i = 0; i < 10; i++)
			frames.add(s.getTa().findRegion("Aim (" + (i + 1) + ")"));
		MonsterStand = new Animation(0.1f, frames);
		frames.clear();
		for (int i = 0; i < 10; i++)
			frames.add(s.getTa().findRegion("Run (" + (i + 1) + ")"));
		MonsterRun = new Animation(0.1f, frames);
		frames.clear();
		for (int i = 0; i < 5; i++)
			frames.add(s.getTa().findRegion("Shoot (" + (i + 1) + ")"));
		MonsterShoot = new Animation(0.1f, frames);
		frames.clear();
		for (int i = 0; i < 5; i++)
			frames.add(s.getTa().findRegion("Dead (" + (i + 1) + ")"));
		MonsterDie = new Animation(0.1f, frames);
		frames.clear();
		/*
		 * for(int i=0;i<5;i++) frames.add(s.getTa().findRegion("Jump ("
		 * +(i+1)+")")); MonsterJump=new Animation(0.1f,frames); frames.clear();
		 */
		for (int i = 5; i < 10; i++)
			frames.add(s.getTa().findRegion("Jump (" + (i + 1) + ")"));
		MonsterFall = new Animation(0.1f, frames);
		frames.clear();
		for (int i = 0; i < 10; i++)
			frames.add(s.getTa().findRegion("Throw (" + (i + 1) + ")"));
		MonsterThrow = new Animation(0.1f, frames);
		frames.clear();
		for (int i = 0; i < 10; i++)
			frames.add(s.getTa().findRegion("RunShoot (" + (i + 1) + ")"));
		MonsterRunShoot = new Animation(0.1f, frames);
		frames.clear();
		for (int i = 0; i < 10; i++)
			frames.add(s.getTa().findRegion("Crouch (" + (i + 1) + ")"));
		MonsterCrouch = new Animation(0.1f, frames);
		frames.clear();
		for (int i = 0; i < 5; i++)
			frames.add(s.getTa().findRegion("CrouchShoot (" + (i + 1) + ")"));
		MonsterCrouchShoot = new Animation(0.1f, frames);
		frames.clear();
		for (int i = 0; i < 10; i++)
			frames.add(s.getTa().findRegion("CrouchThrow (" + (i + 1) + ")"));
		MonsterCrouchThrow = new Animation(0.1f, frames);
		frames.clear();

		MonsterJumpShoot = new TextureRegion(s.getTa().findRegion("JumpShoot (4)"));
		MonsterJump = new TextureRegion(s.getTa().findRegion("Jump (4)"));
	}

	public void update(float dt) {
		if(health>0){
			setPosition((b2body.getPosition().x - getWidth() / 2), (b2body.getPosition().y - getHeight() / 2));
			currentState = stateManager();
			setRegion(getFrame(dt));
			
			if (!isRight)
				setFlip(true, false);
			if (isCrouching || isThrowing) {
				stopX();
			}
			if (b2body.getLinearVelocity().y == 0) {
				isJumping = false;
			}

			for (int i = 0; i < fires.size(); i++) {
				if (!fires.get(i).isVisible()) {
					fires.remove(i);
				}
			}
		
		}else{
			Play.deadBodies.add(b2body);
		}
	}
	

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public TextureRegion getFrame(float dt) {
		TextureRegion region;
		switch (currentState) {
		case stand:
			region = MonsterStand.getKeyFrame(stateTimer, true);
			break;
		case run:
			region = MonsterRun.getKeyFrame(stateTimer, true);
			break;
		case shoot:
			region = MonsterShoot.getKeyFrame(stateTimer, true);
			break;
		case runShoot:
			region = MonsterRunShoot.getKeyFrame(stateTimer, true);
			break;
		case crouch:
			region = MonsterCrouch.getKeyFrame(stateTimer, true);
			break;
		case crouchShoot:
			region = MonsterCrouchShoot.getKeyFrame(stateTimer, true);
			break;
		case die:
			region = MonsterDie.getKeyFrame(stateTimer);
			break;
		case jump:
			region = MonsterJump;
			break;
		case jumpShoot:
			region = MonsterJumpShoot;
			break;
		case fall:
			region = MonsterJump;
			break;// MonsterFall.getKeyFrame(stateTimer);break;
		case thrown:
			region = MonsterThrow.getKeyFrame(stateTimer);
			break;
		case crouchThrow:
			region = MonsterCrouchThrow.getKeyFrame(stateTimer);
			break;
		default:
			region = MonsterStand.getKeyFrame(stateTimer, true);
			break;
		}
		stateTimer = currentState == previousState ? dt + stateTimer : 0;
		previousState = currentState;
		return region;
	}

	public void defineMonster(float x, float y) {

		BodyDef bdef = new BodyDef();
		bdef.position.set(x / conf.PPM, y / conf.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		FixtureDef fdef = new FixtureDef();
		fdef.friction = 0;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(40 / conf.PPM, 40 / conf.PPM);
		fdef.shape = shape;

		fdef.filter.categoryBits = conf.BIT_HERO;
		fdef.filter.maskBits = conf.BIT_DEFAULT;
		b2body.createFixture(fdef).setUserData(this);
	}
	public void Hit()
	{
		health--;
	}
	public void moveRight(float dt) {
		if (!isCrouching && !isThrowing) {
			b2body.setLinearVelocity(conf.speedX * dt, b2body.getLinearVelocity().y);
		}
		isRunning = true;
		isRight = true;
	}

	public void moveLeft(float dt) {
		if (!isCrouching && !isThrowing) {
			b2body.setLinearVelocity(-conf.speedX * dt, b2body.getLinearVelocity().y);
		}

		isRunning = true;
		isRight = false;
	}

	public void stopX() {
		b2body.setLinearVelocity(0, b2body.getLinearVelocity().y);
		isRunning = false;
	}

	public void crouch() {
		if (!isJumping) {
			isCrouching = true;
			isRunning = false;
		}
	}

	public void stand() {
		if (isCrouching) {
			isCrouching = false;
		}
	}

	public float getBX() {
		return b2body.getPosition().x;
	}

	public float getBY() {
		return b2body.getPosition().y;
	}

	public void jump(float dt) {
		b2body.applyForceToCenter(0,conf.jump, true);
		isJumping = true;
	}

	public void fire() {
		float x=isRight?(getX() + getWidth()*3/2):(getX()+getWidth());
		fires.add(new Projectile(screen,getX(), getY() + getHeight() / 2, getWidth(), isRight));
		isShooting = true;
	}

	public void stopShooting() {
		isShooting = false;
	}

	public void thrown() {
		isThrowing = true;
	}

	public void stopThrowing() {
		if (MonsterCrouchThrow.isAnimationFinished(stateTimer))
			isThrowing = false;
	}

	public State stateManager() {
		// jump
		if (isShooting && isJumping) {
			return currentState = State.jumpShoot;
		} else if (isJumping) {
			if (b2body.getLinearVelocity().y <= 0) {
				return currentState = State.fall;
			} else {
				return currentState = State.jump;
			}
		}
		// crouch
		if (isThrowing && isCrouching) {
			return currentState = State.crouchThrow;
		} else if (isShooting && isCrouching) {
			return currentState = State.crouchShoot;
		} else if (isCrouching) {
			return currentState = State.crouch;
		}

		// stand
		if (isThrowing) {
			return currentState = State.thrown;
		} else if (isRunning && isShooting) {
			return currentState = State.runShoot;
		} else if (isRunning) {
			return currentState = State.run;
		} else if (isShooting) {
			return currentState = State.shoot;
		} else {
			return currentState = State.stand;
		}

	}
	
}
