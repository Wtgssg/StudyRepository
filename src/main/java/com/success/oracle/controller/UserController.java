package com.success.oracle.controller;

import com.success.oracle.annotation.RequiresPermission;
import com.success.oracle.entity.User;
import com.success.oracle.repository.UserRepository;
import com.success.oracle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
//import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequiresPermission
    @GetMapping("/all")
    public Iterable<User> getAllUser(){
        Sort sort = Sort.by(Sort.Direction.ASC,"username");
        return userRepository.findAll(sort);
    }

    @GetMapping("/ByUsername")
    public User getUserByUserName(@RequestParam String username){
        Sort sort = Sort.by("username");
        return userRepository.findByUsername(username,sort);
    }

    @GetMapping("/Byid")
    public String getUserNameById(@RequestParam("id") Long id){
        return userRepository.findUserById(id);
    }


    @GetMapping("/likeUsername")
    public User findUserLikeusername(@RequestParam("username") String username){
        return userRepository.findUserLikeusername(username);
    }

//    @GetMapping("/userPage")
//    public Page<User> findUserPage(@RequestParam(value = "pagenum",defaultValue = "0") int pagenum,
//                                   @RequestParam(value = "pagesize",defaultValue = "5") int pagesize ){
//        Pageable pageable = PageRequest.of(pagenum,pagesize);
//        return userRepository.findUserPage(pageable);
//    }

    @GetMapping("/userPage")
    public Page<User> findUserPage(@PageableDefault(page = 0, size = 5) Pageable pageable){
        return userService.findUserPage(pageable);
    }

    @Transactional
    @PostMapping("/save")
    public void saveUser(@RequestBody User user){
        userRepository.save(user);
    }

    @GetMapping("/userse")
    public List<User> finduserSe(@RequestParam("id") Long id){
        return userService.findUserSe(id);
    }
}
