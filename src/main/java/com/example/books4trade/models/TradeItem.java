package com.example.books4trade.models;

import javax.persistence.*;

@Entity
@Table(name = "trade_items")
public class TradeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private boolean confirm_sent;

    @ManyToOne
    @JoinColumn(name = "sentBook_id")
    private OwnedBook ownedBook;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "trade_id", nullable = false)
    private Trade trade;

    public TradeItem() {
    }

    public TradeItem(long id, boolean confirm_sent, OwnedBook ownedBook, User user, Trade trade) {
        this.id = id;
        this.confirm_sent = confirm_sent;
        this.ownedBook = ownedBook;
        this.user = user;
        this.trade = trade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isConfirm_sent() {
        return confirm_sent;
    }

    public void setConfirm_sent(boolean confirm_sent) {
        this.confirm_sent = confirm_sent;
    }

    public OwnedBook getOwnedBook() {
        return ownedBook;
    }

    public void setOwnedBook(OwnedBook ownedBook) {
        this.ownedBook = ownedBook;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }
}
