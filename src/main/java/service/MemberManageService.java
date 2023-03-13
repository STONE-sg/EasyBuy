package service;

import dao.MemberDao;
import entity.Goods;
import entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.MSUtil;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: stone
 * @program: SSM_Demo
 * @description:
 * @date: 2021-11-18 11:50:20
 */
@Service
public class MemberManageService {
    private final MemberDao memberDao;

    public MemberManageService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    //会员注册
    public Result registerMember(Member member){
        Result result=new Result();
        Member checkMember = memberDao.findMemberByUname(member);
        if (checkMember != null){
            result.setStatus(1);
            result.setMsg("此用户已存在");
            return result;
        }
        int affectedRowCount=memberDao.addMember(member);
        if (affectedRowCount == 1){
            result.setStatus(0);
            result.setMsg("注册成功");
        } else {
            result.setMsg("注册失败");
        }
        return result;
    }
    //会员登录
    public Result checkLogin(Member member){
        Result result=new Result();
        Member memberResult = memberDao.findMemberByUname(member);
        if (memberResult == null){
            result.setStatus(1);
            result.setMsg("此用户不存在");
            return result;
        } else if (!memberResult.getPassword().equals(member.getPassword())){
            result.setStatus(1);
            result.setMsg("密码错误");
            return result;
        } else {
            result.setStatus(0);
            result.setMsg("登录成功");
            result.setData(member);
            return result;
        }
    }
}

















/*    //会员注册
    public Result registMember(String uname, String password){
        Result result = new Result();
        Member checkMember = memberDao.findByUname(uname);
        if (checkMember != null){
            System.out.println(uname);
            result.setStatus(1);
            result.setMsg("此用户已存在");
            return result;
        }

        Member member = new Member();
        member.setMemberId(null);
        member.setUname(uname);

//        String md5_pwd = MSUtil.md5(password);
        member.setPassword(password);
        member.setEmail("");
        member.setSex((short) 0);
        member.setMobile("");
        member.setRegtime(null);
        member.setLastlogin(null);
        member.setImage("");
        memberDao.addUser(member);

        result.setStatus(0);
        result.setMsg("注册成功");
        result.setData(member);
        return result;
    }

    //会员登录
    public Result checkLogin(String uname, String password) {
        Result result = new Result();
        Member member = memberDao.findByUname(uname);
        //判断用户是否存在
        if (member == null){
            System.out.println(uname);
            result.setStatus(1);
            result.setMsg("此用户不存在");
            return result;
        }
        //判断密码是否正确
        String md5_pwd = MSUtil.md5(password);
//        if (!md5_pwd.equals(member.getPassword())){
        if (!password.equals(member.getPassword())){
            result.setStatus(1);
            result.setMsg("密码错误");
            return result;
        }
        //更新最后登录的时间
        int memberId = member.getMemberId();
        Member updateMember = new Member();
        updateMember.setMemberId(memberId);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        updateMember.setLastlogin(now);
        memberDao.dynamicUpdate(updateMember);
        member.setLastlogin(now);//

        result.setStatus(0);
        result.setMsg("用户名和密码正确");
        result.setData(member);
        return result;
    }

    public Result addUser(Member member){
        Result result=new Result();
        int affectedRowCount=memberDao.addUser(member);
        if (affectedRowCount == 1){
            result.setStatus(0);
            result.setMsg("添加用户成功");
        } else {
            result.setMsg("添加用户失败");
        }
        return result;
    }

    public Result findUser(Member member){
        Result result=new Result();
        List<Member> users=memberDao.findUser(member);
        result.setData(users);
        return result;
    }

    public Result updateUserInfo(Member member){
        Result result=new Result();
        int affectedRowCount=memberDao.updateUserInfo(member);
        if (affectedRowCount == 1){
            result.setStatus(0);
            result.setMsg("修改用户信息成功");
        } else {
            result.setMsg("修改用户信息失败");
        }
        return result;
    }

    public Result deleteUser(Member member){
        Result result=new Result();
        memberDao.deleteUser(member);
        result.setMsg("删除用户完成");
        return result;
    }*/
