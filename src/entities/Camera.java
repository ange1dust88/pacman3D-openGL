package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private boolean oKeyPressed = false;
	private float distanceFromPlayer = 15;
	private float angleAroundPlayer = 0;
	private Vector3f position = new Vector3f(45,160,45);
	private float pitch = 90;
	private float yaw ;
	int pos = 0;
	Player player;

	public Camera(Player player){
		this.player = player;

	}

	public void move() {
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
			if (!oKeyPressed) {
				if (position.equals(new Vector3f(45, 140, 130))) {
					position = new Vector3f(45, 160, 45);
					pitch = 90;
					angleAroundPlayer = 0;
					distanceFromPlayer = 15;
					yaw = 0;
					pos = 0;
				} else {
					position = new Vector3f(45, 140, 130);
					pitch = 60;
					angleAroundPlayer = 0;
					distanceFromPlayer = 15;
					yaw = 0;
					pos = 1;
				}
			}
			oKeyPressed = true;
		} else {
			oKeyPressed = false;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			pitch = 40;
			calculateZoom();
			calculatePitch();
			calculateAngleAroundPlayer();
			float horizontal = calculateHorizontalDistance();
			float vertical = calculateVerticalDistance();
			calculateCameraPosition(horizontal, vertical);
			yaw = 180 - (player.getRotY() + angleAroundPlayer);
		} else {
			if (yaw != 0) {
				if(pos == 0) {
					position = new Vector3f(45, 160, 45);
					pitch = 90;
					angleAroundPlayer = 0;
					distanceFromPlayer = 15;
					yaw = 0;
				} else if ( pos == 1) {
					position = new Vector3f(45, 140, 130);
					pitch = 60;
					angleAroundPlayer = 0;
					distanceFromPlayer = 15;
					yaw = 0;
				}
				else {

				}
			}
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	private void calculateCameraPosition(float horizontalDistance, float verticalDistance){
		float theta = player.getRotY() + angleAroundPlayer;
		float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x - offsetX;
		position.y = player.getPosition().y + verticalDistance;
		position.z = player.getPosition().z - offsetZ;

	}


	private float calculateHorizontalDistance(){
		return (float) (distanceFromPlayer*Math.cos(Math.toRadians(pitch)));
	}

	private float calculateVerticalDistance(){
		return (float) (distanceFromPlayer*Math.sin(Math.toRadians(pitch)));
	}

	private void calculateZoom(){
		float zoomLevel = Mouse.getDWheel() * 0.01f;
		distanceFromPlayer -=zoomLevel;
	}
	public  void calculatePitch(){
		if(Mouse.isButtonDown(1)){
			float pitchChange = Mouse.getDY() * 0.01f;
			pitch -=pitchChange;
		}
	}

	private  void calculateAngleAroundPlayer(){
		if(Mouse.isButtonDown(0)){
			float angleChange = Mouse.getDX()*0.03f;
			angleAroundPlayer -= angleChange;
			player.setCurrentTurnSpeed(angleChange);

		}
	}
	
	

}
