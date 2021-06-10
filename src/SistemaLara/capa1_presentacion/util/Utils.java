package SistemaLara.capa1_presentacion.util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 * @author Antonio AB
 */
public class Utils {

    /**
     * Integer ID for other Windows versions, like Me, 2K and 9x
     */
    public static final int LINUX = 10;
    public static final int MAC_OS = 9;
    public static final int OTHER = 20;
    public static final int UNIX = 11;
    public static final int WINDOWS = 0;
    public static final int WINDOWS_10 = 5;
    public static final int WINDOWS_7 = 3;
    public static final int WINDOWS_8 = 4;
    public static final int WINDOWS_VISTA = 2;
    public static final int WINDOWS_XP = 1;
    private static int doHack = 0;
    private static Method getUsableBounds = null;
    static int operativeSystem;
    static Rectangle screen;

    static {
        String str = System.getProperty("os.name").toLowerCase();
        if (str.contains("windows")) {
            if (str.contains("xp")) {
                operativeSystem = 1;
            } else if (str.contains("vista")) {
                operativeSystem = 2;
            } else if (str.contains("7")) {
                operativeSystem = 3;
            } else if (str.contains("8")) {
                operativeSystem = 4;
            } else if (str.contains("10")) {
                operativeSystem = 5;
            } else {
                operativeSystem = 0;
            }
        } else if (str.contains("mac")) {
            operativeSystem = 9;
        } else if (str.contains("linux")) {
            operativeSystem = 10;
        } else if (str.contains("unix")) {
            operativeSystem = 11;
        } else {
            operativeSystem = 20;
        }
    }

    public static int getOperativeSystem() {
        return operativeSystem;
    }

    public static Rectangle getScreenSize() {
        if (doHack == 0) {
            try {
                Method[] meths = Class.forName("sun.java2d.SunGraphicsEnvironment").getDeclaredMethods();
                doHack = -1;
                for (Method meth : meths) {
                    if (meth.getName().equals("getUsableBounds")) {
                        if (Arrays.equals(meth.getParameterTypes(), new Class[]{GraphicsDevice.class}) && meth.getExceptionTypes().length == 0 && meth.getReturnType().equals(Rectangle.class)) {
                            getUsableBounds = meth;
                            doHack = 1;
                            break;
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                doHack = -1;
            }
        }
        if (doHack == 1) {
            try {
                Frame frame = new Frame();
                GraphicsConfiguration config = frame.getGraphicsConfiguration();
                screen = (Rectangle) getUsableBounds.invoke(null, new Object[]{config.getDevice()});
                frame.dispose();
            } catch (HeadlessException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
                doHack = -1;
            }
        }
        if (doHack != 1) {
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            screen = new Rectangle(0, 0, size.width, size.height);
        }
        System.out.println("Current workspace: " + screen.width + "x" + screen.height + "px");
        return screen;
    }

    public static boolean isTranslucencySupported() {
        if (System.getProperty("java.version").contains("1.6")) {
            System.err.println("Per-pixel translucency is currently not supported.\nPlease upgrade your JRE to at least Java 7 to support this feature.");
            return false;
        } else if (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().isWindowTranslucencySupported(WindowTranslucency.PERPIXEL_TRANSLUCENT)) {
            return true;
        } else {
            System.err.println("Error while starting: Per-pixel translucency is not supported.");
            return false;
        }
    }

    public static Image getBackgroundCap(Rectangle bounds) {
        try {
            return new Robot().createScreenCapture(bounds);
        } catch (AWTException e) {
            return null;
        }
    }
}
