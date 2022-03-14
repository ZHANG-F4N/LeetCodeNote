import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputOutputStream {
    /*
     *  流的体系结构
     *  抽象基类             节点流                缓存流
     *  InputStream         FileInputStream     BufferedInputStream
     *  OutputStream        FileOutputStream    BufferedOutputSteam
     *  Reader              FileReader          BufferedReader
     *  Writer              FileWriter          BufferedWriter
     * */
    public static void main(String[] args) throws IOException {
        ImgTestFileInputOutputStream();
    }

    // 使用字节流处理图片
    public static void ImgTestFileInputOutputStream   () throws IOException {
        FileInputStream fr = null;
        FileOutputStream fw = null;
        try {
            File srcFile = new File("Interview//IO//testimg.jpg");
            File destFile = new File("Interview//IO//copyimg.jpg");

            fr = new FileInputStream(srcFile);
            fw = new FileOutputStream(destFile);
            byte[] cBuf = new byte[5];
            int len;
            while ((len = fr.read(cBuf)) != -1) {
                fw.write(cBuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fr != null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
