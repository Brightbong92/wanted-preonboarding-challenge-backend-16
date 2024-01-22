package com.wanted.preonboarding.ticket.application;

import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.domain.entity.Performance;
import com.wanted.preonboarding.ticket.domain.entity.PerformanceSeatInfo;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import com.wanted.preonboarding.ticket.infrastructure.repository.PerformanceRepository;
import com.wanted.preonboarding.ticket.infrastructure.repository.PerformanceSeatRepository;
import com.wanted.preonboarding.ticket.infrastructure.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketSeller {
    private final PerformanceRepository performanceRepository;
    private final ReservationRepository reservationRepository;
    private final PerformanceSeatRepository performanceSeatRepository;
    private long totalAmount = 0L;

    public List<PerformanceInfo> getAllPerformanceInfoList() {
        return performanceRepository.findByIsReserve("enable").stream().map(PerformanceInfo::of).toList();
    }

    public PerformanceInfo getPerformanceInfoDetail(String name) {
        return PerformanceInfo.of(performanceRepository.findByName(name));
    }

    public ReserveInfo getReserveInfo(ReserveInfo reserveInfo) {
        Performance info = performanceRepository.findById(reserveInfo.getPerformanceId()).orElseThrow(EntityNotFoundException::new);
        Optional<PerformanceSeatInfo> seatInfo = this.getPerformanceSeatInfo(
                PerformanceSeatInfo.builder()
                .line(reserveInfo.getLine())
                .gate(reserveInfo.getGate())
                .seat(reserveInfo.getSeat())
                .round(reserveInfo.getRound())
                .performanceId(reserveInfo.getPerformanceId())
                .build()
        );

        if (seatInfo.isEmpty()) throw new EntityNotFoundException();


        return ReserveInfo.builder()
                .round(reserveInfo.getRound()) // 회차
                .performanceId(info.getId()) // 공연ID
                .performanceName(info.getName()) // 공연명
                .seat(reserveInfo.getSeat()) // 좌석정보
                .reservationName(reserveInfo.getReservationName()) // 이름
                .amount(reserveInfo.getAmount()) // 결제금액 (reserve메소드에서 미리 계산한다)
                .line(seatInfo.get().getLine())
                .gate(reserveInfo.getGate())
                .reservationPhoneNumber(reserveInfo.getReservationPhoneNumber()) // 연락처
                .build();
    }

    public Optional<PerformanceSeatInfo> getPerformanceSeatInfo(PerformanceSeatInfo performanceSeatInfo) {
       return Optional.ofNullable(performanceSeatRepository.findByLineAndGateAndSeatAndRoundAndPerformanceId
                (
                        performanceSeatInfo.getLine(),
                        performanceSeatInfo.getGate(),
                        performanceSeatInfo.getSeat(),
                        performanceSeatInfo.getRound(),
                        performanceSeatInfo.getPerformanceId()
                ).orElseThrow(EntityNotFoundException::new));
    }


    public boolean reserve(ReserveInfo reserveInfo) {
        log.info("reserveInfo ID => {}", reserveInfo.getPerformanceId());
        Performance info = performanceRepository.findById(reserveInfo.getPerformanceId()).orElseThrow(EntityNotFoundException::new);
        String enableReserve = info.getIsReserve();
        if (enableReserve.equalsIgnoreCase("enable")) {
            // 1. 결제
            int price = info.getPrice();
            reserveInfo.setAmount(reserveInfo.getAmount() - price);
            // 2. 예매 진행
            reservationRepository.save(Reservation.of(reserveInfo));

            return true;
        } else {
            return false;
        }


    }

}
