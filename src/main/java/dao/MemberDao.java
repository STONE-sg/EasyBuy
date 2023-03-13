package dao;

import entity.Member;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MemberDao {
    int addMember(Member member);

    Member findMemberByUname(Member member);

    int updateMemberInfo(Member member);

    int deleteMember(Member member);
}



















//    List<Member> findAll();
//
//    Member findByUname(String uname);
//
//    int dynamicUpdate(Member member);
//
//
//    int deleteByPrimaryKey(Integer memberId);
//
//    int addUser(Member record);
//
//    int insertSelective(Member record);
//
//    Member selectByPrimaryKey(Integer memberId);
//
//    int updateUserInfo(Member record);
//
//    int updateByPrimaryKey(Member record);
//
//    int deleteUser(Member member);
//
//    List<Member> findUser(Member member);
