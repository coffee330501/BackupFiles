import java.io.File;

public class Main {
    private static String fromPath=System.getProperty("user.dir");//当前项目路径（jar包位置）
    private static String toPath ="E:\\学习\\note";//复制到哪
    public static void main(String[] args) {
        Logger.message("默认复制本目录下的所有文件到 E:/学习/note/ 目录下\n" +
                "如果要指定复制到哪，请传入路径\n" +
                "如果要指定复制的目录和复制到哪，请输入两个路径，以空格分隔");
        switch (args.length){
            case 0:
                break;
            case 1:
                toPath=args[0];
                break;
            case 2:
                fromPath=args[0];
                toPath=args[1];
                break;
            default:
                Logger.error("参数数量有误，将以默认方式复制");
        }
        long startTime=System.currentTimeMillis();
        BackupFiles backupFiles = new BackupFiles(fromPath);
        File file = new File(fromPath);
        backupFiles.backFiles(file, toPath);
        //关闭线程池
        MyThreadPool.getThreadPool().stop();
        long endTime=System.currentTimeMillis();
        Logger.message("备份完成,共花费"+((double)(endTime-startTime)/1000)+"秒");
    }
}
