

package com.mangy.client;


public class BulletData {  
    
    	public String  owner;
	public float   X;
	public float   Y;
	public float   Z;
	public float   angle;
        public String  direction;
	
	public BulletData(String  owner , float   X , float   Y , float   Z , float   angle , String direction) {
		this.owner = owner;
		this.X    = X;
		this.Y    = Y;
		this.Z    = Z;
		this.angle= angle; 
                this.direction = direction;
	}
    
    
}
