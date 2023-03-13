package controller;

import entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.Result;
import service.UserService;

/**
 * @author: stone
 * @program: EasyBuy
 * @description:
 * @date: 2021-12-29 20:37:03
 */
@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseBody
    public Result addUser(Member member) {
        System.out.println("获取待新增用户信息：" + member);
        return userService.addUser(member);
    }

    @GetMapping("/find")
    @ResponseBody
    public Result findUsers(Member member) {
        System.out.println("获取待查询用户信息：" + member);
        return userService.findUsers(member);
    }

    @PutMapping("/update")
    @ResponseBody
    public Result updateUserInfo(Member member) {
        System.out.println("获取待更新用户信息：" + member);
        return userService.updateUserInfo(member);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Result deleteUser(Member member) {
        System.out.println("获取待删除用户信息：" + member);
        return userService.deleteUser(member);
    }
}
