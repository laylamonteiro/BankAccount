package com.laylamonteiro.bankaccount.REMOVER.dao;

import com.laylamonteiro.bankaccount.entity.Balance;
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

    public List<Balance> findBalancesByAccountId(String id) {
        return this.sqlSession.selectList("findBalancesByAccountId", id);
    }

    public void createBalance(Balance Balance) {
        this.sqlSession.insert("createBalance", Balance);
    }
}
