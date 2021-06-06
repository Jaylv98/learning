package B.XML;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;


/*
*       Jsoup：工具类，可以解析html或xml文档，返回document
*									parse：解析html或xml文档，返回Document
*										parse(File in,String charsetName):解析xml或html文件
*										parse(String html)：解析xml或html字符串
*                                       parse(URL url, int timeoutMillis)：通过网络路径获取指定的html或xml的文档对象
*
* */
public class JsoupDemo02_Jsoup {
    public static void main(String[] args) throws IOException {
        //获取xml解析文件的路径
        String path = JsoupDemo01.class.getClassLoader().getResource( "xml/student.xml" ).getPath();

        //1.解析xml文档，加载文档进内存,获取document对象
        Document document = Jsoup.parse(new File( path ),"UTF-8");

        //2.parse(String html):解析xml或HTML字符串
        String str="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "\n" +
                "<students>\n" +
                "\t<student number=\"0001\">\n" +
                "\t\t<name>tom</name>\n" +
                "\t\t<age>18</age>\n" +
                "\t\t<sex>male</sex>\n" +
                "\t</student>\n" +
                "\t<student number=\"0002\">\n" +
                "\t\t<name>jack</name>\n" +
                "\t\t<age>18</age>\n" +
                "\t\t<sex>female</sex>\n" +
                "\t</student>\n" +
                "</students>";
        Document document1 = Jsoup.parse( str );
//        System.out.println(document1);

        //3.parse(URL url ,int timeoutMillis):通过网络路径获取指定的HTML或xml文档对象
        URL url=new URL("https://mp.csdn.net/postedit/82555583");
        Document document2 = Jsoup.parse( url,10000);
//        System.out.println(document2);


    }


}
