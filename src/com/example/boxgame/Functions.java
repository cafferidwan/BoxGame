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
import org.andengine.util.modifier.ease.EaseCircularInOut;

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
	public static void jump(Sprite a, int k)
	{
		 
        float jumpDuration = (float) 0.7;
        float startX = 0;
        float startY = 0;
        
        float endX = 0;
        float endY = 0;
        
        float midPointX = 0 ;
        float midPointY = 0 ;
        
        //int j =(int)( Math.random()*2);
        
        if(k == 0)
        {
        	startX = MainActivity.closedBox.getX();
        	startY = MainActivity.closedBox.getY();
        	
        	midPointX = 120;
        	midPointY = 45;
        	
        	endX = 50;
        	endY = MainActivity.closedBox.getY();
        	
        	jumpModifier = new SequenceEntityModifier(
                    new MoveYModifier(jumpDuration, startY, midPointY, EaseCircularInOut.getInstance()),
                    new MoveYModifier(jumpDuration, midPointY, endY, EaseBounceOut.getInstance()));
            a.registerEntityModifier(jumpModifier);
            
            jumpModifier1 = new SequenceEntityModifier(
                    new MoveXModifier(jumpDuration, startX, midPointX, EaseCircularInOut.getInstance()),
                    new MoveXModifier(jumpDuration, midPointX, endX, EaseBounceOut.getInstance()));
            a.registerEntityModifier(jumpModifier1);
        }
        else if(k == 1)
        {
        	startX = MainActivity.closedBox.getX()+30;
        	startY = MainActivity.closedBox.getY();
        	
        	midPointX = MainActivity.CAMERA_WIDTH/2+70;
        	midPointY = 45;
        	
        	endX = MainActivity.CAMERA_WIDTH-130;
        	endY = MainActivity.closedBox.getY()+70;
        	
        	jumpModifier = new SequenceEntityModifier(
                    new MoveYModifier(jumpDuration, startY, midPointY, EaseCircularInOut.getInstance()),
                    new MoveYModifier(jumpDuration, midPointY, endY, EaseBounceOut.getInstance()));
            a.registerEntityModifier(jumpModifier);
            
            jumpModifier1 = new SequenceEntityModifier(
                    new MoveXModifier(jumpDuration, startX, midPointX, EaseCircularInOut.getInstance()),
                    new MoveXModifier(jumpDuration, midPointX, endX, EaseBounceOut.getInstance()));
            a.registerEntityModifier(jumpModifier1);
            
        }
        
	}
	
	public static void path(final Sprite a1)
	{
		
		final Path bouncePath = new Path(2).to(a1.getX(), a1.getY()).
				to(MainActivity.closedBox.getX(), MainActivity.closedBox.getY()+20);
		
		a1.registerEntityModifier(new PathModifier((float)1.4, bouncePath,  new IPathModifierListener() 
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
