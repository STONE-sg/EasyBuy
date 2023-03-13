package controller;

import entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.MemberManageService;
import service.Result;

import javax.annotation.Resource;

/**
 * @author: stone
 * @program: SSM_Demo
 * @description:
 * @date: 2021-11-18 11:50:52
 */
@Controller
@RequestMapping("member")
public class MemberController {
    private final MemberManageService memberManageService;

    public MemberController(MemberManageService memberManageService) {
        this.memberManageService = memberManageService;
    }

    @PostMapping("/login")
    @ResponseBody
    public Result checkLogin(Member member){
        System.out.println("controller:"+member.getUname());
        return memberManageService.checkLogin(member);
    }

    @PostMapping("/register")
    @ResponseBody
    public Result registerMember(Member member){
        return memberManageService.registerMember(member);
    }

}













//    //    @RequestMapping(value = "/add", method = RequestMethod.PUT)
//    @PostMapping("/add")
//    @ResponseBody
//    public Result addUser(Member member) {
//        System.out.println("获取待新增用户信息：" + member);
//        return userManageService.addUser(member);
//    }
//
//    @GetMapping("/find")
//    @ResponseBody
//    public Result findUser(Member member) {
//        System.out.println("获取待查询用户信息：" + member);
//        return userManageService.findUser(member);
//    }
//
//    @PutMapping("/update")
//    @ResponseBody
//    public Result updateUserInfo(Member member) {
//        System.out.println("获取待更新用户信息：" + member);
//        return userManageService.updateUserInfo(member);
//    }
//
//    @DeleteMapping("/delete")
//    @ResponseBody
//    public Object deleteUser(Member member) {
//        System.out.println("获取待删除用户信息：" + member);
//        return userManageService.deleteUser(member);
//    }
