package com.fangheng.meepo.controller;

import com.fangheng.meepo.entity.User;
import com.fangheng.meepo.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Fangheng Sun on 2020/10/27
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * 插入或更新user
     * @param user
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity<String> saveOrUpdate(@Valid @RequestBody User user) {
        userService.saveOrUpdate(user);
        return ResponseEntity.ok("User is valid");
    }

    /**
     * 查询user列表
     * @return
     */
    @GetMapping("/list")
    public List<User> list() {
        List<User> list = userService.list();
        list.forEach(u -> LOGGER.info("当前用户数据:{}", u));
        return list;
    }

    /**
     * 根据id删除user
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Boolean delete(@RequestParam String id) {
        return userService.removeById(id);
    }

    /**
     * Handle http error
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
