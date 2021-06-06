package B.XML;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/*
*       Element：元素对象
							1. 获取子元素对象
									getElementById(String id)：根据id属性值获取唯一的element对象
									getElementsByTag(String tagName)：根据标签名称获取元素对象集合
									getElementsByAttribute(String key)：根据属性名称获取元素对象集合
									getElementsByAttributeValue(String key, String value)：
											根据对应的属性名和属性值获取元素对象集合

							2. 获取属性值
									String attr(String key)：根据属性名称获取属性值
							3. 获取文本内容
									String text():获取文本内容
									String html():获取标签体的所有内容(包括字标签的字符串内容)
*
* */
public class JsoupDemo04_Element {
    public static void main(String[] args) throws IOException {
        String path = JsoupDemo04_Element.class.getClassLoader().getResource( "xml/student.xml" ).getPath();
        Document document = Jsoup.parse( new File( path ), "UTF-8" );

        //通过document获取所有的name标签，可以获取两个
        Elements name = document.getElementsByTag( "name" );
        System.out.println(name.size());

        //通过element对象子标签对象,可以获取一个
        Element ele_Student = document.getElementsByTag( "name" ).get( 0 );
        Elements els_name = ele_Student.getElementsByTag( "name" );
        System.out.println(els_name.size());

        //获取student对象的属性值
        String number = ele_Student.attr( "number" );
        System.out.println(number.length());
        //获取文本内容
        String text = els_name.text();
        String html = els_name.html();
        System.out.println(text);
        System.out.println(html);
    }


}
