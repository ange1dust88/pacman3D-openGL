package entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;

import java.util.ArrayList;
import java.util.List;

public class ghost1 {

    private final List<Entity> ghost1;
    private int movementDirection = 0;

    public ghost1(Loader loader, TexturedModel TexturedModel) {
        ghost1 = placeGhost1(loader, TexturedModel);
    }

    public List<Entity> getGhost1() {
        return ghost1;
    }

    public void moveGhost1() {
        float movementSpeed = 0.3f;

        for (Entity ghost : ghost1) {
            float currentX = ghost.getPosition().x;
            float currentZ = ghost.getPosition().z;

            switch (movementDirection) {
                case 0:
                    ghost.increasePosition(movementSpeed, 0, 0);
                    if (currentX >= 81) {
                        movementDirection = 1;
                    }
                    break;
                case 1:
                    ghost.increasePosition(0, 0, movementSpeed);
                    if (currentZ >= 81f) {
                        movementDirection = 2;
                    }
                    break;
                case 2:
                    ghost.increasePosition(-movementSpeed, 0, 0);
                    if (currentX <= 9f) {
                        movementDirection = 3;
                    }
                    break;
                case 3:
                    ghost.increasePosition(0, 0, -movementSpeed);
                    if (currentZ <= 9f) {
                        movementDirection = 0;
                    }
                    break;
            }
        }
    }



    private static List<Entity> placeGhost1(Loader loader, TexturedModel TexturedModel) {
        List<Entity> enemies = new ArrayList<>();
        float enemySize = 5f;
        float terrainHeight = -1;

        Vector3f[] ghost1Positions = {
                new Vector3f(33, terrainHeight + enemySize / 2, 9),
        };

        for (Vector3f position : ghost1Positions) {
            float x = position.x;
            float y = position.y;
            float z = position.z;
            enemies.add(new Entity(TexturedModel, new Vector3f(x, y, z), 0, 0, 0, 0.8f));
        }

        return enemies;
    }
}
