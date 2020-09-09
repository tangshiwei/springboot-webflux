package com.webflux.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "p_user")
@Entity
public class PUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "groupId")
    private Long groupId;

    @Column(name = "parentId")
    private Long parentId;

    @Column(name = "merchantsId")
    private Long merchantsId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "passWord")
    private String passWord;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private Long status;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "createBy")
    private String createBy;

    @Column(name = "updateTime")
    private Date updateTime;

    @Column(name = "updateBy")
    private String updateBy;

} 