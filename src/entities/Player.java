package entities;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;

import java.util.List;

public class Player extends Entity {
    public static float RUN_SPEED = 9;
    public static final float TURN_SPEED = 100;
    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private int fruitsCollected = 0;
    private List<Entity> labyrinthCubes;
    private List<Entity> fruitsPos;
    private List<Entity> ghost1;
    private List<Entity> ghost2;
    private List<Entity> ghost3;
    private final int totalFruits = 112;//112
    public boolean playerWins = false;
    public boolean gameover = false;
    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, List<Entity> labyrinthCubes, List<Entity> fruitsPos,List<Entity> ghost1,List<Entity> ghost2,List<Entity> ghost3) {
        super(model, position, rotX, rotY, rotZ, scale);
        this.labyrinthCubes = labyrinthCubes;
        this.fruitsPos = fruitsPos;
        this.ghost1 = ghost1;
        this.ghost2 = ghost2;
        this.ghost3 = ghost3;
    }

    public void move() {

            checkInputs();
            float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
            float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
            float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
            handleFruitCollision(dx, 0, dz);
            handleGhostCollision(dx, 0, dz);

            if (!checkCollisionWithWalls(dx, 0, dz)) {
                super.increasePosition(dx, 0, dz);
            } else {
                handleWallCollision(dx, 0, dz);
            }

            super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        if(gameover) {
            super.setScale(0);
        }
    }

    public void checkInputs() {
        if(!playerWins) {
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                this.currentSpeed = RUN_SPEED;
            } else {
                this.currentSpeed = 0;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                this.currentTurnSpeed = TURN_SPEED;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                this.currentTurnSpeed = -TURN_SPEED;
            } else {
                this.currentTurnSpeed = 0;
            }
        }

    }

    public void setCurrentTurnSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
    public void setCurrentSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
    public int getFruitsCollected() {
        return fruitsCollected;
    }
    //collision
    public boolean checkCollision(Entity otherEntity) {
        Vector3f thisMin = super.getPosition() != null ? new Vector3f(super.getPosition().x - super.getScale(), super.getPosition().y - super.getScale(), super.getPosition().z - super.getScale()) : new Vector3f();
        Vector3f thisMax = super.getPosition() != null ? new Vector3f(super.getPosition().x + super.getScale(), super.getPosition().y + super.getScale(), super.getPosition().z + super.getScale()) : new Vector3f();

        Vector3f otherMin = otherEntity.getPosition() != null ? new Vector3f(otherEntity.getPosition().x - otherEntity.getScale(), otherEntity.getPosition().y - otherEntity.getScale(), otherEntity.getPosition().z - otherEntity.getScale()) : new Vector3f();
        Vector3f otherMax = otherEntity.getPosition() != null ? new Vector3f(otherEntity.getPosition().x + otherEntity.getScale(), otherEntity.getPosition().y + otherEntity.getScale(), otherEntity.getPosition().z + otherEntity.getScale()) : new Vector3f();

        return thisMin.x < otherMax.x && thisMax.x > otherMin.x &&
                thisMin.y < otherMax.y && thisMax.y > otherMin.y &&
                thisMin.z < otherMax.z && thisMax.z > otherMin.z;
    }
    //walls
    private void handleWallCollision(float dx, float dy, float dz) {
        boolean collisionX = checkCollisionWithWalls(dx, 0, 0);
        boolean collisionZ = checkCollisionWithWalls(0, 0, dz);

        handleWallCollisionDirection(collisionX, dx, 0, 0);
        handleWallCollisionDirection(collisionZ, 0, 0, dz);
    }

    private void handleWallCollisionDirection(boolean collision, float dx, float dy, float dz) {
        if (collision && currentSpeed > 0) {
            float pushBackDistance = 0.01f;

            float pushBackX = (dx != 0) ? Math.signum(dx) * pushBackDistance : 0;
            float pushBackZ = (dz != 0) ? Math.signum(dz) * pushBackDistance : 0;

            Vector3f newPosition = new Vector3f(super.getPosition());
            newPosition.x -= pushBackX;
            newPosition.z -= pushBackZ;

            if (!checkCollisionWithWalls(pushBackX, dy, pushBackZ)) {
                super.setPosition(newPosition);
                currentSpeed = 0;
            }
        } else {

            super.increasePosition(dx, dy, dz);
        }
    }

    private boolean checkCollisionWithWalls(float dx, float dy, float dz) {
        Vector3f newPosition = new Vector3f(super.getPosition());
        newPosition.x += dx;
        newPosition.y += dy;
        newPosition.z += dz;

        for (Entity wall : labyrinthCubes) {
            if (checkCollision(wall, newPosition)) {
                return true;
            }
        }

        return false;
    }

    private boolean checkCollision(Entity otherEntity, Vector3f newPosition) {
        Vector3f thisMin = new Vector3f(newPosition.x - super.getScale(), newPosition.y - super.getScale(), newPosition.z - super.getScale());
        Vector3f thisMax = new Vector3f(newPosition.x + super.getScale(), newPosition.y + super.getScale(), newPosition.z + super.getScale());

        Vector3f otherMin = new Vector3f(otherEntity.getPosition().x - otherEntity.getScale(),
                otherEntity.getPosition().y - otherEntity.getScale(), otherEntity.getPosition().z - otherEntity.getScale());
        Vector3f otherMax = new Vector3f(otherEntity.getPosition().x + otherEntity.getScale(),
                otherEntity.getPosition().y + otherEntity.getScale(), otherEntity.getPosition().z + otherEntity.getScale());

        return thisMin.x < otherMax.x && thisMax.x > otherMin.x &&
                thisMin.y < otherMax.y && thisMax.y > otherMin.y &&
                thisMin.z < otherMax.z && thisMax.z > otherMin.z;
    }
    //fruits
    private boolean checkCollisionWithFruit(float dx, float dy, float dz) {
        Vector3f newPosition = super.getPosition() != null ? new Vector3f(super.getPosition()) : new Vector3f();
        newPosition.x += dx;
        newPosition.y += dy;
        newPosition.z += dz;

        for (Entity fruit : fruitsPos) {
            if (checkCollision(fruit)) {
                return true;
            }
        }

        return false;
    }
    private void handleFruitCollision(float dx, float dy, float dz) {
        if (fruitsPos == null || playerWins) {
            return;
        }

        boolean collisionWithFruit = checkCollisionWithFruit(dx, 0, dz);

        if (collisionWithFruit) {
            fruitsPos.removeIf(this::checkCollision);
            fruitsCollected++;
            System.out.println("Fruits collected: " + fruitsCollected);

            if (fruitsCollected >= totalFruits) {
                playerWins = true;
            }
        }
    }

    //enemies
    private boolean checkCollisionWithGhost(float dx, float dy, float dz) {
        Vector3f newPosition = super.getPosition() != null ? new Vector3f(super.getPosition()) : new Vector3f();
        newPosition.x += dx;
        newPosition.y += dy;
        newPosition.z += dz;

        for (Entity ghost : ghost1) {
            if (checkCollision(ghost)) {
                return true;
            }
        }
        for (Entity ghost : ghost2) {
            if (checkCollision(ghost)) {
                return true;
            }
        }
        for (Entity ghost : ghost3) {
            if (checkCollision(ghost)) {
                return true;
            }
        }

        return false;
    }

    private void handleGhostCollision(float dx, float dy, float dz) {
        boolean collisionWithGhost = checkCollisionWithGhost(dx, 0, dz);

        if (collisionWithGhost) {
            gameover = true;
        }
    }


}
