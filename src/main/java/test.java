import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Project name(项目名称)：文件加密解密
 * Package(包名): PACKAGE_NAME
 * Class(类名): test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/11/24
 * Time(创建时间)： 18:31
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class test
{
    static long startTime;   //获取开始时间

    public static void start()
    {
        //------------------------------------------------------
        startTime = System.nanoTime();
        //------------------------------------------------------
    }

    public static void end()
    {
        System.out.println();
        //------------------------------------------------------
        long endTime = System.nanoTime(); //获取结束时间
        if ((endTime - startTime) < 1000000)
        {
            double final_runtime;
            final_runtime = (endTime - startTime);
            final_runtime = final_runtime / 1000;
            System.out.println("算法运行时间： " + final_runtime + "微秒");
        }
        else if ((endTime - startTime) >= 1000000 && (endTime - startTime) < 10000000000L)
        {
            double final_runtime;
            final_runtime = (endTime - startTime) / 1000;
            final_runtime = final_runtime / 1000;
            System.out.println("算法运行时间： " + final_runtime + "毫秒");
        }
        else
        {
            double final_runtime;
            final_runtime = (endTime - startTime) / 10000;
            final_runtime = final_runtime / 100000;
            System.out.println("算法运行时间： " + final_runtime + "秒");
        }
        Runtime r = Runtime.getRuntime();
        float memory;
        memory = r.totalMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("JVM总内存：%.3fMB\n", memory);
        memory = r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf(" 空闲内存：%.3fMB\n", memory);
        memory = r.totalMemory() - r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("已使用的内存：%.4fMB\n", memory);
        //------------------------------------------------------
    }

    public static void main(String[] args) throws IOException
    {
        if (args.length==2&&args[1].equals("test2"))
        {
            test2.main(args);
            System.exit(1);
        }
        String path;
        if (args.length == 0)
        {
            Scanner input = new Scanner(System.in);
            System.out.print("请输入路径：");
            path = input.nextLine();
        }
        else
        {
            path = args[0];
        }
        String[] str = path.split("\\.");
        if (str.length != 2 && str.length != 1)
        {
            System.out.println("路径输入有误！！！");
            System.exit(1);
        }
        String pathout;
        if (str.length == 1)
        {
            pathout = path + "out";
        }
        else
        {
            pathout = str[0] + "out." + str[1];
        }
        System.out.println("1.加密       2.解密");
        System.out.print("请输入：");
        Scanner input = new Scanner(System.in);
        char ch = input.next().charAt(0);
        if (ch == '1')
        {
            byte pwd = 96;
            FileInputStream f = new FileInputStream(path);
            FileOutputStream fout = new FileOutputStream(pathout);
            start();
            System.out.println("开始加密...");
            int n = f.available() / 5;
            byte[] buf = new byte[n];
            int count = 0;
            while ((count = f.read(buf, 0, n)) != -1)
            {
                for (int i = 0; i < count; i++)
                {
                    buf[i] = (byte) (buf[i] ^ pwd);//密码与值进行异或运算
                }
                fout.write(buf, 0, count);
            }
            System.out.println("完成加密");
            end();
            f.close();
            fout.close();
        }
        else if (ch == '2')
        {
            byte pwd = 96;
            FileInputStream f = new FileInputStream(path);
            FileOutputStream fout = new FileOutputStream(pathout);
            start();
            System.out.println("开始解密...");
            int n = f.available() / 5;
            byte[] buf = new byte[n];
            int count = 0;
            while ((count = f.read(buf, 0, n)) != -1)
            {
                for (int i = 0; i < count; i++)
                {
                    buf[i] = (byte) (buf[i] ^ pwd);//密码与值进行异或运算
                }
                fout.write(buf, 0, count);
            }
            System.out.println("完成解密");
            end();
            f.close();
            fout.close();
        }
        else
        {
            Toolkit.getDefaultToolkit().beep();
            System.out.println("输入错误！！！");
        }
    }
}
