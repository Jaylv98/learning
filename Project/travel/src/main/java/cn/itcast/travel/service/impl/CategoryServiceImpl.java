package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao=new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {

        //从redis中查询
        //获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //可使用sortedset排序查询
        //Set<String> categories = jedis.zrange("category", 0, -1);
        //查询sortedset中的分数（cid）和值（cname）
        Set<Tuple> categories = jedis.zrangeWithScores("category", 0, -1);

        List<Category> cs=null;
        //判断查询的集合是否为null
        if(categories==null||categories.size()==0){

            System.out.println("从数据库查询");

            //如果为空，需要从数据库查询，再将数据存入redis
            cs = categoryDao.findAll(); //从数据库查询

            //将集合数据存储到redis中的category的key
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else {

            System.out.println("从redis中查询");

            //如果不为空，将set的数据存入list(将set对象转换为list对象)
            cs=new ArrayList<Category>();
            for (Tuple name:categories) {

                Category category = new Category();
                category.setCname(name.getElement());
                category.setCid((int)name.getScore());
                cs.add(category);
            }
        }

        return cs;
    }
}
