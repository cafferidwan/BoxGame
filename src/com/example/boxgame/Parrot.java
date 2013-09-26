package com.example.boxgame;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;


public class Parrot extends AnimatedSprite
{

	public Parrot(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager vertexBufferObjectManager)
	{
		super(pX, pY, pTiledTextureRegion, vertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY)
	{
		switch (pSceneTouchEvent.getAction()) 
		{
			case TouchEvent.ACTION_DOWN:
			
				Functions.audioPlay = true;
				Functions.playAudio(R.raw.mo);
				
			break;
			
			case TouchEvent.ACTION_UP:
				
			break;
		
			case TouchEvent.ACTION_MOVE:
				
			break;
		}

		return true;
	}

	static void parrotPath() 
	{
		MoveModifier mMod = new MoveModifier(4.0f, MainActivity.CAMERA_WIDTH + 200, MainActivity.CAMERA_WIDTH - 250, 
				MainActivity.CAMERA_HEIGHT / 2 -30, MainActivity.CAMERA_HEIGHT / 2 - 140);
		
		MoveModifier mModLetter = new MoveModifier(4.0f, MainActivity.CAMERA_WIDTH + 200,MainActivity.CAMERA_WIDTH - 210,
				MainActivity.CAMERA_HEIGHT / 2 + 100, MainActivity.CAMERA_HEIGHT / 2 - 10);
		
		DelayModifier dMod = new DelayModifier((float) 0.2,new IEntityModifierListener()
		{

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) 
					{
						Functions.audioPlay = true;
						Functions.playAudio(R.raw.parrot);
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1)
					{
						//playAudio(R.raw.parrot_introducing_mo);
						//mLetter.setVisible(false);
						
					}
				});
		
		SequenceEntityModifier parrot_sm = new SequenceEntityModifier(mMod,dMod);
		SequenceEntityModifier mLetter_sm = new SequenceEntityModifier(mModLetter,dMod);
		//RotationModifier rm1 = new RotationModifier(1.4f, 0.0f, 1.3f);
		//RotationModifier rm2 = new RotationModifier(1.4f, 1.3f, 0.0f);
		
		MainActivity.parrot.registerEntityModifier(parrot_sm);
		MainActivity.mo.registerEntityModifier(mLetter_sm);
		
//		SequenceEntityModifier mLetter_sm2 = new SequenceEntityModifier(rm1,rm2);
//		LoopEntityModifier Lpm = new LoopEntityModifier(mLetter_sm2);
//		MainActivity.mo.registerEntityModifier(Lpm);
		
	}
}
