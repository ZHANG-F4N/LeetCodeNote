import org.junit.Test;

import java.io.*;
import java.util.Arrays;

public class FileReaderWriter {
    /*
     *  流的体系结构
     *  抽象基类             节点流                缓存流
     *  InputStream         FileInputStream     BufferedInputStream
     *  OutputStream        FileOutputStream    BufferedOutputSteam
     *  Reader              FileReader          BufferedReader
     *  Writer              FileWriter          BufferedWriter
     * */


    public static void main(String[] args) throws IOException {
        testFileReader();
        testFileReader1();
        TestFileWriter();
    }

    // 测试 FileReader
    // 为了保证资源一定可以被关闭,使用try-catch-finally来关闭资源
    public static void testFileReader() {
        FileReader fr = null;
        try {
            // 1. 实例化File类对象
            File file = new File("Interview\\hello.txt"); // 路径相较于当前工程
            // 2. 提供具体的流
            fr = new FileReader(file);
            // 3. 数据的读入过程
            // RETURN The character read, or -1 if the end of the stream has been reached
            // 返回一个读入的字符,如果到达文件末尾返回-1
            int data;
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 4. 关闭流操作
                // JVM 虽然有垃圾回收机制,但对于物理连接无能为力,比如: 数据库连接、输入输出流、Socket
                if (fr != null) fr.close();
                // 因为 fr可能在前面由于打开失败而为null,不能直接close
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testFileReader1() {

        FileReader fr = null;
        try {
            File file = new File("Interview//hello.txt");
            fr = new FileReader(file);
            char[] cBuf = new char[5];
            // RETURN   The number of characters read,
            //          or -1 if the end of the stream has been reached
            int len;
            while ((len = fr.read(cBuf)) != -1) {
//                错误写法1
//                System.out.println(Arrays.toString(cBuf));//
//                错误写法2
//                for (int i = 0; i < cBuf.length; i++) {
//                    System.out.print(cBuf[i]);
//                }
                // 正确1
                for (int i = 0; i < len; i++) {
                    System.out.print(cBuf[i]);
                }
                // 正确2
                String s = new String(cBuf, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 从内存中写出数据到磁盘的文件里。
    public static void TestFileWriter() throws IOException {

        File file = new File("Interview//helloWriter.txt");
        System.out.println(file.getAbsolutePath());
        // file文件本身没有写出的能力,
        FileWriter fw = new FileWriter(file);
        fw.write("Fuck F4N!");
        fw.write("2022-3-11");
        fw.close();
    }
}
