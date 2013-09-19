package com.example.boxgame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
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
import org.andengine.util.modifier.IModifier;

import android.media.MediaPlayer;
import android.util.Log;
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
	
	 Sprite backGround, ball, banana, crocodile, jackFruit, keramBoard, mango, pen;
	static Sprite mo;
	static AnimatedSprite  parrot;
	AnimatedSprite box;
	TimerHandler timer1;
	
	@Override
	public EngineOptions onCreateEngineOptions()
	{
		// TODO Auto-generated method stub
		Display display = getWindowManager().getDefaultDisplay();
		CAMERA_HEIGHT = display.getHeight();
		CAMERA_WIDTH = display.getWidth();
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
		mAnimatedBitmapTextureAtlas1 = new BuildableBitmapTextureAtlas(this.getTextureManager(), 200, 52, TextureOptions.NEAREST);
		MainActivity.mParrotTextureRegion = BitmapTextureAtlasTextureRegionFactory.
				createTiledFromAsset(this.mAnimatedBitmapTextureAtlas, this, "parrot1.png", 8, 2);
		MainActivity.mBoxTextureRegion = BitmapTextureAtlasTextureRegionFactory.
				createTiledFromAsset(this.mAnimatedBitmapTextureAtlas1, this, "box.png", 4, 1);
		
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
		timer1 = new TimerHandler(1.0f/120, true, new ITimerCallback()
		{
			@Override
			public void onTimePassed(TimerHandler pTimerHandler)
			{
				// TODO Auto-generated method stub
//				if(ball.collidesWith(box))
//				{
//					box.animate(new long[]{200, 200, 200, 200}, 0, 3, true);
//				}
				if(banana.collidesWith(box))
				{
					box.animate(new long[]{200, 200, 200, 200}, 0, 3, true);
					Debug.d("Colliding here with banana");
				}
//				if(!ball.collidesWith(box))
//				{
//					box.animate(new long[]{200,200}, 1, 2,true);
//				}
				if(!banana.collidesWith(box))
				{
					box.animate(new long[]{200,200}, 1, 2,true);
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
		parrot.animate(new long[]{200, 200, 200, 200, 200, 200}, 0, 5, true);
		parrot.setFlippedHorizontal(true);
		parrot.setWidth(CAMERA_WIDTH/6 + 100);
		parrot.setHeight(CAMERA_HEIGHT/4 + 60);
		mScene.registerTouchArea(parrot);
		mScene.attachChild(parrot);
		
		box = new Box(CAMERA_WIDTH/2, CAMERA_HEIGHT/2, mBoxTextureRegion, getVertexBufferObjectManager());
		box.setWidth(100);
		box.setHeight(100);
		mScene.attachChild(box);		
		
		banana = new Objects(100, CAMERA_HEIGHT-100, mBananaTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(banana);
		mScene.attachChild(banana);
		
		ball = new Objects(200, CAMERA_HEIGHT-100, mBallTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(ball);
		mScene.attachChild(ball);
		
		crocodile = new Objects(300, CAMERA_HEIGHT-100, mCrocodileTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(crocodile);
		mScene.attachChild(crocodile);
		
		jackFruit = new Objects(400, CAMERA_HEIGHT-100, mJackFruitTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(jackFruit);
		mScene.attachChild(jackFruit);
		
		keramBoard = new Objects(500, CAMERA_HEIGHT-100, mKeramBoardTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(keramBoard);
		mScene.attachChild(keramBoard);
		
		mango = new Objects(600, CAMERA_HEIGHT-100, mMangoTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(mango);
		mScene.attachChild(mango);
		
		pen = new Objects(700, CAMERA_HEIGHT-100, mPenTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(pen);
		mScene.attachChild(pen);
		
		mo = new Objects(900, CAMERA_HEIGHT-100, mMoTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(mo);
		mo.setHeight(60);
		mo.setWidth(60);
		mScene.attachChild(mo);
		Parrot.parrotPath();
		
		mScene.registerUpdateHandler(timer1);
		
		return mScene;
	}
	
//	public static void collides(Sprite s)
//	{
//		if(s.collidesWith(box))
//		{
//			box.animate(new long[]{200, 200, 200, 200}, 0, 3, true);
//		}
//		if(!s.collidesWith(box))
//		{
//			box.animate(new long[]{200,200}, 1, 2,true);
//		}
//	}
	
}
