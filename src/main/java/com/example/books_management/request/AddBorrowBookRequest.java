package com.example.books_management.request;

import lombok.Data;

@Data
public class AddBorrowBookRequest {

    private String bookName;

    private Integer term;

}
