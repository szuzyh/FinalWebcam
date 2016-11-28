
import com.github.sarxos.webcam.*;
import com.ibm.media.codec.audio.BufferedEncoder;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IMediaDataWrapper;
import com.xuggle.xuggler.video.ConverterFactory;
import org.eclipse.jetty.security.Authenticator;
import uk.co.caprica.vlcj.medialist.MediaListItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2016/11/25.
 */
public class TestFFmpeg {

    public static void main(String args[]) throws InterruptedException, IOException {
        String name1 = "ace";
        //String rtsp1 = "rtsp://admin:12345@192.168.2.68:554/h264/ch1/main/av_stream";
        String rtsp1 = "rtsp://admin:12345@192.168.2.68:554/h264/ch1/main/av_stream";
        List<MediaListItem> mediaListItemList=new ArrayList<MediaListItem>();
        mediaListItemList.add(new MediaListItem(name1,rtsp1,new ArrayList<MediaListItem>()));
        Webcam.setDriver(new VlcjDriver(mediaListItemList));

        File file = new File("E:\\output.mp4");
        IMediaWriter writer = ToolFactory.makeWriter(file.getName());
        Dimension size = WebcamResolution.QVGA.getSize();

        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, size.width, size.height);

        final Webcam webcam=Webcam.getDefault();
        webcam.setViewSize(size);
        webcam.open(true);
        final WebcamStreamer webcamStreamer=new WebcamStreamer(554,Webcam.getDefault(),10,true);
        IContainer container=IContainer.make();




    }
}
