package com.example.books_management.request;

import lombok.Data;

@Data
public class updateBorrowBookTermRequest {

    private Long borrowId;

    private Integer term;
}
