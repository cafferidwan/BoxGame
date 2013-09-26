package com.example.boxgame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Objects extends Sprite
{

	public static boolean touchFlag;
	
	int i =0;
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
				break;
			}
			case TouchEvent.ACTION_MOVE: 
			{
				this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, 
						pSceneTouchEvent.getY() - this.getHeight() / 2);
				
				if(pSceneTouchEvent.getX()- this.getWidth()/2 == MainActivity.moi.getX() && 
						pSceneTouchEvent.getY()- this.getHeight()/2 == MainActivity.moi.getY())
				{
					i++;
					if(i==1)
					{
						Functions.audioPlay = true;
						Functions.playAudio(R.raw.moi);
					}
				}
				
				else if(pSceneTouchEvent.getX()- this.getWidth()/2 == MainActivity.ma.getX() && 
						pSceneTouchEvent.getY()- this.getHeight()/2 == MainActivity.ma.getY())
				{
					i++;
					if(i==1)
					{
						Functions.audioPlay = true;
						Functions.playAudio(R.raw.ma);
					}
				}
				else if(pSceneTouchEvent.getX()- this.getWidth()/2 == MainActivity.mula.getX() && 
						pSceneTouchEvent.getY()- this.getHeight()/2 == MainActivity.mula.getY())
				{
					i++;
					if(i==1)
					{
						Functions.audioPlay = true;
						Functions.playAudio(R.raw.mula);
					}
				}
				else if(pSceneTouchEvent.getX()- this.getWidth()/2 == MainActivity.megh.getX() && 
						pSceneTouchEvent.getY()- this.getHeight()/2 == MainActivity.megh.getY())
				{
					i++;
					if(i==1)
					{
						Functions.audioPlay = true;
						Functions.playAudio(R.raw.megh);
					}
				}
				else if(pSceneTouchEvent.getX()- this.getWidth()/2 == MainActivity.mohis.getX() && 
						pSceneTouchEvent.getY()- this.getHeight()/2 == MainActivity.mohis.getY())
				{
					i++;
					if(i==1)
					{
						Functions.audioPlay = true;
						Functions.playAudio(R.raw.mohis);
					}
				}
				break;
			}
			case TouchEvent.ACTION_UP:
			{
				i=0;
				
				touchFlag = false;
				
				break;
			}
		}
		return true;
	}
				
}
