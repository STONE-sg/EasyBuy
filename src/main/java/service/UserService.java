package service;

import dao.MemberDao;
import dao.UserDao;
import entity.Goods;
import entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: stone
 * @program: EasyBuy
 * @description:
 * @date: 2021-12-29 20:33:02
 */
@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    //新增用户
    public Result addUser(Member member){
        Result result=new Result();
        int affectedRowCount=userDao.addUser(member);
        System.out.println(affectedRowCount);
        if (affectedRowCount == 1){
            result.setStatus(0);
            result.setMsg("添加用户成功");
        } else {
            result.setMsg("添加用户失败");
        }
        return result;
    }
    //查找用户
    public Result findUsers(Member member){
        Result result=new Result();
        List<Member> userList=userDao.findUsers(member);
        result.setData(userList);
        return result;
    }
    //修改用户信息
    public Result updateUserInfo(Member member){
        Result result=new Result();
        int affectedRowCount=userDao.updateUserInfo(member);
        if (affectedRowCount == 1){
            result.setStatus(0);
            result.setMsg("修改用户信息成功");
        } else {
            result.setMsg("修改用户信息失败");
        }
        return result;
    }
    //删除用户
    public Result deleteUser(Member member){
        Result result=new Result();
        userDao.deleteUser(member);
        result.setMsg("删除用户完成");
        return result;
    }
}
