import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * �Զ���������������ڶ�ȡ���⴦������ֽ����ļ�
 *
 * @author gaodong
 * @program jvm
 * @description �Զ������������ȡһ��������������ֽ����ļ�
 * @create 2021-01-12 19:19
 * @since 1.8
 **/
public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args){
        try{
            Class<?> clz = new HelloClassLoader().findClass("Hello");
            Object obj = clz.getConstructor().newInstance();
            System.out.println(obj);
            clz.getMethod("hello").invoke(obj);
        }
        catch(InstantiationException | IllegalAccessException e){
            System.out.println(e);
        }
        catch(NoSuchMethodException e){
            System.out.println(e);
        }
        catch(InvocationTargetException e){
            System.out.println(e);
        }

    }

    @Override
    public Class<?> findClass(String name){
        try(
                FileInputStream fi = new FileInputStream(".\\Hello.xlass");
                BufferedInputStream bi = new BufferedInputStream(fi);
        ){
            byte[] bytes = new byte[bi.available()];
            int pos = 0;
            while(bi.available() > 0){
                int c = 255 - bi.read();
                bytes[pos] = (byte)c;
                pos++;
            }
            return defineClass(name,bytes,0,bytes.length);
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch(IOException e){
            System.out.println(e);
        }
        return null;
    }


}
