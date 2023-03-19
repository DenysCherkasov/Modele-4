package com.cherkasov.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatsDTO {
    private long countProducts;
    private long brokenMicrocircuits;
    private long producedFuel;
    private long usedFuel;
}