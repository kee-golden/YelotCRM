package com.yelot.crm.mapper;

import com.yelot.crm.entity.Shop;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kee on 17/5/26.
 */
@Mapper
@Repository
public interface ShopMapper {

    @Select("select * from t_shop where id = #{id}")
    Shop find(Long id);

    @Select("select * from t_shop")
    List<Shop> findAll();

    @Insert("insert into t_shop(name,address,phone,create_at,update_at) " +
            "values(#{name},#{address},#{phone},#{create_at},#{update_at})")
    void save(Shop shop);

    @Delete("delete from t_shop where id = #{id}")
    void delete(Long id);

    @Update("update t_shop set name=#{name},address=#{address},phone=#{phone},is_alive=#{is_alive},update_at=#{update_at}" +
            " where id = #{id}")
    void update(Shop shop);


    /**
     * 门店的名称唯一,已建立索引，unique
     * @param name
     * @return
     */
    @Select("select * from t_shop where name = #{name}")
    Shop findByName(String name);

    @Select("select * from t_shop where name = #{name} and id <> #{id}")
    Shop findByNameAndId(@Param("name") String name, @Param("id") Long id);

    @Update("update t_shop set is_alive = #{alive} and id = #{id}")
    void updateAlive(@Param("alive") Integer alive, @Param("id") Long id);

    @Select("select * from t_shop where #{start},#{size}")
    List<Shop> findByPage(@Param("start") Integer start, @Param("size") Integer size);
}
