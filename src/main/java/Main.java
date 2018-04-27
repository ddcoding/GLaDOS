import Constans.FileConstants;
import GLaDOSService.GLaDOSService;
import SoundRecord.RecordService;
import ai.kitt.snowboy.SnowboyDetect;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    private static GLaDOSService gLaDOSService = new GLaDOSService();

    static {
        System.load(FileConstants.SNOWBOY_JNI_PATH);
    }

    public static void main(String[] args) throws Exception {
        gLaDOSService.startService();
    }
}