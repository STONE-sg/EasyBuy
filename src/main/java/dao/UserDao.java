package dao;

import entity.Member;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    int addUser(Member member);
    List<Member> findUsers(Member member);
    int updateUserInfo(Member member);
    int deleteUser(Member member);
}
