package com.example.books_management.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_borrow_books")
public class BorrowBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 0续借1期限内未还2期限内归还3逾期归还4逾期未归还
     */
    private Short state;

    private Integer term;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 借书到期时间
     */
    @Column(name = "due_time")
    private Date dueTime;

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
     * @return book_id
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * @param bookId
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取1期限内未还2期限内归还3逾期归还4逾期未归还
     *
     * @return state - 1期限内未还2期限内归还3逾期归还4逾期未归还
     */
    public Short getState() {
        return state;
    }

    /**
     * 设置1期限内未还2期限内归还3逾期归还4逾期未归还
     *
     * @param state 1期限内未还2期限内归还3逾期归还4逾期未归还
     */
    public void setState(Short state) {
        this.state = state;
    }

    /**
     * @return term
     */
    public Integer getTerm() {
        return term;
    }

    /**
     * @param term
     */
    public void setTerm(Integer term) {
        this.term = term;
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
     * 获取借书到期时间
     *
     * @return due_time - 借书到期时间
     */
    public Date getDueTime() {
        return dueTime;
    }

    /**
     * 设置借书到期时间
     *
     * @param dueTime 借书到期时间
     */
    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }
}