import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Leo on 2016/11/28.
 */
public class CreateVideo {
    public  CreateVideo(String path) throws InterruptedException {
        File file = new File("E:\\output"+path+".mp4");
        IMediaWriter writer = ToolFactory.makeWriter(file.getAbsolutePath());
        Dimension size = WebcamResolution.QVGA.getSize();
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, 704,578);
        System.out.println(size.width);
        System.out.println(size.height);
        Webcam webcam = Webcam.getDefault();
        webcam.open(true);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {         //一分钟07 秒
            System.out.println("Capture frame " + i);
            BufferedImage image = ConverterFactory.convertToType(webcam.getImage(), BufferedImage.TYPE_3BYTE_BGR);
            IConverter converter = ConverterFactory.createConverter(image, IPixelFormat.Type.YUV420P);
            IVideoPicture frame = converter.toPicture(image, (System.currentTimeMillis() - start) * 1000);
            frame.setKeyFrame(i == 0);
            frame.setQuality(0);
            writer.encodeVideo(0,frame);
            // 10 FPS
            Thread.sleep(100);
        }
        writer.close();
        System.out.println("Video recorded in file: " + file.getAbsolutePath());


    }
}
