package com.example.books_management.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    /**
     * 图书的剩余数量
     */
    @Column(name = "surplus_number")
    private String surplusNumber;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "book_price")
    private Double bookPrice;

    /**
     * 简介
     */
    @Column(name = "brief_introduction")
    private String briefIntroduction;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 封面
     */
    @Column(name = "cover_url")
    private String coverUrl;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return book_name
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * @param bookName
     */
    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    /**
     * 获取图书的剩余数量
     *
     * @return surplus_number - 图书的剩余数量
     */
    public String getSurplusNumber() {
        return surplusNumber;
    }

    /**
     * 设置图书的剩余数量
     *
     * @param surplusNumber 图书的剩余数量
     */
    public void setSurplusNumber(String surplusNumber) {
        this.surplusNumber = surplusNumber == null ? null : surplusNumber.trim();
    }

    /**
     * @return author_name
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * @param authorName
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    /**
     * @return book_price
     */
    public Double getBookPrice() {
        return bookPrice;
    }

    /**
     * @param bookPrice
     */
    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    /**
     * 获取简介
     *
     * @return brief_introduction - 简介
     */
    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    /**
     * 设置简介
     *
     * @param briefIntroduction 简介
     */
    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction == null ? null : briefIntroduction.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取封面
     *
     * @return cover_url - 封面
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * 设置封面
     *
     * @param coverUrl 封面
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }
}