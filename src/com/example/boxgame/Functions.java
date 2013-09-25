package com.example.boxgame;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.debug.Debug;
import org.andengine.util.modifier.ease.EaseBounceOut;
import org.andengine.util.modifier.ease.EaseCircularOut;

import android.content.Context;
import android.media.MediaPlayer;

public class Functions 
{
	static Boolean audioPlay = false;
	static MediaPlayer mediaPlayer = new MediaPlayer();
	
	public static SequenceEntityModifier jumpModifier, jumpModifier1;
	
	//a is the Box and b is the other Object
	public static boolean collisionCheck(Sprite a, Sprite b)
	{
		
		if(b.collidesWith(a))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	public static void jump(Sprite a)
	{
		 
        final float jumpDuration = (float) 1.0;
        //final int jumpHeight = 50;             
        final float startY = MainActivity.closedBox.getY();
        final float peakY = 70;
        
        final float startX = a.getX();
        final float endX = MainActivity.CAMERA_WIDTH-30;
        final float peakX = MainActivity.CAMERA_WIDTH/2+30;
        
        float midPointX = MainActivity.closedBox.getX()- a.getX()/2;
        float midPointY = 45;
        
        
        jumpModifier = new SequenceEntityModifier(
                new MoveYModifier(jumpDuration, startY, midPointY, EaseCircularOut.getInstance()),
                new MoveYModifier(jumpDuration, peakY, startY, EaseBounceOut.getInstance()));
        a.registerEntityModifier(jumpModifier);
        
        jumpModifier1 = new SequenceEntityModifier(
                new MoveXModifier(jumpDuration, startX, peakX, EaseCircularOut.getInstance()),
                new MoveXModifier(jumpDuration, peakX, endX, EaseBounceOut.getInstance()));
        a.registerEntityModifier(jumpModifier1);
	}
	
	public static void bouncePath(Sprite a, float x, float y)
	{
		
		final Path bouncePath = new Path(5).to(Objects.pSceneTouchEventX, Objects.pSceneTouchEventY).
				to(260,70).to(200, 80).to(170, 70).to(x, y);
		
		a.registerEntityModifier(new PathModifier((float)2.1, bouncePath,  new IPathModifierListener() 
		{
			@Override
			public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) 
			{
				Debug.d("onPathStarted");
			}

			@Override
			public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex)
			{
				Debug.d("onPathWaypointStarted:  " + pWaypointIndex);
		
			}

			@Override
			public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) 
			{
				Debug.d("onPathWaypointFinished: " + pWaypointIndex);
			}

			@Override
			public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) 
			{
				
			}
		}));
	}
	
	//FadeOut Function
	public static void fadeOut(final Sprite a)
	{
		AlphaModifier yourModifier = new AlphaModifier(2f, 1f, 0f)
		{
		        @Override
		        protected void onModifierStarted(IEntity pItem)
		        {
		                super.onModifierStarted(pItem);
		                // Your action after starting modifier
		                MainActivity.mScene.unregisterTouchArea(a);
		        }
		       
		        @Override
		        protected void onModifierFinished(IEntity pItem)
		        {
		                super.onModifierFinished(pItem);
		                // Your action after finishing modifier
						a.setY(MainActivity.CAMERA_HEIGHT+200);
		        }
		};
		 
		a.registerEntityModifier(yourModifier);
	}
	
	//Audio play Function
	public static void playAudio(int val)
	{
		if(audioPlay)
		{
			if(!mediaPlayer.isPlaying())
			{
				mediaPlayer.reset();
				mediaPlayer = MediaPlayer.create(getAppContext(), val);
				
				try 
				{
					mediaPlayer.start();
					mediaPlayer.setLooping(false);
				} 
				catch (IllegalStateException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			audioPlay = true;
		}
	}

	//getting the MainActivity context
	private static Context getAppContext() 
	{
		// TODO Auto-generated method stub
		return MainActivity.context;
	}
}
