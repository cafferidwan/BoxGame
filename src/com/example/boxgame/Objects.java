package com.example.boxgame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

public class Objects extends Sprite
{

	public static float pSceneTouchEventX, pSceneTouchEventY; 
	public static boolean touchFlag;
	
	public Objects(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager VertexBufferObject) 
	{
		super(pX, pY, pTextureRegion, VertexBufferObject);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) 
	{
		switch (pSceneTouchEvent.getAction())
		{
			case TouchEvent.ACTION_DOWN:
			{
				touchFlag = true;
				//this.setScale(1.5f);
				//Debug.d("pSceneTouchEvent.x:"+pSceneTouchEvent.getX());
				//Debug.d("ball.x:"+MainActivity.ball.getX());
//				if(pSceneTouchEvent.getX() == MainActivity.ball.getX()- MainActivity.ball.getWidth()/2)
//				{
//					Debug.d("Ball touched");
//				}
				break;
			}
			case TouchEvent.ACTION_MOVE: 
			{
				this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, 
						pSceneTouchEvent.getY() - this.getHeight() / 2);
			
				break;
			}
			case TouchEvent.ACTION_UP:
			{
				//this.setScale(1.0f);
				//playAudio2(R.raw.megh);
				
				pSceneTouchEventX = pSceneTouchEvent.getX();
				pSceneTouchEventY = pSceneTouchEvent.getY();
				touchFlag = false;
				
				break;
			}
		}
		return true;
	}
}
