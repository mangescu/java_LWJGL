package pkg3d_studio_max;

public class Camera {

    static double distance = 10;
    static double xPos = 0;
    static double yPos = -distance;
    static double zPos = 0;
    private double CxPos = 0;
    private double CyPos = -distance;
    private double CzPos = 0;
    static double zomX = 0;
    static double zomY = -distance;
    static double zomZ = 0;
    private double xLPos = 0;
    private double yLPos = 0;
    private double zLPos = 0;
    private double pitch = 0;
    private double yaw = 0;
    static double x1 = 0;
    static double y1 = 0;
    static double z1 = 1;
    static double x2 = 0;
    static double y2 = 1;
    static double z2 = 0;
    static double index = 0;

    public Camera() {
    }

    public void rotate_left(double unghi) {
        pitch += unghi;


//camera
        x2 = distance * Math.cos(Math.toRadians(pitch));
        y2 = distance * Math.sin(Math.toRadians(-pitch));

//light   
        CxPos = distance * Math.cos(Math.toRadians((90 + pitch)));
        CyPos = distance * Math.sin(Math.toRadians(-(90 + pitch)));


        xPos = CxPos;
        yPos = CyPos;

    }

    public void rotate_right(double unghi) {
        pitch += unghi;

//camera
        x2 = distance * Math.cos(Math.toRadians(pitch));
        y2 = distance * Math.sin(Math.toRadians(-pitch));

//light
        CxPos = distance * Math.cos(Math.toRadians((90 + pitch)));
        CyPos = distance * Math.sin(Math.toRadians(-(90 + pitch)));


        xPos = CxPos;
        yPos = CyPos;

    }

    public void rotate_up(double unghi) {
        yaw -= unghi;

//camera 
        CzPos = distance * Math.cos(Math.toRadians((90 - yaw)));
        CxPos = CxPos * Math.cos(Math.toRadians((yaw)));
        CyPos = CyPos * Math.cos(Math.toRadians((yaw)));



//zom
        zomX = CxPos;
        zomY = CyPos;
        zomZ = CzPos;

        xPos = CxPos;
        yPos = CyPos;
        zPos = CzPos;

    }

    public void rotate_down(double unghi) {
        yaw += unghi;

        //camera
        CzPos = distance * Math.cos(Math.toRadians((90 - yaw)));
        CxPos = CxPos * Math.cos(Math.toRadians((yaw)));
        CyPos = CyPos * Math.cos(Math.toRadians((yaw)));





//zom
        zomX = CxPos;
        zomY = CyPos;
        zomZ = CzPos;


        xPos = CxPos;
        yPos = CyPos;
        zPos = CzPos;

    }

    public void scrol_in(double magnitude) {

        double xMovement = magnitude * CxPos;
        double yMovement = magnitude * CyPos;
        double zMovement = magnitude * CzPos;

        zomX -= xMovement;
        zomY -= yMovement;
        zomZ -= zMovement;

        xPos = zomX;
        yPos = zomY;
        zPos = zomZ;

        CxPos = xPos;
        CyPos = yPos;
        CzPos = zPos;

        distance = Math.sqrt(Math.pow(xPos, 2) + Math.pow(zomY, 2) + Math.pow(zPos, 2));
    }

    public void scrol_out(double magnitude) {

        double xMovement = magnitude * CxPos;
        double yMovement = magnitude * CyPos;
        double zMovement = magnitude * CzPos;

        zomX += xMovement;
        zomY += yMovement;
        zomZ += zMovement;

        xPos = zomX;
        yPos = zomY;
        zPos = zomZ;

        CxPos = xPos;
        CyPos = yPos;
        CzPos = zPos;

        distance = Math.sqrt(Math.pow(xPos, 2) + Math.pow(zomY, 2) + Math.pow(zPos, 2));

    }

    public double getCxPos() {
        return CxPos;
    }

    public void setCxPos(double CxPos) {
        this.CxPos = CxPos;
    }

    public double getCyPos() {
        return CyPos;
    }

    public void setCyPos(double CyPos) {
        this.CyPos = CyPos;
    }

    public double getCzPos() {
        return CzPos;
    }

    public void setCzPos(double CzPos) {
        this.CzPos = CzPos;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public double getxLPos() {
        return xLPos;
    }

    public void setxLPos(double xLPos) {
        this.xLPos = xLPos;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyLPos() {
        return yLPos;
    }

    public void setyLPos(double yLPos) {
        this.yLPos = yLPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public double getzLPos() {
        return zLPos;
    }

    public void setzLPos(double zLPos) {
        this.zLPos = zLPos;
    }

    public double getzPos() {
        return zPos;
    }

    public void setzPos(double zPos) {
        this.zPos = zPos;
    }
}
