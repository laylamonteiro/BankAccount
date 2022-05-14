package com.laylamonteiro.bankaccount.dao;

import com.laylamonteiro.bankaccount.entity.Balance;
import com.laylamonteiro.bankaccount.entity.Transaction;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BalanceDAO {

    private final SqlSession sqlSession;

    public BalanceDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Balance> findAll() {
        return this.sqlSession.selectList("findAllBalances");
    }

    public List<Balance> findBalancesByAccountId(Long id) {
        return this.sqlSession.selectList("findBalancesByAccountId", id);
    }

    public List<Balance> findBalancesByAccountIdAndCurrency(Transaction transaction) {
        return this.sqlSession.selectList("findBalancesByAccountIdAndCurrency", transaction);
    }

    public void createBalance(Balance Balance) {
        this.sqlSession.insert("createBalance", Balance);
    }

    public void updateBalance(Balance balance) {
        this.sqlSession.update("updateBalance", balance);
    }
}
