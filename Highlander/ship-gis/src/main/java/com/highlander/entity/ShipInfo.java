package com.highlander.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shipinfo")
public class ShipInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "unit")
    private String unit;
    @Column(name = "type")
    private String type;
    @Column(name = "name")
    private String name;
    @Column(name = "devid")
//    @OneToOne(mappedBy="shipInfo")
    private String devid;
    @Column(name = "owner")
    private String owner;
    @Column(name = "tele")
    private String tele;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "material")
    private String material;
    @Column(name = "tonnage")
    private String tonnage;
    @Column(name = "power")
    private String power;
    @Column(name = "region")
    private String region;
    @Column(name = "port")
    private String port;
    @Column(name = "card")
    private String card;
    @Column(name = "demo")
    private String demo;
    @Column(name = "addr")
    private String addr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getTonnage() {
        return tonnage;
    }

    public void setTonnage(String tonnage) {
        this.tonnage = tonnage;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
