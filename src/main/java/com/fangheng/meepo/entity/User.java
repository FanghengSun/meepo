package com.fangheng.meepo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * @author FanghengSun on 2020/10/27
 */

@Data
@TableName(value = "USER")
@Accessors(chain = true)
public class User {

    /**
     * Primary Key
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * Username
     */
    @NotBlank(message = "Username is mandatory")
    private String userName;

    /**
     * Password
     */
    @Size(min=8, max=30, message = "Password length should be between 8 and 30")
    private String password;

    /**
     * Email
     */
    @NotBlank(message = "Email is mandatory")
    private String email;

    /**
     * Phone
     */
    private String phone;

    /**
     * Full Name
     */
    private String fullName;
}
