package com.example.boxgame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

public class Objects extends Sprite
{

	public static boolean touchFlag, touchFlag1, touchFlag2, touchFlag3, touchFlag4;
	
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
						touchFlag1 = true;
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
						touchFlag1 = true;
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
						touchFlag2 = true;
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
						touchFlag2 = true;
						Functions.audioPlay = true;
						Functions.playAudio(R.raw.mohis);
					}
				}
				else if(pSceneTouchEvent.getX()- this.getWidth()/2 == MainActivity.kola.getX() && 
						pSceneTouchEvent.getY()- this.getHeight()/2 == MainActivity.kola.getY())
				{
					i++;
					if(i==1)
					{
						Debug.d("////////");
						Debug.d("closedBox.getX():"+MainActivity.closedBox.getX());
						Debug.d("kola.getX():"+MainActivity.kola.getX());
						Debug.d("closedBox.getY():"+MainActivity.closedBox.getY());
						Debug.d("kola.getY():"+MainActivity.kola.getY());
					}
				}
				
				break;
			}
			case TouchEvent.ACTION_UP:
			{
				i=0;
				
				touchFlag = false;
				touchFlag1 = false;
				touchFlag2 = false;
				if(pSceneTouchEvent.getX()- this.getWidth()/2 == MainActivity.kola.getX() && 
						pSceneTouchEvent.getY()- this.getHeight()/2 == MainActivity.kola.getY())
				{
					i++;
					if(i==1)
					{
						Debug.d("////////");
						Debug.d("1closedBox.getX():"+MainActivity.closedBox.getX());
						Debug.d("1kola.getX():"+MainActivity.kola.getX());
						Debug.d("1closedBox.getY():"+MainActivity.closedBox.getY());
						Debug.d("1kola.getY():"+MainActivity.kola.getY());
					}
				}
				
				break;
			}
		}
		return true;
	}
				
}
