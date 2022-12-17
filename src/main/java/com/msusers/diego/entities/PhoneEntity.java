package com.msusers.diego.entities;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "phone" ,uniqueConstraints=
@UniqueConstraint(columnNames={"number", "cityCode","contryCode"}))
@Getter
@Setter

public class PhoneEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String number;
    private String cityCode;
    private String contryCode;

    @ManyToOne
    @JoinColumn(name = "FK_USER", nullable = false, updatable = false)
    private UserEntity user;
}
