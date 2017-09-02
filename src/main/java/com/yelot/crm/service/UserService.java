package com.yelot.crm.service;

import com.yelot.crm.entity.User;
import com.yelot.crm.mapper.RoleMapper;
import com.yelot.crm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kee on 17/5/26.
 */
@Transactional
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    public User login(String username,String password){
        return userMapper.findByNameAndPassword(username,password);
    }

    public User find(Long id){
        return userMapper.find(id);
    }

    public void save(User user,String[]role){
        userMapper.save(user);
        saveUserRoles(user,role);
    }

    public void update(User user,String []role){
        userMapper.updateUser(user);
        saveUserRoles(user,role);
    }

    private void saveUserRoles(User user, String[] role) {
        roleMapper.deleteUserRoleByUserId(user.getId());
        if (role != null) {
            for (String roleId : role) {
                roleMapper.insertUserRole(user.getId(),Long.valueOf(roleId));
            }
        }

    }

    public void updatePassword(String password,Long id){
        userMapper.updatePassword(password,id);
    }

    public List<User> findByPage(Integer start, Integer size){
        return userMapper.findByPage(start,size);
    }

    public List<User> findByNameLike(String name){
        name = "%"+name + "%";
        return userMapper.findByNameLike(name);
    }

    public void updateAlive(Integer alive,Long id){
        userMapper.updateAlive(alive,id);
    }


    public void editPassword(Long id, String newPsdMd5) {
        userMapper.editPassword(id,newPsdMd5);
    }

    public int checkPassword(Long id, String psdMd5) {
        return userMapper.checkPassword(id,psdMd5);
    }
}
