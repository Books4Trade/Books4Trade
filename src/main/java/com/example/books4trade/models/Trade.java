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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trade")
    private List<TradeItem> tradeItems;

    public Trade() {
    }

    public Trade(long id, List<TradeItem> tradeItems) {
        this.id = id;
        this.tradeItems = tradeItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TradeItem> getTradeItems() {
        return tradeItems;
    }

    public void setTradeItems(List<TradeItem> tradeItems) {
        this.tradeItems = tradeItems;
    }
}
