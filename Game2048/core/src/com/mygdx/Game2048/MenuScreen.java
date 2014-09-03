package com.mygdx.Game2048;

import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.mygdx.Game2048.GameScreen;

public class MenuScreen implements Screen {

	final Game2048 game;
	Stage stage;
	Skin skin;
	TextureAtlas atlas;
	Table table;
	TextButton startGame, highScores, about;
	BitmapFont smallFont, largeFont;
	Label logo;
	TextButtonStyle buttonStyle;
	LabelStyle labelStyle;

	public MenuScreen(final Game2048 gam) {
		this.game = gam;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas("main.pack");
		skin = new Skin(atlas);
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		smallFont = new BitmapFont(Gdx.files.internal("fonts/smallFont.fnt"));
		largeFont = new BitmapFont(Gdx.files.internal("fonts/largeFont.fnt"));
		
		smallFont.setColor(.9804f, .9725f, .9373f, 1);
		buttonStyle = new TextButtonStyle();
		labelStyle = new LabelStyle(largeFont , new Color(.4667f, .4314f, .3961f, 1));
		labelStyle.font = largeFont;
		buttonStyle.up = skin.getDrawable("button_normal");
		buttonStyle.down = skin.getDrawable("button_pressed");
		buttonStyle.checked = skin.getDrawable("button_pressed");
		buttonStyle.font = smallFont;
		logo = new Label("2048", labelStyle);
		startGame = new TextButton("Start", buttonStyle);
		highScores = new TextButton("High Scores", buttonStyle);
		about = new TextButton("About", buttonStyle);

		table.row();
		table.add(logo).padBottom(30);
		table.row();
		table.add(startGame);
		table.row();
		table.add(highScores);
		table.row();
		table.add(about);
		table.center();
		table.debug();
		stage.addActor(table);

		startGame.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
			}

		});
		highScores.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
			}

		});
		about.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
			}

		});



	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.9804f, .9725f, .9373f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
     atlas.dispose();
     stage.dispose();
     skin.dispose();
     
	}

}
