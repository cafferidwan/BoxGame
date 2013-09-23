package com.example.boxgame;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.debug.Debug;


public class Functions 
{
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
	
	public static void bouncePath(Sprite a, float x, float y)
	{
		
		
		final Path bouncePath = new Path(2).to(Objects.pSceneTouchEventX, Objects.pSceneTouchEventY)
				.to(x, y);
		
	
		a.registerEntityModifier(new PathModifier((float)0.7, bouncePath,  new IPathModifierListener() 
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
	
	public static void fadeOut(final Sprite a)
	{
		AlphaModifier yourModifier = new AlphaModifier(2f, 1f, 0f)
		{
		        @Override
		        protected void onModifierStarted(IEntity pItem)
		        {
		                super.onModifierStarted(pItem);
		                // Your action after starting modifier
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
}
