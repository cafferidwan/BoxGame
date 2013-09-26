package com.example.boxgame;

import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
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
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import android.content.Context;
import android.hardware.SensorManager;
import android.view.Display;

public class MainActivity extends SimpleBaseGameActivity 
{

	static int CAMERA_WIDTH;
	static int CAMERA_HEIGHT;

	public Camera mCamera;
	public static Scene mScene;
	static Context context;
	
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
	
	static float mulaX , mulaY, kolaX, kolaY, maX, maY, mohisX, 
		  mohisY, keramBoardX, keramBoardY, meghX, meghY, moiX, moiY;
	static Sprite openedBox, closedBox;
	
	@Override
	public EngineOptions onCreateEngineOptions()
	{
		// TODO Auto-generated method stub
		Display display = getWindowManager().getDefaultDisplay();
		CAMERA_HEIGHT = display.getHeight();
		CAMERA_WIDTH = display.getWidth();
		
		mulaX = CAMERA_WIDTH/2 - CAMERA_WIDTH/4;
		mulaY = CAMERA_HEIGHT-CAMERA_HEIGHT*(4/3);
		
		kolaX = 50;
		kolaY = CAMERA_HEIGHT-CAMERA_HEIGHT/4;
		
		maX = 50;
		maY = CAMERA_HEIGHT-CAMERA_HEIGHT*(4/3);
		
		mohisX = CAMERA_WIDTH - 100;
		mohisY = CAMERA_HEIGHT-CAMERA_HEIGHT*(4/3);
		
		keramBoardX = 500;
		keramBoardY = CAMERA_HEIGHT-130;
		
		meghX = CAMERA_WIDTH - 100;
		meghY = CAMERA_HEIGHT-CAMERA_HEIGHT/4;
		
		moiX = CAMERA_WIDTH/2;
		moiY = CAMERA_HEIGHT-CAMERA_HEIGHT*(4/3);
		
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
					int i = 0;
					i++;
					if(i==1)
					{

						MainActivity.openedBox.setVisible(true);
						MainActivity.closedBox.setVisible(false);
					
						if(Objects.touchFlag == false)
						{
							// Create jump
							Functions.jump(kola, 0);
						}
					}
				}
				
				//Keram board
				else if(Functions.collisionCheck(closedBox , keramBoard)== true)
				{
					MainActivity.openedBox.setVisible(true);
					MainActivity.closedBox.setVisible(false);
					
					if(Objects.touchFlag == false)
					{
						// Create jump
						Functions.jump(keramBoard, 1);
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
		
		//opened box-y:160
		openedBox = new Sprite(CAMERA_WIDTH/2-100, CAMERA_HEIGHT/2, mBox1TextureRegion, getVertexBufferObjectManager());
		mScene.attachChild(openedBox);
		openedBox.setVisible(false);
		
		//closed box
		closedBox = new Sprite(CAMERA_WIDTH/2-100, CAMERA_HEIGHT/2, mBox2TextureRegion, getVertexBufferObjectManager());
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
		
		mo = new Sprite(900, CAMERA_HEIGHT-100, mMoTextureRegion, getVertexBufferObjectManager())
		{
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY)
			{
				switch (pSceneTouchEvent.getAction()) 
				{
					case TouchEvent.ACTION_DOWN:
					
						Functions.audioPlay = true;
						Functions.playAudio(R.raw.mo);
						
					break;
				}

				return true;
			}
		};
		mo.setHeight(60);
		mo.setWidth(60);
		mScene.attachChild(mo);
		Parrot.parrotPath();
		
		mScene.registerUpdateHandler(timer1);
		
		//getting the context
		MainActivity.context = getApplicationContext();
		
		return mScene;
	}
	
}
