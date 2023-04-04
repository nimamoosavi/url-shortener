package com.snap.linkshortener.common.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pages<T> {
    private Long totalElement;
    private Integer pageSize;
    private Integer totalPages;
    private T object;
}
