package SoundRecord;

import Constans.FileConstants;
import GoogleRecognize.TranscriptVoice;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

public class RecordService {
    private SoundRecordingUtil soundRecordingUtil ;

    private TranscriptVoice transcriptVoice;

    private static final long RECORD_TIME = 3000;


    public RecordService() {
        soundRecordingUtil = new SoundRecordingUtil();
        transcriptVoice = new TranscriptVoice();
    }

    public void record(long recordTime) throws Exception {
        File wavFile = new File(FileConstants.URL_PATH);
        File flacFile = new File(FileConstants.URL_PATH_FLAC);
        final Thread recordThread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Start recording ...");
                try {
                    soundRecordingUtil.start();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }

            }
        });
        recordThread.start();
        try {
            Thread.sleep(recordTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            soundRecordingUtil.stop();
            soundRecordingUtil.save(wavFile,flacFile);
            System.out.println("STOPPED");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RecordException();
        }
    }


    public byte[] recordToBytes(long recordTime) throws RecordException {
        final Thread recordThread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Start recording ...");
                try {
                    soundRecordingUtil.start();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }

            }
        });
        recordThread.start();
        try {
            Thread.sleep(recordTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            soundRecordingUtil.stop();
            System.out.println("STOPPED");
            return soundRecordingUtil.save();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RecordException();
        }
    }
}
