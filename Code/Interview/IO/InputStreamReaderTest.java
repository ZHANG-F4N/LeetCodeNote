import java.io.*;

public class InputStreamReaderTest {

    /*
     *  转换流:
     *  InputStreamReader ： 将一个字节的输入流 转换为 字符 的输入流
     *  OutputStreamWriter ：将一个字符的输出流 转换为 字节 的输出流
     *  实现了 字节流 到 字符流之间的转换。
     *
     *  从 字节 --> 字符 是一种解码
     *  从 字符 --> 字节 是一种编码
     *
     *  这个过程存在字符集的设置问题
     * */
    public static void main(String[] args) throws IOException {

        /* 实现字节流到输入流的转换 */
        FileInputStream fis = new FileInputStream("Interview//IO//comment.txt");
        FileOutputStream fos = new FileOutputStream("Interview//IO//commentGBK.txt");
        // 参数二指明字符集,根据读取的文件字符集来确定
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
        char[] cbuf = new char[20];
        int len;
        while ((len = isr.read(cbuf)) != -1) {
            String s = new String(cbuf, 0, len);
            System.out.print(s);
            osw.write(s);
        }
        isr.close();
        osw.close();
//        InputStreamReader gbk = new InputStreamReader(new FileInputStream("Interview//IO//commentGBK.txt"), "GBK");
//        char[] cbuff = new char[20];
//        int len2;
//        System.out.println("==============");
//        while ((len2 = gbk.read(cbuff)) != -1) {
//            String s = new String(cbuff, 0, len2);
//            System.out.print(s);
//        }
//        gbk.close();
    }


}
