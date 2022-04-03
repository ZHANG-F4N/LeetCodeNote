import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class fastCopy {
    private static final String FILE_PATH = "Interview//NIO//";

    public static void main(String[] args) throws IOException {

        /*输入字节流*/
        FileInputStream fin = new FileInputStream(FILE_PATH + "hello.txt");
        /*输入通道*/
        FileChannel fcin = fin.getChannel();

        /*输出管道*/
        FileOutputStream fout = new FileOutputStream(FILE_PATH + "out.txt");
        /*输出字节流*/
        FileChannel fcout = fout.getChannel();

        /* 为缓冲区分配 1024 个字节 */
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {

            int read = fcin.read(buffer);
            if (read == -1) break;
            /*切换读写*/
            buffer.flip();
            /* 把缓冲区的内容写入输出文件中 */
            fcout.write(buffer);
            /* 清空缓冲区 */
            buffer.clear();

        }
        fcout.close();
        fcin.close();


    }
}
