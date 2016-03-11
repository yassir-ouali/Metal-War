package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.yassir.game.mainClass;


public class Loading implements Screen{

	private mainClass mainClass;
	private Texture loading;
	
	public Loading(mainClass mainClass){
		this.mainClass=mainClass;
		loading=new Texture(Gdx.files.internal("ui/loading.png"));
	}
	
	public void assets(){
		mainClass.assets.load("ui/background.png",Texture.class);
		mainClass.assets.load("ui/Window_Elements.png",Texture.class);
		mainClass.assets.load("ui/GUI_Templates_01.png",Texture.class);
		mainClass.assets.load("hero/Blue (1).png",Texture.class);
		mainClass.assets.load("hero/AmiSprite.pack",TextureAtlas.class);
	//	mainClass.assets.load("therightone.tmx",TiledMap.class);
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		assets();
	}

	private void update(float delta) {
        
        if (mainClass.assets.update()) {
        	mainClass.setScreen(new MenuScreen(mainClass));
        }
    }
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		mainClass.batch.begin();
		mainClass.batch.draw(loading,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mainClass.batch.end();
		update(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		
	}

}
