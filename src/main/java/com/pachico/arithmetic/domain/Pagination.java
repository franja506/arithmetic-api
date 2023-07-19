package com.pachico.arithmetic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination {
    private Integer page;
    private Integer size;
}
