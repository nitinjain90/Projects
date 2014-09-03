package com.mygdx.Game2048;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameBoard extends Actor{
        
	    private TextureRegion b;
     	private float x;
     	private float y;

	public GameBoard(TextureRegion board , float x , float y){
		this.x =x;
		this.y =y;
		board = new TextureRegion(new Texture(Gdx.files.internal("big_board.png")));
		this.b = board;
		
	    
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(b, x, y);
	}   
}
