package entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;

import java.util.ArrayList;
import java.util.List;

public class ghost3 {

    private final List<Entity> ghost3;
    private int movementDirection = 0;

    public ghost3(Loader loader, TexturedModel TexturedModel) {
        ghost3 = placeGhost3(loader, TexturedModel);
    }

    public List<Entity> getGhost3() {
        return ghost3;
    }

    public void moveGhost3() {
        float movementSpeed = 0.3f;

        for (Entity ghost : ghost3) {
            float currentX = ghost.getPosition().x;
            float currentZ = ghost.getPosition().z;

            switch (movementDirection) {
                case 0:
                    ghost.increasePosition(movementSpeed, 0, 0);
                    if (currentX >= 80f) {
                        movementDirection = 1;
                    }
                    break;
                case 1:
                    ghost.increasePosition(-movementSpeed, 0, 0);  // Изменено на движение влево
                    if (currentX <= 10f) {
                        movementDirection = 0;
                    }
                    break;
            }
        }
    }



    private static List<Entity> placeGhost3(Loader loader, TexturedModel TexturedModel) {
        List<Entity> enemies = new ArrayList<>();
        float enemySize = 5f;
        float terrainHeight = -1;

        Vector3f[] ghost3Positions = {
                new Vector3f(15, terrainHeight + enemySize / 2, 27),
        };

        for (Vector3f position : ghost3Positions) {
            float x = position.x;
            float y = position.y;
            float z = position.z;
            enemies.add(new Entity(TexturedModel, new Vector3f(x, y, z), 0, 0, 0, 0.8f));
        }

        return enemies;
    }
}
