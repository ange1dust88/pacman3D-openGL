package engineTester;

import java.util.ArrayList;
import java.util.List;

import entities.*;
import guis.GuiTexture;
import guis.guiRenderer;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.*;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		//models
		RawModel model = OBJLoader.loadObjModel("pacman", loader); // player
		RawModel cubeModel = OBJLoader.loadObjModel("cube", loader);
		RawModel enemyModel = OBJLoader.loadObjModel("ghost", loader);
		RawModel fruitModel = OBJLoader.loadObjModel("fruit", loader);
		//textures
		TexturedModel cubeTexturedModel = new TexturedModel(cubeModel, new ModelTexture(loader.loadTexture("wall")));
		TexturedModel fruitTexturedModel = new TexturedModel(fruitModel, new ModelTexture(loader.loadTexture("red")));
		TexturedModel enemyTexturedModel = new TexturedModel(enemyModel, new ModelTexture(loader.loadTexture("blue")));
		TexturedModel enemy1TexturedModel = new TexturedModel(enemyModel, new ModelTexture(loader.loadTexture("pink")));
		TexturedModel enemy2TexturedModel = new TexturedModel(enemyModel, new ModelTexture(loader.loadTexture("red")));

		Labyrinth labyrinth = new Labyrinth(loader, cubeTexturedModel);
		List<Entity> labyrinthCubes = labyrinth.getLabyrinthCubes();
		Fruits fruits = new Fruits(loader, fruitTexturedModel);
		List<Entity> fruitsPos = fruits.getFruits();
		ghost1 ghosts = new ghost1(loader, enemyTexturedModel);
		List<Entity> ghost1 = ghosts.getGhost1();
		ghost2 ghosts1 = new ghost2(loader, enemy1TexturedModel);
		List<Entity> ghost2 = ghosts1.getGhost2();
		ghost3 ghosts2 = new ghost3(loader, enemy2TexturedModel);
		List<Entity> ghost3 = ghosts2.getGhost3();
		TexturedModel pl = new TexturedModel(model, new ModelTexture(loader.loadTexture("pacman")));
		List<Entity> entities = new ArrayList<Entity>();
		//gui
		List<GuiTexture> guis = new ArrayList<GuiTexture>();
		List<GuiTexture> guis2 = new ArrayList<GuiTexture>();
		GuiTexture gui = new GuiTexture(loader.loadTexture("youwin"), new Vector2f(0.25f, 0.25f), new Vector2f(0.5f, 0.5f));
		guis.add(gui);
		GuiTexture gui2 = new GuiTexture(loader.loadTexture("gameover"), new Vector2f(0.25f, 0.25f), new Vector2f(0.5f, 0.5f));
		guis2.add(gui2);
		guiRenderer guiRenderer = new guiRenderer(loader);

		Light light = new Light(new Vector3f(20000, 20000, 1000), new Vector3f(1, 1, 1));
		Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("text")));
		Player player = new Player(pl, new Vector3f(45, 1, 51), 0, 0, 0, 1.5f, labyrinthCubes, fruitsPos, ghost1,ghost2,ghost3);
		Camera camera = new Camera(player);
		MasterRenderer renderer = new MasterRenderer();


		while (!Display.isCloseRequested() ) {
			player.checkInputs();
			player.move();
			camera.move();

			renderer.processEntity(player);
			renderer.processTerrain(terrain);


			for (Entity cube : labyrinthCubes) {
				renderer.processEntity(cube);
			}

			for (Entity fruit : fruitsPos) {
				renderer.processEntity(fruit);
			}
			for (Entity ghost : ghost1) {
				renderer.processEntity(ghost);
				ghosts.moveGhost1();

			}
			for (Entity ghost : ghost2) {
				renderer.processEntity(ghost);
				ghosts1.moveGhost2();

			}
			for (Entity ghost : ghost3) {
				renderer.processEntity(ghost);
				ghosts2.moveGhost3();

			}
			renderer.render(light, camera);
			if (player.playerWins) {
				guiRenderer.render(guis);
				player.setCurrentTurnSpeed(0);
				player.setCurrentSpeed(0);

			} else if(player.gameover){
				guiRenderer.render(guis2);
			}
			DisplayManager.updateDisplay();
		}
			guiRenderer.cleanUP();
			renderer.cleanUp();
			loader.cleanUp();
			DisplayManager.closeDisplay();
		}
	}


//todo: map, fruits( + xp counter(when its full - you won)), enemies(death after contact(you lost)), camera pos change(3 pos togle O, hold 3rd person), design(textures, models, sky color)