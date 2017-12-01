package com.highlander.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_RAIL")
public class Rail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rail_code")
    private Integer railCode;
    @Column(name = "rail_name")
    private String railName;
    @Column(name = "start_lon")
    private Integer startLon;
    @Column(name = "start_lat")
    private Integer startLat;
    @Column(name = "end_lon")
    private Integer endLon;
    @Column(name = "end_lat")
    private Integer endLat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRailName() {
        return railName;
    }

    public void setRailName(String railName) {
        this.railName = railName;
    }

    public Integer getRailCode() {
        return railCode;
    }

    public void setRailCode(Integer railCode) {
        this.railCode = railCode;
    }

    public Integer getStartLon() {
        return startLon;
    }

    public void setStartLon(Integer startLon) {
        this.startLon = startLon;
    }

    public Integer getStartLat() {
        return startLat;
    }

    public void setStartLat(Integer startLat) {
        this.startLat = startLat;
    }

    public Integer getEndLon() {
        return endLon;
    }

    public void setEndLon(Integer endLon) {
        this.endLon = endLon;
    }

    public Integer getEndLat() {
        return endLat;
    }

    public void setEndLat(Integer endLat) {
        this.endLat = endLat;
    }


}
