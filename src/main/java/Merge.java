import com.coremedia.iso.boxes.*;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.AppendTrack;

import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Leo on 2016/11/28.
 */
public class Merge {
    public static void main(String args[]){
        List<String> fileList = new ArrayList<String>();
        List<Movie> moviesList = new LinkedList<Movie>();
        fileList.add("E:\\output0.mp4");
        fileList.add("E:\\output.mp4");

        try
        {
            for (String file : fileList)
            {
                moviesList.add(MovieCreator.build(file));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        List<Track> videoTracks = new LinkedList<Track>();
        List<Track> audioTracks = new LinkedList<Track>();
        for (Movie m : moviesList)
        {
            for (Track t : m.getTracks())
            {
                if (t.getHandler().equals("soun"))
                {
                    audioTracks.add(t);
                }
                if (t.getHandler().equals("vide"))
                {
                    videoTracks.add(t);
                }
            }
        }

        Movie result = new Movie();

        try
        {
            if (audioTracks.size() > 0)
            {
                result.addTrack(new AppendTrack(audioTracks.toArray(new Track[audioTracks.size()])));
            }
            if (videoTracks.size() > 0)
            {
                result.addTrack(new AppendTrack(videoTracks.toArray(new Track[videoTracks.size()])));
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        com.coremedia.iso.boxes.Container out = new DefaultMp4Builder().build(result);

        try
        {
            FileChannel fc = new RandomAccessFile("E:\\out\\output.mp4", "rw").getChannel();
            out.writeContainer(fc);
            fc.close();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        moviesList.clear();
        fileList.clear();
    }
}
