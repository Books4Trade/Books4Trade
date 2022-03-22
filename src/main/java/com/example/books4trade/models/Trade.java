package com.example.books4trade.models;


import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="trades")

public class Trade {
//    Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private TradeItem item1;

    @OneToOne
    private TradeItem item2;

    //  CONSTRUCTORS
    public Trade() {
    }

    public Trade(long id) {
        this.id = id;
    }

    public Trade(long id, TradeItem item1, TradeItem item2) {
        this.id = id;
        this.item1 = item1;
        this.item2 = item2;
    }

    //  GETTERS/SETTERS
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public TradeItem getItem1() {
        return item1;
    }
    public void setItem1(TradeItem item1) {
        this.item1 = item1;
    }
    public TradeItem getItem2() {
        return item2;
    }
    public void setItem2(TradeItem item2) {
        this.item2 = item2;
    }
}
