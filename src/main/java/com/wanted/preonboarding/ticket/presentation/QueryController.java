package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("query")
@RequiredArgsConstructor
public class QueryController {
    private final TicketSeller ticketSeller;

    @GetMapping("/all/performance")
    public ResponseEntity<ResponseHandler<List<PerformanceInfo>>> getAllPerformanceInfoList() {
        System.out.println("getAllPerformanceInfoList");
        return ResponseEntity
                .ok()
                .body(ResponseHandler.<List<PerformanceInfo>>builder()
                        .message("Success")
                        .statusCode(HttpStatus.OK)
                        .data(ticketSeller.getAllPerformanceInfoList())
                        .build()
                );
    }

    @GetMapping("/detail/performance")
    public ResponseEntity<ResponseHandler<PerformanceInfo>> getPerformanceInfoDetail(@RequestParam(required = true) String name) {
        System.out.println("getPerformanceInfoDetail");
        return ResponseEntity
                .ok()
                .body(ResponseHandler.<PerformanceInfo>builder()
                        .message("Success")
                        .statusCode(HttpStatus.OK)
                        .data(ticketSeller.getPerformanceInfoDetail(name))
                        .build()
                );
    }
}
