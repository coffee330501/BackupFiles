import java.io.File;
import java.io.IOException;

public class BackupFiles {
    private final CopyFiles copyFiles = new CopyFiles();
    private final MyThreadPool pool = MyThreadPool.getThreadPool();
    private final String fromPath;

    public BackupFiles(String fromPath) {
        this.fromPath = fromPath;
    }

    /**
     * 递归遍历目录，是文件则复制，是目录则创建目录，再递归
     *
     * @param fromPath 文件路径  C:\Users\coffee\Desktop\星星
     * @param toPath   复制路径   E:\学习\note
     */
    public void backFiles(File fromPath, String toPath) {
        File[] files = fromPath.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            //文件或文件夹名字，得到的文件名前面多了一个/
            String fileName = f.getAbsolutePath().substring(this.fromPath.length());
            if (f.isDirectory()) {
                createDirection(toPath + fileName);
                backFiles(f, toPath);
            } else {
                pool.execute(() -> {
                    File file = new File(toPath + fileName);
                    try {
                        /*
                        如果文件存在，则删除文件后创建
                        注意：不要使用file.deleteOnExit();
                        这个方法会在在JVM进程退出的时候删除文件,通常用在临时文件的删除。
                        这个不会立刻执行，会等到jvm进程退出的时候删除。会导致下面创建的文件被删除。
                         */
                        if (file.exists()) {
                            file.delete();
                        }
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    copyFiles.copyFile(f, toPath + fileName);
                });
            }
        }
    }

    @SuppressWarnings("all")
    private void createDirection(String directionPath) {
        File file = new File(directionPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
