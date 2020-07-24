import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test1 {
    @Test
    public void test(){
        //输出当前项目路径 D:\WorkSpace\WorkSpace\TissueBank-New-ZSFE\CopyFiles
        System.out.println(System.getProperty("user.dir"));
    }
    @Test
    public void test2(){
        File file=new File("C:\\Users\\coffee\\Desktop\\项目存在问题.md");
        System.out.println(file.getName());//项目存在问题.md
    }
    @Test
    public void test3(){
        File file=new File("C:\\Users\\coffee\\Desktop\\ㅤ\\note");
        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println(f.getName());
        }
    }
    @Test
    public void test4(){
        File file=new File("C:\\Users\\coffee\\Desktop");
        File file2=new File("C:\\Users\\coffee\\Desktop\\项目存在问题.md");
        System.out.println(file2.getAbsolutePath().substring(file.getAbsolutePath().length()));//项目存在问题.md
    }//E:\学习\测试\mysql5.5\data\data\performance_schema\file_instances.frm
    @Test
    public void test5(){
        try {
            File file = new File("E:\\学习\\测试\\mysql5.5\\data\\data\\performance_schema\\file_instances.frm");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
