package knight.arkham.helpers;

import knight.arkham.Space;

public class Constants {
    public static final float PIXELS_PER_METER = 32.0f;
    public static final float FULL_SCREEN_HEIGHT = Space.INSTANCE.screenHeight / PIXELS_PER_METER;
    public static final float FULL_SCREEN_WIDTH = Space.INSTANCE.screenWidth / PIXELS_PER_METER;
    public static final short PLAYER_BIT = 1;
    public static final short PIPE_BIT = 2;
    public static final short FLOOR_BIT = 4;
}
