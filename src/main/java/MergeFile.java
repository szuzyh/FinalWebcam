import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Leo on 2016/11/28.
 */
public class MergeFile {
    public static void main(String args[]) throws IOException {
        File file0=new File("E:\\out\\output0.flv");
        File file1=new File("E:\\out\\output1.flv");

        File out=new File("E:\\out\\output.mp4");
        FileInputStream in0=new FileInputStream(file0);
        FileInputStream in1=new FileInputStream(file1);

        FileOutputStream out2=new FileOutputStream(out);



        //创建一个合并流的对象
        SequenceInputStream sis = null;
        //创建输出流。
        BufferedOutputStream bos = null;
        try {
            //构建流集合。
            Vector<InputStream> vector = new Vector<InputStream>();
            vector.addElement(in0);
            vector.addElement(in1);

            Enumeration<InputStream> e = vector.elements();


            sis = new SequenceInputStream(e);

            bos = new BufferedOutputStream(out2);
            //读写数据
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = sis.read(buf)) != -1) {
                System.out.println(len);
                bos.write(buf, 0, len);
                bos.flush();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (sis != null)
                    sis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
