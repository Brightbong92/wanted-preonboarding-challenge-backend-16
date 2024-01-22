package com.wanted.preonboarding.ticket.infrastructure.repository;

import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.entity.PerformanceSeatInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PerformanceSeatRepository extends JpaRepository<PerformanceSeatInfo, UUID> {
    Optional<PerformanceSeatInfo> findByLineAndGateAndSeatAndRoundAndPerformanceId(
            char line, int gate, int seat, int round, UUID performanceId);
}
