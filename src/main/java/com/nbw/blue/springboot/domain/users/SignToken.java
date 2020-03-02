package com.nbw.blue.springboot.domain.users;

import com.nbw.blue.springboot.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "sign_token")
public class SignToken extends BaseTimeEntity {

    @Id
    @Column(name = "uid", columnDefinition = "varchar(10)", nullable = false)
    private String uid;

    @Column(name = "token", columnDefinition = "varchar(100)", unique = true, nullable = false)
    private String token;


}
