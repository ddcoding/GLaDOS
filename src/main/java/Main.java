import Constans.FileConstants;
import GLaDOSService.GLaDOSService;
import SoundRecord.RecordService;
import ai.kitt.snowboy.SnowboyDetect;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    private static GLaDOSService gLaDOSService = new GLaDOSService();

    private static final String OS = System.getProperty("os.name");

    static {
        if(!OS.toLowerCase().equals("windows"))
        System.load(FileConstants.SNOWBOY_JNI_PATH);
    }

    public static void main(String[] args) throws Exception {
        gLaDOSService.startService();
    }
}