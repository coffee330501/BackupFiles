import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFiles {
    /**
     * 复制单个文件
     *
     * @param fromFile 要复制的文件
     * @param toFile   复制到哪个文件
     */
    public void copyFile(File fromFile, String toFile) {
        FileChannel readChannel = null;
        FileChannel writeChannel = null;
        try (
                FileInputStream readFile = new FileInputStream(fromFile);
                FileOutputStream writeFile = new FileOutputStream(toFile);
        ) {
            readChannel = readFile.getChannel();
            writeChannel = writeFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (readChannel.read(buffer) > 0) {
                buffer.flip();
                writeChannel.write(buffer);
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Logger.error("未找到文件" + e.getMessage());
        } catch (IOException e) {
            Logger.error(e.getMessage());
        } finally {
            if (writeChannel != null) {
                try {
                    writeChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (readChannel != null) {
                try {
                    readChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
