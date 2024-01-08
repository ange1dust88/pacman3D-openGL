
        package entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;

import java.util.ArrayList;
import java.util.List;

public class Fruits {

    private final List<Entity> fruitsPos;

    public Fruits(Loader loader, TexturedModel TexturedModel) {
        fruitsPos = fillLabyrinth(loader, TexturedModel);
    }

    public List<Entity> getFruits() {
        return fruitsPos;
    }

    private static List<Entity> fillLabyrinth(Loader loader, TexturedModel TexturedModel) {
        List<Entity> fruits = new ArrayList<>();
        float fruitSize = 5f;
        float terrainHeight = -1;


        Vector3f[] fruitPositions = {
                new Vector3f(9, terrainHeight + fruitSize / 2, 9),
                new Vector3f(15, terrainHeight + fruitSize / 2, 9),
                new Vector3f(21, terrainHeight + fruitSize / 2, 9),
                new Vector3f(27, terrainHeight + fruitSize / 2, 9),
                new Vector3f(33, terrainHeight + fruitSize / 2, 9),
                new Vector3f(39, terrainHeight + fruitSize / 2, 9),
                new Vector3f(45, terrainHeight + fruitSize / 2, 9),
                new Vector3f(51, terrainHeight + fruitSize / 2, 9),
                new Vector3f(57, terrainHeight + fruitSize / 2, 9),
                new Vector3f(63, terrainHeight + fruitSize / 2, 9),
                new Vector3f(69, terrainHeight + fruitSize / 2, 9),

                new Vector3f(21, terrainHeight + fruitSize / 2, 15),
                new Vector3f(27, terrainHeight + fruitSize / 2, 15),
                new Vector3f(33, terrainHeight + fruitSize / 2, 15),
                new Vector3f(45, terrainHeight + fruitSize / 2, 15),
                new Vector3f(57, terrainHeight + fruitSize / 2, 15),
                new Vector3f(63, terrainHeight + fruitSize / 2, 15),
                new Vector3f(69, terrainHeight + fruitSize / 2, 15),
                new Vector3f(75, terrainHeight + fruitSize / 2, 15),
                new Vector3f(81, terrainHeight + fruitSize / 2, 15),

                new Vector3f(9, terrainHeight + fruitSize / 2, 21),
                new Vector3f(15, terrainHeight + fruitSize / 2, 27),
                new Vector3f(21, terrainHeight + fruitSize / 2, 21),
                new Vector3f(45, terrainHeight + fruitSize / 2, 21),
                new Vector3f(81, terrainHeight + fruitSize / 2, 21),
                new Vector3f(39, terrainHeight + fruitSize / 2, 27),

                new Vector3f(9, terrainHeight + fruitSize / 2, 27),
                new Vector3f(21, terrainHeight + fruitSize / 2, 27),
                new Vector3f(27, terrainHeight + fruitSize / 2, 27),
                new Vector3f(33, terrainHeight + fruitSize / 2, 27),
                new Vector3f(45, terrainHeight + fruitSize / 2, 27),
                new Vector3f(51, terrainHeight + fruitSize / 2, 27),
                new Vector3f(57, terrainHeight + fruitSize / 2, 27),
                new Vector3f(63, terrainHeight + fruitSize / 2, 27),
                new Vector3f(69, terrainHeight + fruitSize / 2, 27),
                new Vector3f(75, terrainHeight + fruitSize / 2, 27),
                new Vector3f(81, terrainHeight + fruitSize / 2, 27),

                new Vector3f(9, terrainHeight + fruitSize / 2, 33),
                new Vector3f(33, terrainHeight + fruitSize / 2, 33),
                new Vector3f(45, terrainHeight + fruitSize / 2, 33),
                new Vector3f(69, terrainHeight + fruitSize / 2, 33),
                new Vector3f(81, terrainHeight + fruitSize / 2, 33),


                new Vector3f(9, terrainHeight + fruitSize / 2, 39),
                new Vector3f(15, terrainHeight + fruitSize / 2, 39),
                new Vector3f(21, terrainHeight + fruitSize / 2, 39),
                new Vector3f(33, terrainHeight + fruitSize / 2, 39),
                new Vector3f(45, terrainHeight + fruitSize / 2, 39),
                new Vector3f(57, terrainHeight + fruitSize / 2, 39),
                new Vector3f(69, terrainHeight + fruitSize / 2, 39),
                new Vector3f(81, terrainHeight + fruitSize / 2, 39),

                new Vector3f(9, terrainHeight + fruitSize / 2, 45),
                new Vector3f(15, terrainHeight + fruitSize / 2, 45),
                new Vector3f(21, terrainHeight + fruitSize / 2, 45),
                new Vector3f(33, terrainHeight + fruitSize / 2, 45),
                new Vector3f(45, terrainHeight + fruitSize / 2, 45),
                new Vector3f(57, terrainHeight + fruitSize / 2, 45),
                new Vector3f(63, terrainHeight + fruitSize / 2, 45),
                new Vector3f(69, terrainHeight + fruitSize / 2, 45),
                new Vector3f(81, terrainHeight + fruitSize / 2, 45),

                new Vector3f(9, terrainHeight + fruitSize / 2, 51),
                new Vector3f(21, terrainHeight + fruitSize / 2, 51),
                new Vector3f(27, terrainHeight + fruitSize / 2, 51),
                new Vector3f(33, terrainHeight + fruitSize / 2, 51),
                new Vector3f(39, terrainHeight + fruitSize / 2, 51),
                new Vector3f(51, terrainHeight + fruitSize / 2, 51),
                new Vector3f(57, terrainHeight + fruitSize / 2, 51),
                new Vector3f(81, terrainHeight + fruitSize / 2, 51),

                new Vector3f(9, terrainHeight + fruitSize / 2, 57),
                new Vector3f(33, terrainHeight + fruitSize / 2, 57),
                new Vector3f(45, terrainHeight + fruitSize / 2, 57),
                new Vector3f(57, terrainHeight + fruitSize / 2, 57),
                new Vector3f(63, terrainHeight + fruitSize / 2, 57),
                new Vector3f(69, terrainHeight + fruitSize / 2, 57),
                new Vector3f(75, terrainHeight + fruitSize / 2, 57),
                new Vector3f(81, terrainHeight + fruitSize / 2, 57),

                new Vector3f(9, terrainHeight + fruitSize / 2, 63),
                new Vector3f(15, terrainHeight + fruitSize / 2, 63),
                new Vector3f(21, terrainHeight + fruitSize / 2, 63),
                new Vector3f(33, terrainHeight + fruitSize / 2, 63),
                new Vector3f(45, terrainHeight + fruitSize / 2, 63),
                new Vector3f(81, terrainHeight + fruitSize / 2, 63),

                new Vector3f(9, terrainHeight + fruitSize / 2, 69),
                new Vector3f(21, terrainHeight + fruitSize / 2, 69),
                new Vector3f(33, terrainHeight + fruitSize / 2, 69),
                new Vector3f(45, terrainHeight + fruitSize / 2, 69),
                new Vector3f(51, terrainHeight + fruitSize / 2, 69),
                new Vector3f(57, terrainHeight + fruitSize / 2, 69),
                new Vector3f(63, terrainHeight + fruitSize / 2, 69),
                new Vector3f(69, terrainHeight + fruitSize / 2, 69),
                new Vector3f(81, terrainHeight + fruitSize / 2, 69),

                new Vector3f(9, terrainHeight + fruitSize / 2, 75),
                new Vector3f(21, terrainHeight + fruitSize / 2, 75),
                new Vector3f(33, terrainHeight + fruitSize / 2, 75),
                new Vector3f(45, terrainHeight + fruitSize / 2, 75),
                new Vector3f(69, terrainHeight + fruitSize / 2, 75),
                new Vector3f(81, terrainHeight + fruitSize / 2, 75),
                new Vector3f(9, terrainHeight + fruitSize / 2, 15),
                new Vector3f(81, terrainHeight + fruitSize / 2, 9),
                new Vector3f(75, terrainHeight + fruitSize / 2, 9),
                new Vector3f(9, terrainHeight + fruitSize / 2, 81),
                new Vector3f(15, terrainHeight + fruitSize / 2, 81),
                new Vector3f(21, terrainHeight + fruitSize / 2, 81),
                new Vector3f(27, terrainHeight + fruitSize / 2, 81),
                new Vector3f(33, terrainHeight + fruitSize / 2, 81),
                new Vector3f(39, terrainHeight + fruitSize / 2, 81),
                new Vector3f(45, terrainHeight + fruitSize / 2, 81),
                new Vector3f(51, terrainHeight + fruitSize / 2, 81),
                new Vector3f(57, terrainHeight + fruitSize / 2, 81),
                new Vector3f(63, terrainHeight + fruitSize / 2, 81),
                new Vector3f(69, terrainHeight + fruitSize / 2, 81),
                new Vector3f(75, terrainHeight + fruitSize / 2, 81),
                new Vector3f(81, terrainHeight + fruitSize / 2, 81),
        };



        for (Vector3f position : fruitPositions) {
            float x = position.x;
            float y = position.y;
            float z = position.z;
            fruits.add(new Entity(TexturedModel, new Vector3f(x, y, z), 0, 0, 0, 0.5f));
        }


        return fruits;
    }
}