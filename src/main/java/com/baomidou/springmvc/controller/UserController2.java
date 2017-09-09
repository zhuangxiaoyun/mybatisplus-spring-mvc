package com.baomidou.springmvc.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springmvc.common.utils.StringUtil;
import com.baomidou.springmvc.model.system.User;
import com.baomidou.springmvc.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class UserController2 extends BaseController {

    private final IUserService userService;

    @Autowired
    public UserController2(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public ModelAndView index(ModelAndView modelAndView, @RequestParam(value = "param", required = false) String param) {
        modelAndView.setViewName("index");
        if (StringUtil.isNotEmpty(param)) {
            modelAndView.addObject("userList", userService.selectList(new EntityWrapper<User>().and("name={0}", param)));
        } else {
            modelAndView.addObject("userList", userService.selectList(null));
        }
        Page<User> page = new Page<User>();
        page = userService.selectPage(page);
        modelAndView.addObject("page", page);

        return modelAndView;
    }

    
    @ResponseBody
    @RequestMapping("save")
    public Object save(User user) {
        if (user.getId() == null) {
            return userService.insert(user) ? renderSuccess("添加成功") : renderError("添加失败");
        } else {
            return userService.updateById(user) ? renderSuccess("修改成功") : renderError("修改失败");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam(value = "id", required = false) Long id) {
        return userService.deleteById(id) ? renderSuccess("删除成功") : renderError("删除失败");
    }

    public static void main(String[] args) {

    }
}
