package com.pachico.arithmetic.adapter.in.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationRequest {
    public BigInteger x;
    public BigInteger y;
}