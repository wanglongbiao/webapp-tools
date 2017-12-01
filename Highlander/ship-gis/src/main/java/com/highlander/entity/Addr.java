package com.highlander.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_ADDR")
public class Addr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "MANAGEMENT_ID")
    private Long managementId;

    @Column(name = "ADDR_NAME")
    private String addrName;

    @Column(name = "ADDR_LEVEL")
    private Integer addrLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getManagementId() {
        return managementId;
    }

    public void setManagementId(Long managementId) {
        this.managementId = managementId;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    public Integer getAddrLevel() {
        return addrLevel;
    }

    public void setAddrLevel(Integer addrLevel) {
        this.addrLevel = addrLevel;
    }
}
