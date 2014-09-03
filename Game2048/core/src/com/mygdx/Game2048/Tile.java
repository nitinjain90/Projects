package com.mygdx.Game2048;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Tile extends Actor {

	private TextureRegion tile;
    private int value;
	private int x;
	private int y;
    
    private boolean canCombine = true;
    
	public Tile(int value, int x, int y, TextureRegion tileImage) {
     	this.x = x;
		this.y = y;
		this.value = value;
		
		tile = setImage(tileImage, value);
		

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(tile, x, y);
	}

	private TextureRegion setImage(TextureRegion Image, int v) {
		if (v == 0) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("blank_tile_large.png")));
		}
		else if (v == 2) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("2_large.png")));
		}
		else if (v == 4) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("4_large.png")));
		}
		else if (v == 8) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("8_large.png")));
		}
		else if (v == 16) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("16_large.png")));
		}
		else if (v == 32) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("32_large.png")));
		}
		else if (v == 64) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("64_large.png")));
		}
		else if (v == 128) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("128_large.png")));
		}
		else if (v == 256) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("256_large.png")));
		}
		else if (v == 512) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("512_large.png")));
		}
		else if (v == 1024) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("1024_large.png")));
		}
		else if (v == 2048) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("2048_large.png")));
		}
		else if (v == 4096) {
			Image = new TextureRegion(new Texture(
					Gdx.files.internal("4096_large.png")));
		} 
		return Image;
	}
	
	public int getValue(){
		return value;
	}
    public void setValue(int v){
    	this.value = v;
    	//drawImage
    }
	public boolean CanCombine() {
		return canCombine;
	}

	public void setCanCombine(boolean canCombine) {
		this.canCombine = canCombine;
	}

	
	
}
  