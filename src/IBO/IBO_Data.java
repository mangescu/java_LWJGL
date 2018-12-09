
package IBO;

import org.joml.Vector3f;


public class IBO_Data {

    float[] vertices;
    int[] indices;
    float[] textureCoords;
  
    Vector3f  pointA ,  pointB ,  pointC ,  pointD;
    int mod;
  public IBO_Data(){
      pointA = new Vector3f();
      pointB = new Vector3f();
      pointC = new Vector3f();
      pointD = new Vector3f();
  }             
  public IBO_Data(Vector3f pozition , float width , float height , int mod){
    this.mod = mod;
    if(mod == 0) {
        initData_quad(pozition , width , height);
    }
    if(mod == 1) {
        initData_triangle(pozition , width , height);
    }

  }
  public void setupVectors(Vector3f  pointA , Vector3f  pointB , Vector3f  pointC , Vector3f  pointD){
      this.pointA.x = pointA.x;
      this.pointA.y = pointA.y;
      this.pointA.z = pointA.z;
      
      this.pointB.x = pointB.x;
      this.pointB.y = pointB.y;
      this.pointB.z = pointB.z;    
      
      this.pointC.x = pointC.x;
      this.pointC.y = pointC.y;
      this.pointC.z = pointC.z;  

      this.pointD.x = pointD.x;
      this.pointD.y = pointD.y;
      this.pointD.z = pointD.z;    
      
      initDinamicQuadData(this.pointA.x,this.pointA.y , this.pointB.x,this.pointB.y , this.pointC.x,this.pointC.y , this.pointD.x,this.pointD.y); 
  }
  public void initDinamicQuadData(float x1,float y1,float x2,float y2,float x3,float y3,float x4,float y4)  {
  
       float[] vertices = {
              x1,  y1 , 1,
              x2, y2  ,1,
              x3, y3 , 1,
              x4,  y4 ,1
      };
      int[] indices = {
              0,1,3,2
      };
      float[] textureCoords = {
              0,0,
              0,1,
              1,1,
              1,0
      };

      this.vertices = vertices;
      this.indices = indices;
      this.textureCoords = textureCoords; 
  
  }
  public void initData_quad(Vector3f pozition , float width , float height) {

      float[] vertices = {
              -width+pozition.x,  height+pozition.y, pozition.z,
              -width+pozition.x, -height+pozition.y, pozition.z,
              width+pozition.x, -height+pozition.y, pozition.z,
              width+pozition.x,  height+pozition.y, pozition.z
      };
      int[] indices = {
              0,1,3 , 2
      };
      float[] textureCoords = {
              0,0,
              0,1,
              1,1,
              1,0
      };

      this.vertices = vertices;
      this.indices = indices;
      this.textureCoords = textureCoords;

  }
  public void initData_triangle(Vector3f pozition , float width , float height) {

      float[] vertices = {
              -width+pozition.x,  height+pozition.y, pozition.z,
              pozition.x, -height+pozition.y, pozition.z,
              pozition.x, -height+pozition.y, pozition.z,

      };
      int[] indices = {
              0,1,2
      };
      float[] textureCoords = {
              0,0,
              0,1,
              1,1,
              1,0
      };



      this.vertices = vertices;
      this.indices = indices;
      this.textureCoords = textureCoords;

  }
              

    public float[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public float[] getTextureCoords() {
        return textureCoords;
    }
    
    
}
