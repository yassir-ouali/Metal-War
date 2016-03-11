package state;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.yassir.game.conf;
import com.yassir.game.mainClass;

public class MenuScreen implements Screen{
	
	public mainClass getMainClass() {
		return mainClass;
	}

	private final mainClass mainClass;
	private Stage stage;
	private Image backgroundI,playBtnI, fbBtnI, optionBtnI, panierBtnI,musicBtnI,
	sandBtnI, aboutBtnI, optionCercleI, optionTubeI, cercleRougeI, gereeI;;
	private Texture Window_Elements;
	private Texture GUI_Templates_01,background;
	private TextureRegion playBtn, fbBtn, optionBtn, panierBtn;
	private TextureRegion musicBtn, sandBtn, aboutBtn, optionCercle, optionTube, cercleRouge, geree;
	
	
	 public MenuScreen(mainClass play){
		 this.mainClass = play;
//		 
//		 cam = new OrthographicCamera();
//		 cam.setToOrtho(false, 600, 510);
	        
		// gamePort=new FitViewport(conf.viewPortWidth/conf.PPM,conf.viewPortHeight/conf.PPM, cam);
		// cam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
		 
//	     batcher = new SpriteBatch();
//	     batcher.setProjectionMatrix(cam.combined);
	     
	     stage=new Stage(new FitViewport(conf.viewPortWidth,conf.viewPortHeight));
	     
	     Gdx.input.setInputProcessor(stage);
	     
	     background = play.assets.get("ui/background.png",Texture.class);
	     backgroundI=new Image(background);
	     backgroundI.setBounds(0,0,stage.getWidth(),stage.getHeight());
	     stage.addActor(backgroundI);
	     
	     Window_Elements = play.assets.get("ui/Window_Elements.png", Texture.class);
	    
	     GUI_Templates_01 =play.assets.get("ui/GUI_Templates_01.png", Texture.class);
	     
	     /*
	     geree = new TextureRegion(GUI_Templates_01, 340, 710, 59, 60);
	     gereeI=new Image(geree);
	     gereeI.setBounds(40, 430, geree.getRegionWidth(), geree.getRegionHeight());
	     stage.addActor(gereeI);
	     
	     cercleRouge = new TextureRegion(GUI_Templates_01, 320, 784, 96, 99);
	     cercleRougeI=new Image(cercleRouge);
	     cercleRougeI.setBounds(26, 416,cercleRouge.getRegionWidth(),cercleRouge.getRegionHeight());
	     stage.addActor(cercleRougeI);
	     
	     optionTube = new TextureRegion(GUI_Templates_01, 239, 522, 69, 342);
	     optionTubeI=new Image(optionTube);
	     optionTubeI.setBounds(40, 320, optionTube.getRegionWidth(),optionTube.getRegionHeight());
	     stage.addActor(optionTubeI);
	     
	     optionCercle = new TextureRegion(GUI_Templates_01, 142, 622, 70, 73);
	     optionCercleI=new Image(optionCercle);
	     optionCercleI.setBounds(40, 269, optionCercle.getRegionWidth(),optionCercle.getRegionHeight());
	     stage.addActor(optionCercleI);
	     
	     musicBtn = new TextureRegion(GUI_Templates_01, 92, 636, 36, 41);
	     musicBtnI=new Image(musicBtn);
	     musicBtnI.setBounds(50, 325,musicBtn.getRegionWidth(), musicBtn.getRegionHeight());
	     stage.addActor(musicBtnI);
	     
	     sandBtn = new TextureRegion(GUI_Templates_01, 84, 720, 40, 36);
	     sandBtnI=new Image(sandBtn);
	     sandBtnI.setBounds(47, 378,sandBtn.getRegionWidth(),sandBtn.getRegionHeight());
	     stage.addActor(sandBtnI);
	     
	     aboutBtn = new TextureRegion(GUI_Templates_01, 92, 554, 30, 43);
	     aboutBtnI=new Image(aboutBtn);
	     aboutBtnI.setBounds(20, 250,aboutBtn.getRegionWidth(),aboutBtn.getRegionHeight());
	     stage.addActor(sandBtnI);
	     */
	     playBtn = new TextureRegion(Window_Elements,588,455,333,147);
	     playBtnI=new Image(playBtn);
	     playBtnI.setPosition(110, 190);
	     playBtnI.setSize(280, 60);
	     playBtnI.setBounds(stage.getWidth(),240, playBtn.getRegionWidth()/2, playBtn.getRegionHeight()/2);
	     playBtnI.addAction(moveTo(160,240,0.5f));
	     playBtnI.addListener(new ClickListener(){
	    	 @Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		// TODO Auto-generated method stub
	    		 getMainClass().setScreen(new Play(getMainClass()));
	    		dispose();
	    		
	    	}
	     });
	     stage.addActor(playBtnI);
	     /*
	     fbBtn = new TextureRegion(Window_Elements,708,611,303,123);
	     fbBtnI=new Image(fbBtn);1
	     fbBtnI.setPosition(200,260);
	     stage.addActor(fbBtnI);
	     
	     optionBtn = new TextureRegion(Window_Elements,206,433,130,379);
	     optionBtnI =new Image(optionBtn);
	     optionBtnI.setPosition(20, 250);
	     stage.addActor(optionBtnI);
	     
	     panierBtn = new TextureRegion(Window_Elements,1250,690,109,128);	     
	     panierBtnI= new Image(panierBtn);
	     panierBtnI.setPosition(470, 400);
	     stage.addActor(panierBtnI);*/
	     
	 }
	  
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        
        stage.draw();     
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.getViewport().update(width, height);
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

	public void dispose() {
        // We must dispose of the texture when we are finished.
		Window_Elements.dispose();
		GUI_Templates_01.dispose();
		playBtnI.remove();
		stage.dispose();
    }

}
