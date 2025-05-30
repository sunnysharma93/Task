package com.webapp.webapp.mapper;

import com.webapp.webapp.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO member(email, password, username, created_at) VALUES(#{email}, #{password}, #{username}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Member member);

    @Select("SELECT * FROM member WHERE email = #{email} AND deleted = false")
    Optional<Member> findByEmail(String email);

    @Select("SELECT * FROM member WHERE id = #{id} AND deleted = false")
    Optional<Member> findById(Long id);

    @Update("UPDATE member SET password = #{password}, username = #{username} WHERE id = #{id}")
    int update(Member member);

    @Update("UPDATE member SET deleted = true WHERE id = #{id}")
    int delete(Long id);
}
