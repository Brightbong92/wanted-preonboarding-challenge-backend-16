package com.wanted.preonboarding.ticket.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceSeatInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "BINARY(16)", nullable = false, name = "performance_id")
    private UUID performanceId;
    @Column(nullable = false)
    private int round;
    @Column(nullable = false)
    private int gate;
    @Column(nullable = false)
    private char line;
    @Column(nullable = false)
    private int seat;
    @Column(nullable = false, name = "is_reserve", columnDefinition = "varchar(255) default 'disable'")
    private String isReverse;
}
