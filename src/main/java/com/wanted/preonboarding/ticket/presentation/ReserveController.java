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
    public boolean reservation(@RequestBody ReserveInfo reserveInfo) {
        System.out.println("reservation" + reserveInfo.toString());



        return true;

//        return ticketSeller.reserve(ReserveInfo.builder()
//                .performanceId(UUID.fromString("4438a3e6-b01c-11ee-9426-0242ac180002"))
//                .reservationName("유진호")
//                .reservationPhoneNumber("010-1234-1234")
//                .reservationStatus("reserve")
//                .amount(200000)
//                .round(1)
//                .line('A')
//                .seat(1)
//                .build()
//        );
    }
}
