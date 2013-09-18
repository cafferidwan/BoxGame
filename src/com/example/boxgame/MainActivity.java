package com.example.boxgame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Display;

public class MainActivity extends SimpleBaseGameActivity 
{

	int CAMERA_WIDTH;
	int CAMERA_HEIGHT;
	int START_APPEARING_DELAY = 13;
	static float APPEARING_TIME = 2.0f;
	Boolean audioPlay = false;

	public Camera mCamera;
	public Scene mScene;
	MediaPlayer mediaPlayer = new MediaPlayer();
	
	private BuildableBitmapTextureAtlas mBitmapTextureAtlas;
	public static ITextureRegion mBallTextureRegion;
	public static ITextureRegion mBananaTextureRegion;
	public static ITextureRegion mBoxTextureRegion;
	public static ITextureRegion mCrocodileTextureRegion;
	
	public static ITextureRegion mJackFruitTextureRegion;
	public static ITextureRegion mbackGroundTextureRegion;
	public static ITextureRegion mKeramBoardTextureRegion;
	public static ITextureRegion mMangoTextureRegion;
	public static ITextureRegion mPenTextureRegion;
	
	Sprite backGround, ball, banana, box, crocodile, jackFruit, keramBoard, mango, pen;
	
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
		MainActivity.mBoxTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "Box1.png");
		
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
	}

	@Override
	protected Scene onCreateScene()
	{
		// TODO Auto-generated method stub
		mScene = new Scene();
		mScene.setBackground(new Background(Color.WHITE));
		mScene.setTouchAreaBindingOnActionDownEnabled(true);
		
		backGround = new Sprite(0, 0, mbackGroundTextureRegion, this.getVertexBufferObjectManager());
		backGround.setHeight(CAMERA_HEIGHT);
		backGround.setWidth(CAMERA_WIDTH);
		mScene.attachChild(backGround);
		
		box = new Sprite(CAMERA_WIDTH/2, CAMERA_HEIGHT/2, this.mBoxTextureRegion, this.getVertexBufferObjectManager());
		mScene.attachChild(box);		
		
		banana = new Objects(100, CAMERA_HEIGHT-100, this.mBananaTextureRegion, this.getVertexBufferObjectManager());
		mScene.registerTouchArea(banana);
		mScene.attachChild(banana);
		
		ball = new Objects(200, CAMERA_HEIGHT-100, this.mBallTextureRegion, this.getVertexBufferObjectManager());
		mScene.registerTouchArea(ball);
		mScene.attachChild(ball);
		
		crocodile = new Objects(300, CAMERA_HEIGHT-100, this.mCrocodileTextureRegion, this.getVertexBufferObjectManager());
		mScene.registerTouchArea(crocodile);
		mScene.attachChild(crocodile);
		
		jackFruit = new Objects(400, CAMERA_HEIGHT-100, this.mJackFruitTextureRegion, this.getVertexBufferObjectManager());
		mScene.registerTouchArea(jackFruit);
		mScene.attachChild(jackFruit);
		
		keramBoard = new Objects(500, CAMERA_HEIGHT-100, this.mKeramBoardTextureRegion, this.getVertexBufferObjectManager());
		mScene.registerTouchArea(keramBoard);
		mScene.attachChild(keramBoard);
		
		mango = new Objects(600, CAMERA_HEIGHT-100, this.mMangoTextureRegion, this.getVertexBufferObjectManager());
		mScene.registerTouchArea(mango);
		mScene.attachChild(mango);
		
		pen = new Objects(700, CAMERA_HEIGHT-100, this.mPenTextureRegion, this.getVertexBufferObjectManager());
		mScene.registerTouchArea(pen);
		mScene.attachChild(pen);
		
		return mScene;
	}

}
