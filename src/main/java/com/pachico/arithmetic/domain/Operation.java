package com.pachico.arithmetic.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operations")
public class Operation {

    @Id
    private UUID id;
    private String method;
    private String uri;
    private String request;
    private String response;
    private OffsetDateTime datetime;
}
