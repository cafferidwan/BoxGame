package com.example.boxgame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
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
	public static ITextureRegion mMulaTextureRegion;
	public static ITextureRegion mKolaTextureRegion;
	public static ITextureRegion mMaTextureRegion;
	
	public static ITextureRegion mMohisTextureRegion;
	public static ITextureRegion mbackGroundTextureRegion;
	public static ITextureRegion mKeramBoardTextureRegion;
	public static ITextureRegion mMeghTextureRegion;
	public static ITextureRegion mMoiTextureRegion;
	public static ITextureRegion mMoTextureRegion;
	
	public static ITextureRegion mBox1TextureRegion;
	public static ITextureRegion mBox2TextureRegion;
	
	private BuildableBitmapTextureAtlas mAnimatedBitmapTextureAtlas;
	private BuildableBitmapTextureAtlas mAnimatedBitmapTextureAtlas1;
	public static TiledTextureRegion mParrotTextureRegion;
	public static TiledTextureRegion mBoxTextureRegion;
	
	public static Sprite backGround, mula, kola, ma, mohis, keramBoard, megh, moi;
	static Sprite mo;
	static AnimatedSprite  parrot;
	public TimerHandler timer1, timer2;
	
	float mulaX , mulaY, kolaX, kolaY, maX, maY, mohisX, 
		  mohisY, keramBoardX, keramBoardY, meghX, meghY;
	static float moiX;
	static float moiY;
	static Sprite openedBox, closedBox;
	
	@Override
	public EngineOptions onCreateEngineOptions()
	{
		// TODO Auto-generated method stub
		Display display = getWindowManager().getDefaultDisplay();
		CAMERA_HEIGHT = display.getHeight();
		CAMERA_WIDTH = display.getWidth();
		
		mulaX = 0;
		mulaY = MainActivity.CAMERA_HEIGHT-130;
		
		kolaX = 50;
		kolaY = MainActivity.CAMERA_HEIGHT-130;
		
		maX = 100;
		maY = MainActivity.CAMERA_HEIGHT-130;
		
		mohisX = 600;
		mohisY = MainActivity.CAMERA_HEIGHT-130;
		
		keramBoardX = 500;
		keramBoardY = MainActivity.CAMERA_HEIGHT-130;
		
		meghX = 550;
		meghY = MainActivity.CAMERA_HEIGHT-130;
		
		moiX = 660;
		moiY = MainActivity.CAMERA_HEIGHT-130;
		
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
		MainActivity.mMulaTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "mula-2.png");
		MainActivity.mKolaTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "kola-1.png");
		
		MainActivity.mMaTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "ma-2.png");
		MainActivity.mMohisTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "mohis-2.png");
		MainActivity.mKeramBoardTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "keramBoard1.png");
		MainActivity.mMeghTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "megh-2.png");
		MainActivity.mMoiTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "moi-2.png");
		MainActivity.mMoTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "mo.png");
		
		MainActivity.mBox1TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "box-15.png");
		MainActivity.mBox2TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "box-16.png");
		
		mAnimatedBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 900, 228, TextureOptions.NEAREST);
		mAnimatedBitmapTextureAtlas1 = new BuildableBitmapTextureAtlas(this.getTextureManager(), 600, 153, TextureOptions.NEAREST);
		MainActivity.mParrotTextureRegion = BitmapTextureAtlasTextureRegionFactory.
				createTiledFromAsset(this.mAnimatedBitmapTextureAtlas, this, "parrot1.png", 8, 2);
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
				
				//Kola
				if(Functions.collisionCheck(closedBox , kola)== true)
				{
					MainActivity.openedBox.setVisible(true);
					MainActivity.closedBox.setVisible(false);
					
					if(Objects.touchFlag == false)
					{
						Functions.bouncePath(kola, kolaX, kolaY);
					}
				}
				
				//Keram board
				else if(Functions.collisionCheck(closedBox , keramBoard)== true)
				{
					MainActivity.openedBox.setVisible(true);
					MainActivity.closedBox.setVisible(false);
					
					if(Objects.touchFlag == false)
					{
						Functions.bouncePath(keramBoard, keramBoardX, keramBoardY);
					}
				}
				
				//Mohis
				else if(Functions.collisionCheck(closedBox , mohis)== true)
				{
					MainActivity.openedBox.setVisible(true);
					MainActivity.closedBox.setVisible(false);
					
					if(Objects.touchFlag == false)
					{
						Functions.fadeOut(mohis);
					}
				}
				
				
				//Megh
				else if(Functions.collisionCheck(closedBox , megh)== true)
				{
					MainActivity.openedBox.setVisible(true);
					MainActivity.closedBox.setVisible(false);
					
					if(Objects.touchFlag == false)
					{
						Functions.fadeOut(megh);
					}
				}
				
				//Mula
				else if(Functions.collisionCheck(closedBox , mula)== true)
				{
					MainActivity.openedBox.setVisible(true);
					MainActivity.closedBox.setVisible(false);
					
					if(Objects.touchFlag == false)
					{
						Functions.fadeOut(mula);
					}
				}
				
				//Moi
				else if(Functions.collisionCheck(closedBox , moi)== true)
				{
					MainActivity.openedBox.setVisible(true);
					MainActivity.closedBox.setVisible(false);
					
					if(Objects.touchFlag == false)
					{
						Functions.fadeOut(moi);
					}
				}
				
				//Ma
				else if(Functions.collisionCheck(closedBox , ma)== true)
				{
					MainActivity.openedBox.setVisible(true);
					MainActivity.closedBox.setVisible(false);
					
					if(Objects.touchFlag == false)
					{
						Functions.fadeOut(ma);
					}
				}
				
				else
				{
					MainActivity.openedBox.setVisible(false);
					MainActivity.closedBox.setVisible(true);
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
		
		//opened box
		openedBox = new Sprite(CAMERA_WIDTH/2-100, 160, mBox1TextureRegion, getVertexBufferObjectManager());
		mScene.attachChild(openedBox);
		openedBox.setVisible(false);
		
		//closed box
		closedBox = new Sprite(CAMERA_WIDTH/2-100, 160, mBox2TextureRegion, getVertexBufferObjectManager());
		mScene.attachChild(closedBox);
		closedBox.setVisible(true);
		
		parrot = new Parrot(MainActivity.CAMERA_WIDTH+500, MainActivity.CAMERA_HEIGHT/2-50, mParrotTextureRegion, this.getVertexBufferObjectManager());
		parrot.animate(new long[]{200, 200, 200, 200, 200, 200, 200, 200}, 0, 7, true);
		parrot.setFlippedHorizontal(true);
		parrot.setWidth(CAMERA_WIDTH/6 + 80);
		parrot.setHeight(CAMERA_HEIGHT/4 + 60);
		mScene.registerTouchArea(parrot);
		mScene.attachChild(parrot);
		
		
		kola = new Objects(kolaX, kolaY, mKolaTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(kola);
		mScene.attachChild(kola);
		
		mula = new Objects(mulaX, mulaY, mMulaTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(mula);
		mScene.attachChild(mula);
		
		ma = new Objects(maX, maY, mMaTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(ma);
		mScene.attachChild(ma);
		
		mohis = new Objects(mohisX, mohisY, mMohisTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(mohis);
		mScene.attachChild(mohis);
		
		keramBoard = new Objects(keramBoardX, keramBoardY, mKeramBoardTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(keramBoard);
		mScene.attachChild(keramBoard);
		
		megh = new Objects(meghX, meghY, mMeghTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(megh);
		mScene.attachChild(megh);
		
		moi = new Objects(moiX, moiY, mMoiTextureRegion, getVertexBufferObjectManager());
		mScene.registerTouchArea(moi);
		mScene.attachChild(moi);
		
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
