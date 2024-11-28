package com.zyn.ticketorder.model;

import jakarta.persistence.*;

@Entity // 这个类表示一个数据库表
@Table(name = "matches") // 指定数据库表名
public class Match {

    @Id // 表示这个字段是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自动生成主键
    private Long id;

    private String time; // 比赛时间
    private String venue; // 比赛场地

    @ManyToOne // 多个比赛属于一个队伍（A）
    @JoinColumn(name = "team_a_id") // 外键列名
    private Team teamA;

    @ManyToOne // 多个比赛属于一个队伍（B）
    @JoinColumn(name = "team_b_id") // 外键列名
    private Team teamB;

    private String description; // 比赛描述

    // Getters 和 Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
