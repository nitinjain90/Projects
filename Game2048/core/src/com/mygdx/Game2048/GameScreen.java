package com.mygdx.Game2048;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameScreen implements Screen {

	private TextureRegion temp;
	private TextureRegion gameBoard;
	private Stage stage;
	private TextureRegion tile;
	private GameBoard board;
	private static final int boardWidth = 576;
	private static final int boardHeight = 576;
	private static final int tileWidth = 139;
	private static final int tileHeight = 139;
	private static final int spacing = 4;
	private final static int ROWS = 4;
	private final static int COLS = 4;
	private static final int INITIAL_TILES = 2;
	private Tile[][] gameTiles;
	boolean dead;
    
	
	public GameScreen(final Game2048 gam) {
		
		
		gameBoard = new TextureRegion();
		stage = new Stage();
		board = new GameBoard(gameBoard, Gdx.graphics.getWidth() / 2
				- boardWidth / 2, 200);
		stage.addActor(board);
		gameTiles = new Tile[ROWS][COLS];
		for (int i = 0; i < INITIAL_TILES; i++) {
			spawnRandom();
		}
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (gameTiles[i][j] == null) {
					gameTiles[i][j] = new Tile(0,
							(Gdx.graphics.getWidth() / 2 - boardWidth / 2)
									+ (j) * 139 + (j + 1) * spacing, 200 + (i)
									* 139 + (i + 1) * spacing, tile);
					stage.addActor(gameTiles[i][j]);

				}
			}
		}
		      Gdx.input.setInputProcessor(new InputAdapter() {

				@Override
				public boolean touchDragged(int screenX, int screenY,
						int pointer) {
					System.out.println("dragg performed");
					moveTiles(Direction.RIGHT);
					return true;
					
				}
		    	  
		      });
        
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.9804f, .9725f, .9373f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();

	}

	public int getTileX(int col) {
		return (Gdx.graphics.getWidth() / 2 - boardWidth / 2) + (col + 1)
				* spacing + col * tileWidth;
	}

	public int getTileY(int row) {
		return 200 + (row + 1) * spacing + row * tileHeight;
	}

	private void spawnRandom() {
		Random random = new Random();
		boolean notValid = true;

		while (notValid) {
			int location = random.nextInt(ROWS * COLS);
			int row = location / ROWS;
			int col = location / COLS;
			Tile current = gameTiles[row][col];
			if (current == null) {
				int value = random.nextInt(10) < 9 ? 2 : 4;
				Tile tile = new Tile(value, getTileX(col), getTileY(row), temp);
				gameTiles[row][col] = tile;
				notValid = false;
				stage.addActor(tile);

			}
		}
	}

	private boolean move(int row, int col, int horizontalDirection,
			int verticalDirection, Direction dir) {
		boolean canMove = false;
		Tile current = gameTiles[row][col];
		if (current.getValue() == 0)
			return false;
		boolean move = true;
		int newCol = col;
		int newRow = row;
		while (move) {
			newCol = newCol + horizontalDirection;
			newRow = newRow + verticalDirection;
			if (checkOutOfBounds(dir, newRow, newCol))
				break;
			if (gameTiles[newRow][newCol].getValue() == 0) {
				current = new Tile(current.getValue(), getTileX(newCol), getTileY(newRow), temp);
				stage.addActor(current);
				gameTiles[newRow - verticalDirection][newCol
						- horizontalDirection] = new Tile(0, getTileX(newCol
						- horizontalDirection), getTileY(newRow
						- verticalDirection), temp);
				stage.addActor(gameTiles[newRow - verticalDirection][newCol
						- horizontalDirection]);
				canMove = true;
			} else if (gameTiles[newRow][newCol].getValue() == current
					.getValue() && gameTiles[newRow][newCol].CanCombine()) {
				gameTiles[newRow][newCol].setCanCombine(false);
				gameTiles[newRow][newCol] = new Tile(
						gameTiles[newRow][newCol].getValue() * 2,
						getTileX(newCol), getTileY(newRow), temp);
				stage.addActor(gameTiles[newRow][newCol]);
				canMove = true;
				gameTiles[newRow - verticalDirection][newCol
						- horizontalDirection] = new Tile(0, getTileX(newCol
						- horizontalDirection), getTileY(newRow
						- verticalDirection), temp);
				stage.addActor(gameTiles[newRow - verticalDirection][newCol
						- horizontalDirection]);
			} else {
				move = false;
			}
		}

		return canMove;
	}

	private boolean checkOutOfBounds(Direction dir, int row, int col) {
		if (dir == Direction.LEFT) {
			return col < 0;
		} else if (dir == Direction.RIGHT) {
			return col > COLS - 1;
		} else if (dir == Direction.UP) {
			return row < 0;
		}
		if (dir == Direction.DOWN) {
			return row > ROWS - 1;
		}
		return false;
	}

	private void moveTiles(Direction dir) {
		boolean canMove = false;
		int horizontalDirection = 0;
		int verticalDirection = 0;
		if (dir == Direction.LEFT) {
			horizontalDirection = -1;
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove) {
						canMove = move(row, col, horizontalDirection,
								verticalDirection, dir);
					} else
						move(row, col, horizontalDirection, verticalDirection,
								dir);
				}
			}
		} else if (dir == Direction.RIGHT) {
			horizontalDirection = 1;
			for (int row = 0; row < ROWS; row++) {
				for (int col = COLS - 1; col >= 0; col--) {
					if (!canMove) {
						canMove = move(row, col, horizontalDirection,
								verticalDirection, dir);
					} else
						move(row, col, horizontalDirection, verticalDirection,
								dir);
				}
			}
		} else if (dir == Direction.UP) {
			verticalDirection = -1;
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove) {
						canMove = move(row, col, horizontalDirection,
								verticalDirection, dir);
					} else
						move(row, col, horizontalDirection, verticalDirection,
								dir);
				}
			}
		} else if (dir == Direction.DOWN) {
			verticalDirection = 1;
			for (int row = ROWS - 1; row >= 0; row--) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove) {
						canMove = move(row, col, horizontalDirection,
								verticalDirection, dir);
					} else
						move(row, col, horizontalDirection, verticalDirection,
								dir);
				}
			}
		} else {
              System.out.print("Wrong Direction");
		}
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				Tile current = gameTiles[row][col];
				if (current.getValue() == 0)
					continue;
				current.setCanCombine(true);
			}
		}
		if (canMove) {
			spawnRandom();
			checkDead();
		}
	}

	private void checkDead() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (gameTiles[row][col].getValue() == 0)
					return;
				if (checkSurroundingTiles(row, col, gameTiles[row][col])) {
					return;
				}
			}
		}
		dead = true;
	}

	private boolean checkSurroundingTiles(int row, int col, Tile current) {
		if (row > 0) {
			Tile check = gameTiles[row - 1][col];
			if (check.getValue() == 0)
				return true;
			if (current.getValue() == check.getValue())
				return true;
		}
		if (row < ROWS - 1) {
			Tile check = gameTiles[row + 1][col];
			if (check.getValue() == 0)
				return true;
			if (current.getValue() == check.getValue())
				return true;
		}
		if (col > 0) {
			Tile check = gameTiles[row][col - 1];
			if (check.getValue() == 0)
				return true;
			if (current.getValue() == check.getValue())
				return true;
		}
		if (col < col - 1) {
			Tile check = gameTiles[row][col + 1];
			if (check.getValue() == 0)
				return true;
			if (current.getValue() == check.getValue())
				return true;
		}
		return false;
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
		stage.dispose();

	}



}
