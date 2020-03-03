package com.nbw.blue.springboot.domain.signstatus;

import com.nbw.blue.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "sign_status", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uid"}),
})
public class SignStatus extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", columnDefinition = "varchar(10)", nullable = false)
    private String uid;

    @Column(name = "sign_status", nullable = false)
    private boolean sign_status;

    @Builder
    public SignStatus(String uid, boolean sign_status) {
        this.uid = uid;
        this.sign_status = sign_status;
    }

    public void update(boolean sign_status) {
        this.sign_status = sign_status;
    }
}
