package com.example.divarknockoff.model;

import com.example.divarknockoff.greendao.UUIDConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.util.UUID;

@Entity
public class Account {
    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String userName;
    @Property(nameInDb = "password")
    private String password;

    @Convert(converter = UUIDConverter.class, columnType = String.class)
    private UUID accountId;

    @Generated(hash = 1312762215)
    public Account(Long id, String userName, String password, UUID accountId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.accountId = accountId;
    }
    @Generated(hash = 882125521)
    public Account() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UUID getAccountId() {
        return this.accountId;
    }
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
    
}
