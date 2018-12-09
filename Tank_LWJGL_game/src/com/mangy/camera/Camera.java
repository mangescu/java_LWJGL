

package com.mangy.camera;


public class Camera {

	float xPos = 5f;
	float yPos = 0f;
	float zPos = 10;

	float xLPos = 0f;
	float yLPos = 0f;
	float zLPos = 0f;

	public float pitch = 0f;
	public float yaw = 0f;

	public Camera() {
	}

	public Camera(float xPos, float yPos, float zPos, float xLPos, float yLPos,float zLPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;

		this.xLPos = xLPos;
		this.yLPos = yLPos;
		this.zLPos = zLPos;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public void updatePosition(float xPos, float yPos, float zPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
	}

	public void lookPosition(float xLPos, float yLPos, float zLPos) {
		this.xLPos = xLPos;
		this.yLPos = yLPos;
		this.zLPos = zLPos;
	}

	public void mouse_look(float magnitude) {
		float xCurrent = this.xPos;
		float yCurrent = this.yPos;
		float zCurrent = this.zPos;

		float xMovement = (float) (magnitude * Math.cos(Math.toRadians(pitch)));
		float yMovement = (float) (magnitude * Math.sin((pitch)));
		float zMovement = (float) (magnitude * Math.cos(Math.toRadians(yaw)));

		float xNew = xCurrent + xMovement;
		float yNew = yCurrent + yMovement;
		float zNew = zCurrent + zMovement;

		updatePosition(xNew, yNew, zNew);
	}

	public void moveForward(float magnitude) {
		float xCurrent = this.xPos;
		float yCurrent = this.yPos;
		float zCurrent = this.zPos;

		float xMovement = (float) (magnitude * Math.cos(Math.toRadians(pitch)));
		float yMovement = (float) (magnitude * Math.sin(Math.toRadians(pitch)));

		float xNew = xCurrent + xMovement;
		float yNew = yCurrent + yMovement;
		float zNew = zCurrent;

		updatePosition(xNew, yNew, zNew);
	}

	public void moveLateral(float magnitude) {
		float xCurrent = this.xPos;
		float yCurrent = this.yPos;
		float zCurrent = this.zPos;

		float xMovement = (float) (magnitude * Math.sin(-Math.toRadians(pitch)));
		float yMovement = (float) (magnitude * Math.cos(-Math.toRadians(pitch)));

		float xNew = xCurrent + xMovement;
		float yNew = yCurrent + yMovement;
		float zNew = zCurrent;

		updatePosition(xNew, yNew, zNew);

	}

	public void moveUp(float magnitude) {
		float xCurrent = this.xPos;
		float yCurrent = this.yPos;
		float zCurrent = this.zPos;

		float zMovement = magnitude;
		float zNew = (float) (zCurrent + 0.05);

		updatePosition(xCurrent, yCurrent, zNew);
	}

	public void moveDown(float magnitude) {
		float xCurrent = this.xPos;
		float yCurrent = this.yPos;
		float zCurrent = this.zPos;

		float zMovement = magnitude;
		float zNew = (float) (zCurrent - 0.05);

		updatePosition(xCurrent, yCurrent, zNew);
	}

	public void look(double distanceAway) {
		float xLCurrent = this.xPos;
		float yLCurrent = this.yPos;
		float zLCurrent = this.zPos;

		float xMovement = (float) (10 * Math.cos(Math.toRadians(pitch)));
		float yMovement = (float) (10 * Math.sin(Math.toRadians(pitch)));
		float zMovement = (float) (10 * Math.cos(Math.toRadians(yaw)));

		float xNew = xLCurrent + xMovement;
		float yNew = yLCurrent + yMovement;
		float zNew = zLCurrent + zMovement;

		lookPosition(xNew, yNew, zNew);
	}

	public float getXPos() {
		return xPos;
	}

	public float getYPos() {
		return yPos;
	}

	public float getZPos() {
		return zPos;
	}

	public float getXLPos() {
		return xLPos;
	}

	public float getYLPos() {
		return yLPos;
	}

	public float getZLPos() {
		return zLPos;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void pitchUp(float amount) {
		this.pitch += amount;
	}

	public void pitchDown(float amount) {
		this.pitch -= amount;
	}

	public void yawRight(float amount) {
		this.yaw += amount;
	}

	public void yawLeft(float amount) {
		this.yaw -= amount;
	}
}
