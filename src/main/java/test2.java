import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;

/**
 * Project name(项目名称)：文件加密解密
 * Package(包名): PACKAGE_NAME
 * Class(类名): test2
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/11/24
 * Time(创建时间)： 19:00
 * Version(版本): 1.0
 * Description(描述)： 转换
 */

public class test2
{
    public static void encryption_or_decrypt(String path)
    {
        byte pwd = 96;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try                                  //文件流打开，文件读写
        {
            fileInputStream = new FileInputStream(path);
            fileOutputStream = new FileOutputStream("cache.txt");
            int n = fileInputStream.available() / 5;
            byte[] buf = new byte[n];
            int count = 0;
            while ((count = fileInputStream.read(buf, 0, n)) != -1)
            {
                for (int i = 0; i < count; i++)
                {
                    buf[i] = (byte) (buf[i] ^ pwd);//密码与值进行异或运算
                }
                fileOutputStream.write(buf, 0, count);
            }
        }
        catch (FileNotFoundException e)      //文件未找到
        {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("文件未找到！！！  " + "\n错误内容：" + e.toString());
        }
        catch (Exception e)                  //其它异常
        {
            Toolkit.getDefaultToolkit().beep();
            e.printStackTrace();
        }
        finally
        {
            try                              //关闭流
            {
                if (fileInputStream != null)
                {
                    fileInputStream.close();
                }
                if (fileOutputStream != null)
                {
                    fileOutputStream.close();
                }
            }
            catch (NullPointerException e)    //空指针异常
            {
                Toolkit.getDefaultToolkit().beep();
                System.err.println("文件已经被关闭，无法再次关闭！！！");
            }
            catch (Exception e)              //其它异常
            {
                Toolkit.getDefaultToolkit().beep();
                e.printStackTrace();
            }
        }
        FileInputStream fileInputStream1 = null;
        FileOutputStream fileOutputStream1 = null;
        try                                  //文件流打开，文件读写
        {
            fileInputStream1 = new FileInputStream("cache.txt");
            fileOutputStream1 = new FileOutputStream(path);
            int count = 0;
            byte[] buffer = new byte[1024];
            while ((count = fileInputStream1.read(buffer)) != -1)
            {
                fileOutputStream1.write(buffer, 0, count);
            }
            FileOutputStream f = new FileOutputStream("cache.txt");
            f.close();
        }
        catch (FileNotFoundException e)      //文件未找到
        {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("文件未找到！！！  " + "\n错误内容：" + e.toString());
        }
        catch (Exception e)                  //其它异常
        {
            Toolkit.getDefaultToolkit().beep();
            e.printStackTrace();
        }
        finally
        {
            try                              //关闭流
            {
                if (fileInputStream1 != null)
                {
                    fileInputStream1.close();
                }
                if (fileOutputStream1 != null)
                {
                    fileOutputStream1.close();
                }
            }
            catch (NullPointerException e)    //空指针异常
            {
                Toolkit.getDefaultToolkit().beep();
                System.err.println("文件已经被关闭，无法再次关闭！！！");
            }
            catch (Exception e)              //其它异常
            {
                Toolkit.getDefaultToolkit().beep();
                e.printStackTrace();
            }
        }
        File file = new File("cache.txt");
        if (file.exists())
        {
            file.delete();
        }
    }


    public static void main(String[] args)
    {
        encryption_or_decrypt(args[0]);
    }
}
