package entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;

import java.util.ArrayList;
import java.util.List;

public class ghost2 {

    private final List<Entity> ghost2;
    private int movementDirection = 0;

    public ghost2(Loader loader, TexturedModel TexturedModel) {
        ghost2 = placeGhost2(loader, TexturedModel);
    }

    public List<Entity> getGhost2() {
        return ghost2;
    }

    public void moveGhost2() {
        float movementSpeed = 0.2f;

        for (Entity ghost : ghost2) {
            float currentZ = ghost.getPosition().z;

            switch (movementDirection) {
                case 0:
                    ghost.increasePosition(0, 0, -movementSpeed);
                    if (currentZ <= 10f) {
                        movementDirection = 1;
                    }
                    break;
                case 1:
                    ghost.increasePosition(0, 0, movementSpeed);
                    if (currentZ >= 80f) {
                        movementDirection = 0;
                    }
                    break;
            }
        }
    }



    private static List<Entity> placeGhost2(Loader loader, TexturedModel TexturedModel) {
        List<Entity> enemies = new ArrayList<>();
        float enemySize = 5f;
        float terrainHeight = -1;

        Vector3f[] ghost2Positions = {
                new Vector3f(45, terrainHeight + enemySize / 2, 9),
        };

        for (Vector3f position : ghost2Positions) {
            float x = position.x;
            float y = position.y;
            float z = position.z;
            enemies.add(new Entity(TexturedModel, new Vector3f(x, y, z), 0, 0, 0, 0.8f));
        }

        return enemies;
    }
}
