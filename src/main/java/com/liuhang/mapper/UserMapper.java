package com.liuhang.mapper;

import com.liuhang.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by liuhang on 2017/7/19.
 */
@Mapper
public interface UserMapper {
    String TABLE_NAME = "user";
    String INSET_FIELDS = " name, password, salt, head_url ";
    String SELECT_FIELDS = " id, name, password, salt, head_url";

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, "from", TABLE_NAME, "where id=#{id}"})
    User selectUserById(int id);

    @Select({"select ", SELECT_FIELDS, "from", TABLE_NAME, "where name=#{name}"})
    User selectUserByName(String name);

}
