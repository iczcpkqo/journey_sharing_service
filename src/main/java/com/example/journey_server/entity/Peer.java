package com.example.journey_server.entity;

public class Peer {

    String email;

    Integer gender;

    Integer age;

    Double score;

    Double longitude;

    Double latitude;

    Double dLongtitude;

    Double dLatitude;

    Long startTime;

    Long endTime;

    Integer limit;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getdLongtitude() {
        return dLongtitude;
    }

    public void setdLongtitude(Double dLongtitude) {
        this.dLongtitude = dLongtitude;
    }

    public Double getdLatitude() {
        return dLatitude;
    }

    public void setdLatitude(Double dLatitude) {
        this.dLatitude = dLatitude;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Peer(String email, Integer gender, Integer age, Double score, Double longitude, Double latitude, Double dLongtitude, Double dLatitude, Long startTime, Long endTime, Integer limit) {
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.score = score;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dLongtitude = dLongtitude;
        this.dLatitude = dLatitude;
        this.startTime = startTime;
        this.endTime = endTime;
        this.limit = limit;
    }
}
