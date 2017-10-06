package com.yelot.crm.controller;

import com.yelot.crm.Util.ResponseData;
import com.yelot.crm.Util.ResultData;
import com.yelot.crm.Util.UserUtil;
import com.yelot.crm.base.PageHelper;
import com.yelot.crm.entity.Role;
import com.yelot.crm.entity.Shop;
import com.yelot.crm.entity.User;
import com.yelot.crm.enums.AliveStatus;
import com.yelot.crm.mapper.RoleMapper;
import com.yelot.crm.mapper.ShopMapper;
import com.yelot.crm.mapper.UserMapper;
import com.yelot.crm.service.UserService;
import com.yelot.crm.vo.RoleVo;
import com.yelot.crm.vo.Table;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by kee on 17/5/14.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ShopMapper shopMapper;

    @RequestMapping("test")
    public String test(){
        return "hello world";
    }

    @RequestMapping("index")
    public String index(){
        return "user/user_index";
    }

    @RequestMapping("list")
    public String list(){
        return "user/user_list";
    }

    @ResponseBody
    @RequestMapping("list-data")
    public Table userListData(Model model,
                               @RequestParam(value = "extra_search", defaultValue = "")String extra_search,
                               @RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "length", defaultValue = "10") int length) {

        PageHelper pageHelper = new PageHelper();
        pageHelper.setOffset(start);
        pageHelper.setSize(length);
        int pageCount = userMapper.countBySearch(extra_search);
        List<User> userList = userMapper.findBySearch(pageHelper);

        model.addAttribute("userList", userList);
        return new Table(pageCount, pageCount, userList);
    }

    @RequestMapping("add")
    public String add(Model model) {
        User user = new User();

        List<Role> roles = roleMapper.findAll();
        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("bean", user);
        model.addAttribute("shopList", shopList);

        return "user/user_edit";
    }

    /**
     * 编 管 员
     *
     * @return .
     */
    @RequestMapping("edit")
    public String edit(Model model, Long id) {
        User user = userMapper.find(id);
        List<RoleVo> roles = roleMapper.findAllByUserId(id);
        List<Shop> shopList = shopMapper.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("bean", user);
        model.addAttribute("shopList", shopList);
        return "user/user_edit";
    }



    @RequestMapping("find")
    public User find(Long id){
        return userService.find(id);
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultData save(User user, Long shopId, String[] role){

//        user.setPassword("1234");
        if(user.getId() == null){
            String newPassword = "123456";
            String psdMd5 = new Md5Hash(newPassword).toString();
            user.setPassword(psdMd5);
            user.setCreate_at(new Date());
            user.setUpdate_at(new Date());
            user.setIs_alive(AliveStatus.ALIVE.getCode());
            user.setShop_id(shopId);
            userService.save(user,role);
        }else {//更新user
            user.setShop_id(shopId);
            user.setUpdate_at(new Date());
            userService.update(user,role);
        }

        return ResultData.ok();
    }

    /**
     * validate 异步验证用户名是否存在
     * 注册验证
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("check-username")
    public String checkUsername(String name, HttpServletResponse response) throws IOException {
        User user = userMapper.findByUsername(name);
        if (user == null) {//表示可用，验证成功通过,没用用户注册过
            response.getWriter().write("true");
        } else {
            response.getWriter().write("false");
        }
        return null;
    }

    @RequestMapping("user-delete")
    @ResponseBody
    public ResultData delete(Long id){
        try {
            userMapper.updateAliveAndName(0,id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.errorRequest();
        }
        return ResultData.ok();
    }

    /**
     * 更新用户状态is_alive = 0
     * @param id
     * @return
     */
    @RequestMapping("update-alive")
    public ResponseData updateAlive(Integer alive,Long id){
        userService.updateAlive(alive,id);
        return new ResponseData(ResponseData.SUCCESS,ResponseData.SUCCESS_MESSAGE);

    }

    /**
     * 查询所有用户，包含已删除状态的用户
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("find-by-page")
    public List<User> findByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        if(page <= 0){
            page = 1;
        }
        Integer start = (page - 1) * size;
        return userService.findByPage(start,size);
    }

    @RequestMapping("find-by-name")
    public List<User> findByNameLike(String name){
        return userService.findByNameLike(name);
    }

    @ResponseBody
    @RequestMapping("reset-password")
    public ResultData resetPassword(Long id) {

        String newPassword = "123456";
        String psdMd5 = new Md5Hash(newPassword).toString();
        if(id != null){
            userService.updatePassword(psdMd5,id);
        }
        return ResultData.ok();
    }

    @ResponseBody
    @RequestMapping("edit-psd")
    public ResultData editPassword(String oldPsd, String newPsd) {


        if (!oldPsd.equals(newPsd)) {//修改的密码与原密码不相同
            String newPsdMd5 = new Md5Hash(newPsd).toString();
            userService.editPassword(UserUtil.getCurrentUser().getId(), newPsdMd5);

        } else {
            return ResultData.serverError();
        }

        return ResultData.ok();

    }

    @RequestMapping("check-password")
    public String checkPassword(String password, HttpServletResponse response) throws IOException {
        String psdMd5 = new Md5Hash(password).toString();
        int hasResult = userService.checkPassword(UserUtil.getCurrentUser().getId(), psdMd5);
        if (hasResult > 0) {//表示可用，密码是正确的
            response.getWriter().write("true");
        } else {
            response.getWriter().write("false");
        }
        return null;
    }



    /**
     * to-修改密码
     *
     * @return
     */
    @RequestMapping("to-edit-psd")
    public String toEditPassword() {
        return "user/user_edit_password";

    }


}
