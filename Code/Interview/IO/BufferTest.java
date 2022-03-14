import java.io.*;

public class BufferTest {
    public static void main(String[] args) throws IOException {
        TestBufferStream();
    }

    public static void TestBufferStream() throws IOException {
        /*非文本文件的复制*/
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 造文件
            File srcFile = new File("Interview//IO//testimg.jpg");
            File destFile = new File("Interview//IO//CopyIMG.jpg");
            // 造节点流
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            // 造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            // 简写
//            bis = new BufferedInputStream(new FileInputStream(new File("Interview//IO//testimg" +
//                    ".jpg")));
//            bos = new BufferedOutputStream(new FileOutputStream(new File("Interview//IO//CopyIMG" +
//                    ".jpg")));
            byte[] buffer = new byte[10];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 资源关闭
            // 先关外层的流,再关内层的流
            bis.close();
            bos.close();
            // 其实,关闭外层流时,会自动关闭内层流,也就是说下面的内层的流可以省略
            // fis.close();
            // fos.close();
        }
    }



}
