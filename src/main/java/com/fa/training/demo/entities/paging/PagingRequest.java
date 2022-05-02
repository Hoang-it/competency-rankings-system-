package com.fa.training.demo.entities.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingRequest<T> {

    private List<T> data;
    private long recordsTotal;
    private long recordsFiltered;
    private int start;
    private int draw;

}
