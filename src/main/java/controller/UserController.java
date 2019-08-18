package controller;

import model.User;
import model.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import service.impl.UserFormServiceImpl;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    Environment env;

    @Autowired
    UserService userService;

    @Autowired
    UserFormServiceImpl userFormService;

    @GetMapping("/users")
    public ModelAndView listUsers(@RequestParam("s") Optional<String> s, @PageableDefault(10) Pageable pageable){
        Page<User> users;
        if(s.isPresent()){
            users = userService.findAllByEmailContaining(s.get(), pageable);
        } else {
            users = userService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/user/list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/create-user")
    public ModelAndView showCreateUserForm(){
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("userForm", new UserForm());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView saveUser(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult){
        String fileName = userFormService.fileName(userForm);
        ModelAndView modelAndView = new ModelAndView("/user/create");
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        }
        User user = new User(userForm.getEmail(), userForm.getPassword(), fileName);
        try {
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("userForm", new UserForm());
        modelAndView.addObject("message", "New user created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView showEditUserForm(@PathVariable Integer id){
        User user = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/user/edit");;
        if(user != null) {
            UserForm userForm = new UserForm(user.getId(), user.getEmail(), user.getPassword(), user.getAvatar());
            modelAndView.addObject("userForm", userForm);
        }else {
            modelAndView.addObject("message", "User ID not exist");
        }
        return modelAndView;
    }

    @PostMapping("/edit-user")
    public ModelAndView updateUser(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("/user/edit");
        String fileName = userFormService.fileName(userForm);
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        }
        if (fileName.equals("")){
            fileName = userService.findById(userForm.getId()).getAvatar();
        }
        User user = new User(userForm.getEmail(), userForm.getPassword(), fileName);
        user.setId(userForm.getId());
        try {
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userForm.setImage(user.getAvatar());
        modelAndView.addObject("userForm", userForm);
        modelAndView.addObject("message", "User updated successfully ");
        return modelAndView;
    }

    @GetMapping("/delete-user/{id}")
    public ModelAndView showDeleteUserForm(@PathVariable Integer id){
        User user = userService.findById(id);
        if(user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/delete");
            modelAndView.addObject("user", user);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

    @PostMapping("/delete-user")
    public String deleteUser(@ModelAttribute("user") User user){
        userService.remove(user.getId());
        return "redirect:users";
    }

    @GetMapping("/view-user/{id}")
    public ModelAndView viewUser(@PathVariable("id") Integer id, @PageableDefault(10) Pageable pageable){
        User user = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/user/view");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/index")
    public String index() {
        return "user/index";
    }

    @GetMapping("/user")
    public String user(Principal principal) {
        // Get authenticated user name from Principal
        System.out.println(principal.getName());
        return "user/user";
    }

    @GetMapping("/admin")
    public String admin() {
        // Get authenticated user name from SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication().getName());
        return "user/admin";
    }
}
