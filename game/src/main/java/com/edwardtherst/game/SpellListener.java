package com.edwardtherst.game;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class SpellListener {
    AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
    TargetDataLine line;
    public void init() {

    }
    public void listen() {
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, 
            format);
        if (!AudioSystem.isLineSupported(info)) {
        }
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
        } catch (LineUnavailableException ex) {
        }
        captureAudio();
    }
    public void match() {

    }
    public void play() {

    }

    private void captureAudio(){
        try{
          DataLine.Info dataLineInfo =
                              new DataLine.Info(
                                TargetDataLine.class,
                                format);
          line = (TargetDataLine)
                   AudioSystem.getLine(dataLineInfo);
    
          new CaptureThread().start();
        }catch (Exception e) {
          e.printStackTrace();
          System.exit(0);
        }
      }
    
    class CaptureThread extends Thread{
      public void run(){
        AudioFileFormat.Type fileType = null;
        File audioFile = null;
        fileType = AudioFileFormat.Type.WAVE;
        audioFile = new File("junk.wav");
    
        try{
          line.open(format);
          line.start();
          AudioSystem.write(
                new AudioInputStream(line),
                fileType,
                audioFile);
        }catch (Exception e){
          e.printStackTrace();
        }
    
      }
    }
    
}
