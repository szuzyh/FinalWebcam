import com.sun.org.apache.bcel.internal.Constants;
import it.sauronsoftware.jave.*;

import java.io.File;

/**
 * Created by Leo on 2016/11/28.
 */
public class Change {

    public Change(String path) {
        File source = new File("E:\\output"+path+".mp4");
        File target = new File("E:\\out\\output"+path+".flv");// 转flv

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(64000));
        audio.setChannels(new Integer(1));
        audio.setSamplingRate(new Integer(22050));
        VideoAttributes video = new VideoAttributes();
        video.setCodec("flv");// 转flv
        video.setBitRate(new Integer(180000));// 180kb/s比特率
        video.setFrameRate(new Integer(1));// 1f/s帧频，1是目前测试比较清楚的，越大越模糊
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("flv");// 转flv
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        long beginTime = System.currentTimeMillis();
        try {
            // 获取时长
            MultimediaInfo m = encoder.getInfo(source);
            long sTime = m.getDuration();
            long minute = sTime / 60000;
            long second = (sTime % 60000) / 1000;
            System.out.println("视频时长：" + minute + ":" + (second < 10 ? "0" + second : second));
            System.out.println("获取时长花费时间是：" + (System.currentTimeMillis() - beginTime));
            beginTime = System.currentTimeMillis();
            encoder.encode(source, target, attrs);
            System.out.println("视频转码花费时间是：" + (System.currentTimeMillis() - beginTime));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InputFormatException e) {
            e.printStackTrace();
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}
