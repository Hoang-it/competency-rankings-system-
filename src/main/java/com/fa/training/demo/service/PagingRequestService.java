package com.fa.training.demo.service;

import com.fa.training.demo.entities.paging.PagingRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagingRequestService<T> {

    public PagingRequest<T> request(List<T> list, long recordsTotal, long recordsFiltered, int start, int draw) {
        return new PagingRequest<T>(list, recordsTotal, recordsFiltered, start, draw);
    }
}
