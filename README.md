# BackupFiles v1.0
## 如何使用？
方式一：将jar包放置在你要备份的文件夹里，使用命令`java -jar Backup.jar` 后会自动备份当前文件夹及子文件夹下所有到默认的位置（默认备份到E:\学习\note目录下，当然你可以修改Main.java的toPath变量然后生成jar）；
方式二：使用命令`java -jar Backup.jar E:\学习\note` 指定要备份到哪个文件夹；
方式三：使用命令`java -jar Backup.jar D:\DownLoad E:\学习\note` 指定把 D:\DownLoad 下的文件备份到 E:\学习\note 中。
## 如何不使用命令行启动备份
新建一个Backup.txt，写入内容：
````bash 
::@echo off 
java -jar "C:\你的jar包位置\Backup.jar" "D:\DownLoad" "E:\学习\note"
exit
  ````
  将后缀.txt改为.bat，双击即可运行。
### 设置开机自动备份
在不使用命令行备份的基础上，新建文件Back.vbs，写入内容：
````vb
set ws=WScript.CreateObject("WScript.Shell")
ws.run "D:\你对应的bat文件路径\Backup.bat",0
````
然后把文件放入系统目录`C:\Users\用户名\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup`下即可实现开机自动备份文件。