/*
package JSON.test;

import JSON.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class JacksonTest {

    //java对象转为json
    @Test
    public void test1() throws Exception {
        //创建Person对象
        Person p=new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");

        //创建Jackson的核心对象  ObjectMapper
        ObjectMapper mapper=new ObjectMapper();
        //转换

        */
/*       转换方法：
        *           writeValue(参数1，obj):
        *                   参数1：
        *                       File：将obj对象转换为JSON字符串，并保存到指定的文件中
        *                       Writer：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
        *                       OutputStream：将obj对象转换为JSON字符串，并将json数据填充到字节输出流中
        *           writeValueAsString(obj):键对象转为json字符串
        *//*

        String json=mapper.writeValueAsString(p);
       // System.out.println(json);

        //writeValue,将数据写到E://a.txt文件中
        mapper.writeValue(new File("E://a.txt"),p);

        //writeValue,将数据关联到writer中
        mapper.writeValue(new FileWriter("E://a.txt"),p);
    }




    @Test
    public void test2() throws Exception {
        //创建Person对象
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");
        p.setBirthday(new Date());


        //转换
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(p);

        System.out.println(json);
    }



    //List对象转JSON
    @Test
    public void test3() throws Exception {
        //创建Person对象
        Person p = new Person();
        p.setName("张三");
        p.setAge(23);
        p.setGender("男");
        p.setBirthday(new Date());


        Person p1 = new Person();
        p1.setName("张三");
        p1.setAge(23);
        p1.setGender("男");
        p1.setBirthday(new Date());


        Person p2 = new Person();
        p2.setName("张三");
        p2.setAge(23);
        p2.setGender("男");
        p2.setBirthday(new Date());


        //创建List集合
        List<Person> ps=new ArrayList<Person>();
        ps.add(p);
        ps.add(p1);
        ps.add(p2);

        //转换
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(ps);

        System.out.println(json);
    }


    //map对象转JSON
    @Test
    public void test4() throws Exception {
        //创建Person对象
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name","李四");
        map.put("age","26");
        map.put("gender","男");

        //转换
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(map);

        System.out.println(json);
    }



    //json字符串转为Java对象
    @Test
    public void test5() throws Exception {
        //初始化JSON字符串
        String json="{\"gender\":\"男\",\"name\":\"李四\",\"age\":\"26\"}";

        //创建ObjectMapper 对象
        ObjectMapper mapper=new ObjectMapper();
        Person person = mapper.readValue(json, Person.class);
        System.out.println(person);
    }

}
*/
