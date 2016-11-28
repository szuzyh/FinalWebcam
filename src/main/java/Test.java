/**
 * Created by Leo on 2016/11/25.
 */
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamStreamer;


import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import uk.co.caprica.vlcj.medialist.MediaListItem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.InflaterInputStream;

/**
 * Created by Leo on 2016/11/23.
 */
public class Test {


    public static void main(String args[]) throws Throwable {
        new InitWebcam();
        String path0="0";
        String path1="1";
        new CreateVideo(path0);
        new CreateVideo(path1);

        new Change(path0);
        new Change(path1);
//        int finalVideoId=0;
//        long time=1;
//        long Time=0;
//        for (int i=0;i<2;i++){
//            String I=i+"";
//            new CreateVideo(I);
//            String filePath="E:\\output"+I+".avi";
//              Time+=JaveTestForTime(filePath);
//            if ((Time/60000)>=time){
//                finalVideoId=i;
//                break;
//            }
//        }

    }

    public static long JaveTestForTime(String path) {
        File source= new File(path);
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration();
            System.out.println("此视频时长为:"+ls/60000+"分"+(ls)/1000+"秒！");
            return ls;
        } catch(Exception e) {
            e.printStackTrace();
            return Long.parseLong(null);
        }
    }
}
