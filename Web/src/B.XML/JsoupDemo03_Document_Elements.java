package B.XML;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/*
*           Document：文档对象。代表内存中的DOM树
*                   获取Element对象
*                            getElementById(String id)：根据id属性值获取唯一的element对象
*                            getElementsByTag(String tagName)：根据标签名称获取元素对象集合
*                            getElementsByAttribute(String key)：根据属性名称获取元素对象集合
*                            getElementsByAttributeValue(String key, String value)：根据对应的属性名和属性值获取元素对象集合
*
* */
public class JsoupDemo03_Document_Elements {
    public static void main(String[] args) throws IOException {
        String path = JsoupDemo03_Document_Elements.class.getClassLoader().getResource( "xml/student.xml" ).getPath();
        Document document = Jsoup.parse( new File( path ), "UTF-8" );

        //1.获取所有的student对象
        Elements student = document.getElementsByTag( "student" );
        System.out.println(student);
        System.out.println("========================");

        //2.获取id属性的元素对象
        Elements id = document.getElementsByAttribute( "id" );
        System.out.println(id);
        System.out.println("========================");

        //3.根据id属性值获取唯一的element对象
        Elements value = document.getElementsByAttributeValue( "id", "itcast" );
        System.out.println(value);
    }


}
