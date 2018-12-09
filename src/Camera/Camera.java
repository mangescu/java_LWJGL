
package Camera;

import org.joml.Vector3f;



public class Camera {
  
	private Vector3f position;
	private float pitch;
	private float yaw;
	private float roll;  
        
        public  Camera(Vector3f position , float pitch , float yaw ,float roll) {
            this.position = position;
            this.pitch = pitch;
            this.yaw = yaw;
            this.roll = roll;
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

    public float getRoll() {
        return roll;
    }

    
}
