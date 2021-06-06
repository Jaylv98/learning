package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domin.PageBean;
import domin.User;
import service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao=new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User user) {

        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public void addUser(User user) {

        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {

        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {

        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if(ids!=null&&ids.length>0){
            //遍历数组
            for (String id:ids) {
                //调用dao删除
                dao.delete(Integer.parseInt(id));
            }
        }

    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {
        int currentPage=Integer.parseInt(_currentPage);
        int rows=Integer.parseInt(_rows);

        if(currentPage<=0){
            currentPage=1;
        }
        //创建空PageBean对象
        PageBean<User> pb=new PageBean<User>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setCurrentPage(rows);

        //调用dao查询总记录数
        int totalCount=dao.findTotalCount();
        pb.setTotalCount(totalCount);


        //计算开始记录的索引
        int start=(currentPage -1)*rows;
        //调用dao查询list集合
        List<User> list=dao.findByPage(start,rows);
        pb.setList(list);

        //计算总页码
        int totalPage=(totalCount % rows) == 0 ?totalCount/rows :(totalCount/rows)+1;
        pb.setTotalPage(totalPage);

        return  pb;
    }


}
