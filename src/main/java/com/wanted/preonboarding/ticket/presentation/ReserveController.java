package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.ticket.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {
    private final TicketSeller ticketSeller;

    @PostMapping("/check") // 예약 조회 시스템
    public ResponseEntity<ResponseHandler<?>> check() {
        return ResponseEntity
                .ok()
                .body(ResponseHandler.builder()
                        .statusCode(HttpStatus.OK)
                        .message("Success")
                        .build()
                );
    }

    @PostMapping("/")
    public ResponseEntity<ResponseHandler<ReserveInfo>> reservation(@RequestBody ReserveInfo reserveInfo) {
        System.out.println("reservation" + reserveInfo.toString());
        boolean isSuccess = ticketSeller.reserve(reserveInfo);

        if (isSuccess) {
            return ResponseEntity
                    .ok()
                    .body(
                        ResponseHandler.<ReserveInfo>builder().message("Success")
                                .statusCode(HttpStatus.OK)
                                .data(ticketSeller.getReserveInfo(reserveInfo))
                                .build());
        } else {
            return ResponseEntity
                    .ok()
                    .body(
                        ResponseHandler.<ReserveInfo>builder().message("")
                                .statusCode(HttpStatus.EXPECTATION_FAILED)
                                .data(null)
                                .build());
        }
    }
}
