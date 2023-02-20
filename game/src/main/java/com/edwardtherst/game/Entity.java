package com.edwardtherst.game;

public class Entity {
    Float[] Pos = new Float[]{0f, 0f};
	Float[] Speed = new Float[]{0f, 0f};
    Float MaxSpeed = 0.1f;
    Float Acceleration = 0.01f;
    Float JumpPower = 0.2f;
    Float Gravity = -0.01f;
    Float Width = 0.05f;
    Float Height = 0.1f;
    Boolean OnGround = true;
    Integer Id;
    String OpenGui;
    
}
