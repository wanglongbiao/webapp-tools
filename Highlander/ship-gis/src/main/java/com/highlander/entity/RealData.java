package com.highlander.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_REALDATA")
public class RealData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "devid")
    private  String devId;

    @OneToOne
    @JoinColumn(name = "devid", referencedColumnName = "devid",insertable = false,updatable = false)
    private ShipInfo shipInfo;

    @Column(name = "devname")
    private String devName;

    @Column(name = "nlong")
    private Integer lon;

    @Column(name = "nlati")
    private Integer lat;

    @Column(name = "bat")
    private Integer bat;

    @Column(name = "postime")
    private Date postime;

    @Column(name = "type")
    private Integer type;

    @Column(name = "httime")
    private Date httime;

    @Column(name = "inregion")
    private Integer inregion;

    @Column(name = "termstate")
    private Integer termState;

    @Column(name = "signal")
    private Integer signal;

    @Column(name = "heading")
    private Integer heading;

    @Column(name = "course")
    private Integer course;

    @Column(name = "speed")
    private Integer speed;

    public Integer getSignal() {
        return signal;
    }

    public void setSignal(Integer signal) {
        this.signal = signal;
    }

    public Integer getHeading() {
        return heading;
    }

    public void setHeading(Integer heading) {
        this.heading = heading;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public Integer getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
        this.lon = lon;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getBat() {
        return bat;
    }

    public void setBat(Integer bat) {
        this.bat = bat;
    }

    public Date getPostime() {
        return postime;
    }

    public void setPostime(Date postime) {
        this.postime = postime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getHttime() {
        return httime;
    }

    public void setHttime(Date httime) {
        this.httime = httime;
    }

    public Integer getInregion() {
        return inregion;
    }

    public void setInregion(Integer inregion) {
        this.inregion = inregion;
    }

    public Integer getTermState() {
        return termState;
    }

    public void setTermState(Integer termState) {
        this.termState = termState;
    }

//    public String getDevid() {
//        return devid;
//    }
//
//    public void setDevid(String devid) {
//        this.devid = devid;
//    }

    public ShipInfo getShipInfo() {
        return shipInfo;
    }

    public void setShipInfo(ShipInfo shipInfo) {
        this.shipInfo = shipInfo;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }
}
