package com.yelot.crm.mapper;

import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 参数传递：如果是一个参数，不需要@Param 注解，如果多余1个参数，则需要@Param
 * Created by yelot on 17/5/15.
 */
@Mapper
@Repository
public interface UserMapper {


    @Select("select * from t_user where name = #{username} and password = #{password}")
    User findByNameAndPassword(@Param("username") String username, @Param("password") String password);


    /**
     * 查找一个用户详情
     * @param id
     * @return
     */
//    @Select("select id,name,password,realname,phone,emp_no,is_alive,create_at,update_at,shop_id " +
//            "from t_user where id = #{id}")
    User find(Long id);


    /**
     * 新建
     * @param user
     */

//    @Insert("insert into t_user(name,password,realname,phone,emp_no,is_alive,create_at,update_at,shop_id) " +
//            "values(#{name},#{password},#{realname},#{phone},#{emp_no},#{is_alive},#{create_at},#{update_at},#{shop_id})")
    void save(User user);

    /**
     * 更新用户
     * @param user
     */
//    @Update("update t_user set name = #{name},password=#{password},realname=#{realname},phone=#{phone},emp_no=#{emp_no}," +
//            "is_alive=#{is_alive},create_at=#{create_at},update_at=#{update_at} where id = #{id}")
//    void update(User user);

    void updateUser(User user);

    /**
     * 更新用户密码
     * @param password
     */
    @Update("update t_user set password = #{password} where id = #{id}")
    void updatePassword(@Param("password") String password,@Param("id") Long id);

    /**
     * 分页查找
     * @param start
     * @param size
     * @return
     */
    @Select("select * from t_user limit #{start},#{size}")
    List<User> findByPage(@Param("start") Integer start, @Param("size") Integer size);

    /**
     * 通过名称模糊查找
     * @param name
     * @return
     */
    @Select("select id,name,password from t_user where name like #{name}")
    List<User> findByNameLike(String name);


    /**
     * 删除用户，修改用户状态，修改用户名
     * @param isAlive
     * @param id
     */
    @Update("update t_user set is_alive = #{is_alive},name = concat(name,'_DeleteStatus',now()) where id = #{id}")
    void updateAliveAndName(@Param("is_alive") Integer isAlive,  @Param("id") Long id);


    /**
     * 删除为更新该记录状态is_alive = 0
     * @Param("is_alive") Integer is_alive,@Param("id")
     * @param is_alive
     * @param id
     */
    @Update("update t_user set is_alive = 0 where id = #{id}")
    void updateAlive(@Param("is_alive") Integer is_alive, @Param("id") Long id);

    @Select("select * from t_user where name = #{username}")
    User findByUsername(String username);

    @Select("select count(*) from t_user where is_alive = 1")
    int countBySearch(String extra_search);

    List<User> findBySearch(@Param("pageHelper") PageHelper pageHelper);

    int checkPassword(@Param("id") Long id, @Param("psdMd5") String psdMd5);

    void editPassword(@Param("id") Long id, @Param("newPsdMd5") String newPsdMd5);

    List<User> findRepairUserList();

    List<User> findUserByType(@Param("userType") String userType, @Param("shopId") String shopId);
}
