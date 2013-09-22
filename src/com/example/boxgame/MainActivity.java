package com.example.boxgame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;
import android.media.MediaPlayer;
import android.view.Display;

public class MainActivity extends SimpleBaseGameActivity 
{

	static int CAMERA_WIDTH;
	static int CAMERA_HEIGHT;
	int START_APPEARING_DELAY = 13;
	static float APPEARING_TIME = 2.0f;
	Boolean audioPlay = false;

	public Camera mCamera;
	public Scene mScene;
	MediaPlayer mediaPlayer = new MediaPlayer();
	
	private BuildableBitmapTextureAtlas mBitmapTextureAtlas;
	public static ITextureRegion mBallTextureRegion;
	public static ITextureRegion mBananaTextureRegion;
	public static ITextureRegion mCrocodileTextureRegion;
	
	public static ITextureRegion mJackFruitTextureRegion;
	public static ITextureRegion mbackGroundTextureRegion;
	public static ITextureRegion mKeramBoardTextureRegion;
	public static ITextureRegion mMangoTextureRegion;
	public static ITextureRegion mPenTextureRegion;
	public static ITextureRegion mMoTextureRegion;
	
	private BuildableBitmapTextureAtlas mAnimatedBitmapTextureAtlas;
	private BuildableBitmapTextureAtlas mAnimatedBitmapTextureAtlas1;
	public static TiledTextureRegion mParrotTextureRegion;
	public static TiledTextureRegion mBoxTextureRegion;
	
	public static Sprite backGround, ball, banana, crocodile, jackFruit, keramBoard, mango, pen;
	static Sprite mo;
	static AnimatedSprite  parrot;
	static AnimatedSprite box;
	public TimerHandler timer1, timer2;
	
	float ballX , ballY, bananaX, bananaY, crocodileX, crocodileY, jackFruitX, 
		  jackFruitY, keramBoardX, keramBoardY, mangoX, mangoY,penX, penY;
	
	
	@Override
	public EngineOptions onCreateEngineOptions()
	{
		// TODO Auto-generated method stub
		Display display = getWindowManager().getDefaultDisplay();
		CAMERA_HEIGHT = display.getHeight();
		CAMERA_WIDTH = display.getWidth();
		
		ballX = 10;
		ballY = MainActivity.CAMERA_HEIGHT-100;
		
		bananaX = 110;
		bananaY = MainActivity.CAMERA_HEIGHT-100;
		
		crocodileX = 220;
		crocodileY = MainActivity.CAMERA_HEIGHT-100;
		
		jackFruitX = 330;
		jackFruitY = MainActivity.CAMERA_HEIGHT-100;
		
		keramBoardX = 440;
		keramBoardY = MainActivity.CAMERA_HEIGHT-100;
		
		mangoX = 550;
		mangoY = MainActivity.CAMERA_HEIGHT-100;
		
		penX = 660;
		penY = MainActivity.CAMERA_HEIGHT-100;
		
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
	
	}

	@Override
	protected void onCreateResources() 
	{
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(
				this.getTextureManager(), 1600, 1200);

		MainActivity.mbackGroundTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "JungleBG.png");
		MainActivity.mBallTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "Ball1.png");
		MainActivity.mBananaTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "Banana1.png");
		
		MainActivity.mCrocodileTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "Crocodile1.png");
		MainActivity.mJackFruitTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "JackFruit1.png");
		MainActivity.mKeramBoardTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "keramBoard1.png");
		MainActivity.mMangoTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "Mango1.png");
		MainActivity.mPenTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "Pen1.png");
		MainActivity.mMoTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "mo.png");
		
		mAnimatedBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 900, 228, TextureOptions.NEAREST);
		mAnimatedBitmapTextureAtlas1 = new BuildableBitmapTextureAtlas(this.getTextureManager(), 400, 102, TextureOptions.NEAREST);
		MainActivity.mParrotTextureRegion = BitmapTextureAtlasTextureRegionFactory.
				createTiledFromAsset(this.mAnimatedBitmapTextureAtlas, this, "parrot1.png", 8, 2);
		MainActivity.mBoxTextureRegion = BitmapTextureAtlasTextureRegionFactory.
				createTiledFromAsset(this.mAnimatedBitmapTextureAtlas1, this, "box-2.png", 4, 1);
		
		try 
		{
			this.mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
					BitmapTextureAtlas>(0, 0, 0));
			this.mBitmapTextureAtlas.load();
		} 
		catch (TextureAtlasBuilderException e)
		{
			Debug.e(e);
		}
		
		try {
			this.mAnimatedBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			this.mAnimatedBitmapTextureAtlas.load();
		} 
		catch (TextureAtlasBuilderException e) 
		{
			Debug.e(e);
		}
		
		try {
			this.mAnimatedBitmapTextureAtlas1.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			this.mAnimatedBitmapTextureAtlas1.load();
		} 
		catch (TextureAtlasBuilderException e) 
		{
			Debug.e(e);
		}
		
		//timer1 for checking if the any object collides with the box
		timer1 = new TimerHandler(1.0f/120, true, new ITimerCallback()
		{
			@Override
			public void onTimePassed(TimerHandler pTimerHandler)
			{
				// TODO Auto-generated method stub
				
				if(ball.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 2, 3, true);
					Functions.bouncePath(ball, ballX, ballY);
				}
				else if(banana.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 2, 3, true);
					Functions.bouncePath(banana, bananaX, bananaY);
				}
				else if(crocodile.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 2, 3, true);
					crocodile.setVisible(false);
					Functions.bouncePath(crocodile, crocodileX, crocodileY+200);
				}
				else if(jackFruit.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 2, 3, true);
					Functions.bouncePath(jackFruit, jackFruitX, jackFruitY);
				}
				else if(keramBoard.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 2, 3, true);
					Functions.bouncePath(keramBoard, keramBoardX, keramBoardY);
				}
				else if(mango.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 2, 3, true);
					Functions.bouncePath(mango, mangoX, mangoY);
				}
				else if(pen.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 2, 3, true);
					Functions.bouncePath(pen, penX, penY);
				}
				
				
				else if(!ball.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 0, 1,true);
				}
				else if(!banana.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 0, 1,true);
				}
				else if(!crocodile.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 0, 1,true);
				}
				else if(!jackFruit.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 0, 1,true);
				}
				else if(!keramBoard.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 0, 1,true);
				}
				else if(!mango.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 0, 1,true);
				}
				else if(!pen.collidesWith(box))
				{
					box.animate(new long[]{5, 5}, 0, 1,true);
				}
			}
		});
		
	}

	@Override
	protected Scene onCreateScene()
	{
		// TODO Auto-generated method stub
		mScene = new Scene();
		mScene.setBackground(new Background(Color.WHITE));
		mScene.setTouchAreaBindingOnActionDownEnabled(true);
		
		backGround = new Sprite(0, 0, mbackGroundTextureRegion, getVertexBufferObjectManager());
		backGround.setHeight(CAMERA_HEIGHT);
		backGround.setWidth(CAMERA_WIDTH);
		mScene.attachChild(backGround);
		
		parrot = new Parrot(MainActivity.CAMERA_WIDTH+500, MainActivity.CAMERA_HEIGHT/2-50, mParrotTextureRegion, this.getVertexBufferObjectManager());
		parrot.animate(new long[]{200, 200, 200, 200, 200, 200, 200, 200}, 0, 7, true);
		parrot.setFlippedHorizontal(true);
		parrot.setWidth(CAMERA_WIDTH/6 + 80);
		parrot.setHeight(CAMERA_HEIGHT/4 + 60);
		mScene.registerTouchArea(parrot);
		mScene.attachChild(parrot);
		
		box = new AnimatedSprite(CAMERA_WIDTH/2-40, CAMERA_HEIGHT/2-90, mBoxTextureRegion, getVertexBufferObjectManager());
		mScene.attachChild(box);		
		
		banana = new Objects(bananaX, bananaY, mBananaTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(banana);
		mScene.attachChild(banana);
		
		ball = new Objects(ballX, ballY, mBallTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(ball);
		mScene.attachChild(ball);
		
		crocodile = new Objects(crocodileX, crocodileY, mCrocodileTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(crocodile);
		mScene.attachChild(crocodile);
		
		jackFruit = new Objects(jackFruitX, jackFruitY, mJackFruitTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(jackFruit);
		mScene.attachChild(jackFruit);
		
		keramBoard = new Objects(keramBoardX, keramBoardY, mKeramBoardTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(keramBoard);
		mScene.attachChild(keramBoard);
		
		mango = new Objects(mangoX, mangoY, mMangoTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(mango);
		mScene.attachChild(mango);
		
		pen = new Objects(penX, penY, mPenTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(pen);
		mScene.attachChild(pen);
		
		mo = new Objects(900, CAMERA_HEIGHT-100, mMoTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(mo);
		mo.setHeight(60);
		mo.setWidth(60);
		mScene.attachChild(mo);
		Parrot.parrotPath();
		
		mScene.registerUpdateHandler(timer1);
		//mScene.setOnAreaTouchListener(this);
		
		return mScene;
	}
//
//	@Override
//	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX,
//			float pTouchAreaLocalY) 
//	{
//		// TODO Auto-generated method stub
//
//		if(pSceneTouchEvent.isActionDown())
//		{
//			if(pSceneTouchEvent.getX() == ball.getX()-ball.getWidth()/2)
//			{
//				Debug.d("Ball touched");
//			}
//		}
//		return false;
//	}


	public void playAudio(int val)
	{
		if(audioPlay)
		{
			if(!mediaPlayer.isPlaying())
			{
				mediaPlayer.reset();
				mediaPlayer = MediaPlayer.create(getApplicationContext(), val);
				
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
}
