package monster;

import com.yassir.game.Hero;
import com.yassir.game.Monster;
import com.yassir.game.conf;

public class HandleInputMonster {
	private Monster monster;
	private Hero hero;
	private long lastFire;
	private float oldMonsterX;
	
	private boolean heroHoreZone;
	private boolean heroHoreZoneMFix;
	private boolean heroApproachRight;
	private boolean heroApproachLeft;
	private boolean heroVuRight;
	private boolean heroVuLeft;
	private boolean heroUp;
	private boolean heroDown;
	private boolean monsterJump;
	private boolean heroLeft;
	
	private float monsterMinX;
	private float monsterMinX2;
	
	private int i = 0;
	private int j2 = 0;
	private int j = 0;
	
	public HandleInputMonster(Hero hero, Monster monster){
		this.hero = hero;
		this.monster = monster;
		
	}
	
	
	public void monsterComportement2(float delta){
		heroApproachLeft();
		heroApproachRight();
		heroVuRight();
		heroVuLeft();
		heroHoreZone();
		heroHoreZoneMFix();
		
		
		
				
		if(heroHoreZone){
			if (monster.getBX()>=1675/conf.PPM){
				monster.moveLeft(delta);
				
	        }else if (monster.getBX()<=1480/conf.PPM ){
				monster.moveRight(delta);
	        }
			if(heroHoreZoneMFix){
				monster.moveRight(delta);
			}
		}else{
			if(heroVuLeft || heroVuRight){
				
				oldMonsterX = monster.getBX();	
				
				if(heroVuLeft){
					if (monster.getBX()>=1480/conf.PPM){
						monster.moveLeft(delta);
					}else{
						monster.stopX();
					}
					if((System.currentTimeMillis() - lastFire>conf.fireDephasage)){
						monster.thrown();;
						lastFire = System.currentTimeMillis();
					}
				}
				
				if(heroVuRight){
					if (monster.getBX()<=1675/conf.PPM){
						monster.moveRight(delta);
					}else{
						monster.stopX();
					}
						
					if((System.currentTimeMillis() - lastFire>conf.fireDephasage)){
						monster.thrown();
						lastFire = System.currentTimeMillis();
					}
				}
			}else{
				
				if(hero.getBX()<monster.getBX() && i==1){
					monster.moveLeft(delta);
					i=0;
				}
				if(hero.getBX()>monster.getBX() && i==0){
					monster.moveRight(delta);
					i=1;
				}
				monster.stopX();
				if(HeroTresHB()){
					if((System.currentTimeMillis() - lastFire>conf.fireDephasage)){
						monster.fire();
						lastFire = System.currentTimeMillis();
					}
				}else{
					if((System.currentTimeMillis() - lastFire>conf.fireDephasage)){
						monster.thrown();
						lastFire = System.currentTimeMillis();
					}
				}
			}
		}
		
		monster.stopShooting();
		monster.stopThrowing();
		
		if(j2==0)
			monsterMinX = monster.getX();
		j2++;
		//System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm  "+monsterMinX);
		oldMonsterX = monster.getBX();
	}
	
	
	
	
	
	
	public void monsterComportement(float delta){
		//verifier toutes les fonctions du monsterHero avant d aller au condition if et void le comportement du monster
		heroApproachLeft();
		heroApproachRight();
		heroVuRight();
		heroVuLeft();
		heroHoreZone();
		heroHoreZoneMFix();
		
		oldMonsterX = monster.getBX();
		
		
		if(heroHoreZone){
			if (monster.getBX()>=1200/conf.PPM){
				monster.moveLeft(delta);
	        }else if (monster.getBX()<=1000/conf.PPM ){
				monster.moveRight(delta);
	        }
			if(heroHoreZoneMFix){
				monster.moveRight(delta);
			}
		}else{
			
			if(heroVuLeft || heroVuRight){
				
					
				
				if(heroVuLeft){
					if (monster.getBX()>=1000/conf.PPM){
						monster.moveLeft(delta);
					}else{
						monster.stopX();
					}
					if((System.currentTimeMillis() - lastFire>conf.fireDephasage)){
						monster.fire();
						lastFire = System.currentTimeMillis();
					}
				}
				
				if(heroVuRight){
					if (monster.getBX()<=1200/conf.PPM){
						monster.moveRight(delta);
					}else{
						monster.stopX();
					}
						
					if((System.currentTimeMillis() - lastFire>conf.fireDephasage)){
						monster.fire();
						lastFire = System.currentTimeMillis();
					}
				}
			}else{
				
				if(hero.getBX()<monster.getBX() && i==1){
					monster.moveLeft(delta);
					i=0;
				}
				if(hero.getBX()>monster.getBX() && i==0){
					monster.moveRight(delta);
					i=1;
				}
				monster.stopX();
				if((System.currentTimeMillis() - lastFire>conf.fireDephasage)){
					monster.fire();
					lastFire = System.currentTimeMillis();
				}
			}
		}
		
		monster.stopShooting();
		if(j2==0)
			monsterMinX2 = monster.getX();
		j++;
		//System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm  "+monsterMinX);
		oldMonsterX = monster.getBX();
		
	}
	
		

	
	
//	si le hero approche le monstre le detecte 
	
	
	private void heroHoreZoneMFix(){
		if(heroHoreZone && oldMonsterX==monster.getBX()){
			heroHoreZoneMFix = true;
		}else{
			heroHoreZoneMFix = false;
		}
	}
	
	private void heroHoreZone(){
		if(heroApproachLeft || heroApproachRight){
			heroHoreZone = false;
		}else {
			heroHoreZone = true;
		}
	}
	
	private void heroApproachRight(){
		if(hero.getBX()-monster.getBX()<4.5 && hero.getBX()-monster.getBX()>=0){
			this.heroApproachRight = true;
		
		}else{
			this.heroApproachRight = false;
			
		}
	}
	
	private void heroApproachLeft(){
		if(this.monster.getBX()-this.hero.getBX()<4.5  && this.monster.getBX()-this.hero.getBX()>=0){
			this.heroApproachLeft = true;
		}else{
			this.heroApproachLeft = false;
		}
	}
	
	
//	si le hero est vu par le mostre right or left
	
	private void heroVuRight(){
		if(hero.getBX()-monster.getBX()<3.5 && hero.getBX()-monster.getBX()>=1.2 ){
			heroVuRight = true;
		}else{
			heroVuRight = false;
		}
	}
	
	private void heroVuLeft(){
		if(monster.getBX()-hero.getBX()<3.5 && monster.getBX()-hero.getBX()>=1.2){
			heroVuLeft = true;
		}else{
			heroVuLeft = false;
		}
	}
	
	
	private boolean HeroTresHB(){
		if((hero.getBY()-monster.getBY()>1 || hero.getBY()-monster.getBY()<-1 ) ){
			if((hero.getBX()-monster.getBX()>1.5 || hero.getBX()-monster.getBX()<-1.5)){
				return true;
			}
				return false;
		}
		return true;
	}
	
	
	
	
}
