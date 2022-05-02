package com.fa.training.demo.enumeric;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusEnum {

    PENDING(1, "Pending"),
    REVIEWED(2, "Reviewed"),
    SUBMITTED(3, "Submitted"),
    CLOSED(4,"Closed"),
    APPROVED(5, "Approved"),
    REJECTED(6, "Rejected"),
    IN_PROGRESS(7, "In-Progress"),
    PUBLISHED(8, "Published");

    private Integer key;
    private String value;

}
