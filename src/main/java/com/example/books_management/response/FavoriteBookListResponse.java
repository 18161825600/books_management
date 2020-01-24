package com.example.books_management.response;

import lombok.Data;

import java.util.Date;

@Data
public class FavoriteBookListResponse {

    private String bookName;

    private String authorName;

    private String coverUrl;

    private Date createTime;

    private Integer total;
}
