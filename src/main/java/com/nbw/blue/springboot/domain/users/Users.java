package com.nbw.blue.springboot.domain.users;

import com.nbw.blue.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uid"}),
        @UniqueConstraint(columnNames = {"email"}),
})
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", columnDefinition = "varchar(10)", nullable = false)
    private String uid;

    @Column(name = "email", columnDefinition = "varchar(30)", nullable = false)
    private String email;

    @Column(name = "pwd", columnDefinition = "varchar(15)", nullable = false)
    private String pwd;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday", columnDefinition = "varchar(10)")
    private String birthday;

    @Column(name = "gender", columnDefinition = "varchar(1)")
    private String gender;

    @Column(name = "local", columnDefinition = "varchar(30)")
    private String local;

    @Column(name = "job", columnDefinition = "varchar(20)")
    private String job;

    @Column(name = "interest")
    private String interest;

    @Column(name = "income")
    private Integer income;

    @Column(name = "phone", columnDefinition = "varchar(13)")
    private String phone;

    @Column(name = "sign_type")
    private String signType;

    @Column(name = "appdoc_index")
    private Long appdoc_index;

    @Column(name = "terms_agree")
    private String terms_agree;

    @Builder
    public Users(String uid,
                 String email,
                 String pwd,
                 String name,
                 String birthday,
                 String gender,
                 String local,
                 String job,
                 String interest,
                 Integer income,
                 String phone,
                 String signType,
                 Long appdoc_index,
                 String terms_agree) {
        this.uid = uid;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.local = local;
        this.job = job;
        this.interest = interest;
        this.income = income;
        this.phone = phone;
        this.signType = signType;
        this.appdoc_index = appdoc_index;
        this.terms_agree = terms_agree;
    }

    public void update(String name,
                       String birthday,
                       String gender,
                       String local,
                       String job,
                       String interest,
                       Integer income,
                       String phone,
                       Long appdoc_index) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.local = local;
        this.job = job;
        this.interest = interest;
        this.income = income;
        this.phone = phone;
        this.appdoc_index = appdoc_index;
    }
}
