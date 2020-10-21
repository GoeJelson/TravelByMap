package edu.lewisu.josephknelson;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TravelByMap extends ApplicationAdapter {
	SpriteBatch batch;
	Texture map, mapD;
	OrthographicCamera cam;
	int WIDTH;
	int HEIGHT;
	int imgX;
	int imgY;
	int zoomLevel;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		map = new Texture("image.jpg");
		mapD = new Texture("zoom.png");
		zoomLevel = 0;
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(WIDTH,HEIGHT);
		cam.translate(WIDTH/2,HEIGHT/2);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.isKeyPressed(Keys.W)) {
			cam.translate(0,5);
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			cam.translate(0,-5);
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
			cam.translate(-5,0);
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			cam.translate(5,0);
		}
		if(Gdx.input.isKeyJustPressed(Keys.MINUS)) {
			cam.zoom = cam.zoom + 0.1f;
			zoomLevel--;
		}
		if(Gdx.input.isKeyJustPressed(Keys.EQUALS)) {
			cam.zoom = cam.zoom - 0.1f;
			zoomLevel++;
		}
		if(Gdx.input.isKeyPressed(Keys.Q)) {
			cam.rotate(1f);
		}
		if(Gdx.input.isKeyPressed(Keys.E)) {
			cam.rotate(-1f);
		}
		if(zoomLevel >= 5) {
			batch.begin();
			batch.draw(mapD, 0, 0);
			batch.end();
		} else if(zoomLevel < 5) {
			batch.begin();
			batch.draw(map, 0, 0);
			batch.end();
		} else {
			batch.begin();
			batch.draw(map, 0, 0);
			batch.end();
		}
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		map.dispose();
	}
}
