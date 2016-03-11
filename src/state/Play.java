package state;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.yassir.game.MapParser;
import com.yassir.game.MyInputHandler;
import com.yassir.game.Projectile;
import com.yassir.game.WorldContactListener;
import com.yassir.game.conf;
import com.yassir.game.mainClass;

public class Play implements Screen {

	private mainClass game;
	private Viewport gamePort;
	private OrthographicCamera gameCam;
	private World world;
	private Box2DDebugRenderer b2dr;
	public TextureAtlas ta;
	private MyInputHandler input;
	MapParser map;
	public static ArrayList<Body> deadBodies;
	public Play(mainClass game){
		
		this.game=game;
		gameCam=new OrthographicCamera();
		gamePort=new FitViewport(conf.viewPortWidth/conf.PPM,conf.viewPortHeight/conf.PPM, gameCam);
		world=new World(new Vector2(0,conf.gravity), true);
		b2dr=new Box2DDebugRenderer();
		ta=game.assets.get("hero/AmiSprite.pack",TextureAtlas.class);
		world.setContactListener(new WorldContactListener());
		gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
		map=new MapParser(this,world);
		input=new MyInputHandler(map); 
		deadBodies=new ArrayList<Body>();
	}
	
	public mainClass getGame() {
		return game;
	}

	public void setGame(mainClass game) {
		this.game = game;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		world.step(1/60f,6,2);
		removeBodies();
		gameCam.update();
		if(map.getHero().b2body.getPosition().x>=conf.viewPortWidth/2/conf.PPM)
			gameCam.position.x=MathUtils.lerp(gameCam.position.x, map.getHero().b2body.getPosition().x, 0.05f);
		
		map.getHero().update(delta);
		
		input.update(delta);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		map.render();
		b2dr.render(world, gameCam.combined);
		game.batch.setProjectionMatrix(gameCam.combined);
		game.batch.begin();
		
		map.getHero().draw(game.batch);
		map.updateMonsters(delta);
		
		for(Projectile f : map.getHero().fires){
			if(!f.isDestroyed()){
				f.update(delta);
				f.draw(game.batch);
			}
		}
		
		game.batch.end();
	}
	
	
	public void removeBodies(){
		for(int i=0;i<deadBodies.size();i++){
			if(!world.isLocked()){
				world.destroyBody(deadBodies.get(i));
				deadBodies.remove(i);
			}
		}
	}
	 public OrthographicCamera getGameCam() {
		return gameCam;
	}

	public void setGameCam(OrthographicCamera gameCam) {
		this.gameCam = gameCam;
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		gamePort.update(width,height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		b2dr.dispose();
		game.dispose();
		world.dispose();
		
	}

	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public TextureAtlas getTa() {
		return ta;
	}
	public void setTa(TextureAtlas ta) {
		this.ta = ta;
	}
	public Box2DDebugRenderer getB2dr() {
		return b2dr;
	}

	public void setB2dr(Box2DDebugRenderer b2dr) {
		this.b2dr = b2dr;
	}
}
