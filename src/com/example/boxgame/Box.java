package com.example.boxgame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Box extends AnimatedSprite
{

	public Box(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager vertexBufferObjetManager) 
	{
		super(pX, pY, pTiledTextureRegion,vertexBufferObjetManager);
		// TODO Auto-generated constructor stub
	}

}
