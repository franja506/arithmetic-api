package com.pachico.arithmetic.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operations")
public class Operation {

    @Id
    private UUID id;
    private BigDecimal number1;
    private BigDecimal number2;
    private BigDecimal percentage;
    private BigDecimal result;
    private OffsetDateTime datetime;
}
