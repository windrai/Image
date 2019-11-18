package AllDemo.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.logging.Logger;

public class DemoMMap {
    private static Logger logger = Logger.getLogger(DemoMMap.class.getName());

    public static void main(String args[]) throws Exception {
        File file=new File("D:\\command");
        FileInputStream fileInputStream=new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        MappedByteBuffer map = channel.map(MapMode.READ_ONLY, 0, 1000);
        
    }
}
