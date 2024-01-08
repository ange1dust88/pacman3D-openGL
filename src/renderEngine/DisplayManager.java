package renderEngine;
import java.awt.Font;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.newdawn.slick.TrueTypeFont;

public class DisplayManager {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 960;
	private static final int FPS_CAP = 60;
	public static long lastFrameTime;
	public static float delta;
	public static void createDisplay(){		
		ContextAttribs attribs = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("b00m!");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0,0, WIDTH, HEIGHT);
		lastFrameTime = getCurrentTime();
	}
	
	public static void updateDisplay(){
		
		Display.sync(FPS_CAP);
		Display.update();
		long currentFremeTime = getCurrentTime();
		delta = (currentFremeTime - lastFrameTime)/1000f;
		lastFrameTime = currentFremeTime;
		
	}

	public static float getFrameTimeSeconds(){
		return delta;
	}
	public static void closeDisplay(){
		
		Display.destroy();
		
	}
	public static long getCurrentTime(){
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}
}
