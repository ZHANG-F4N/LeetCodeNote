import java.io.*;

public class OtherStream {


    /*
     *  其他类型的流主要有三类
     *  1. 标准的输入输出流.
     *  2. 打印流
     *  3. 数据流
     *
     * */
    public static void main(String[] args) throws IOException {
        /*
         *  标准的输入流
         *  System.in: 标准的输入流,默认从键盘输入
         *  System.out: 标准的输出流,默认从控制台输出
         *
         *  System类的setIn(InputStream is) / setOut(PrintStream ps)方式重新指定输入输出的流
         *
         * */

        // 实现一个输入转为大写的程序
        // 1. 用Scanner实现
        // 2. 用System.in实现
        //    System.in --> 转换流 --> BufferedReader 的 readLine()

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        while (true) {
            String s = br.readLine();
            if ("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s)) {
                System.out.println("EXIT.");
                break;
            }
            System.out.println(s.toUpperCase());
        }
        br.close();

        // 打印流
        // PrintStream PrintWriter
        // 它们提供了一系列的 print println方法
//        FileOutputStream fos = new FileOutputStream(new File("Interview//IO//hello.txt"));
//        PrintStream stream = new PrintStream(fos);
//        // 把输入目的地改为文件,而不是显示器
//        if (stream != null) System.setOut(stream);

        // 数据流
        // 为了方便操作Java语言中的基本类型和 String,
//        DataInputStream 套接到 InputStream
//        DataOutputStream 套接到 OutputStream
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("Interview//IO//hello" +
                ".txt"));

        dos.writeUTF("中国");

        // 刷新操作 flush-冲洗
        dos.flush();
        dos.writeInt(23);
        dos.flush();
        dos.writeBoolean(true);
        dos.close();
        DataInputStream dis = new DataInputStream(new FileInputStream("Interview//IO//hello.txt"));
        String country = dis.readUTF();
        int age = dis.readInt();
        boolean b = dis.readBoolean();
        System.out.println(country+" "+age+" "+b);
        dis.close();

    }

}
