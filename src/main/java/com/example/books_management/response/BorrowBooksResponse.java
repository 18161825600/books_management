package com.example.books_management.response;

import lombok.Data;

import java.util.Date;

@Data
public class BorrowBooksResponse {

    private String bookName;

    private String authorName;

    private Double bookPrice;

    private String coverUrl;

    private String nickName;

    private Short state;

    private Integer term;

    private Date createTime;

    private Integer total;
}
