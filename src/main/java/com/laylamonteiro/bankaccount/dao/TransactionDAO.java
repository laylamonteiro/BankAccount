package com.laylamonteiro.bankaccount.dao;

import com.laylamonteiro.bankaccount.entity.Transaction;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionDAO {

    private final SqlSession sqlSession;

    public TransactionDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Transaction> findAll() {
        return this.sqlSession.selectList("findAllTransactions");
    }

    public List<Transaction> findAllByAccountId(Long accountId) {
        return this.sqlSession.selectList("findTransactionsByAccountId");
    }

    public Transaction findByTransactionId(Long id) {
        return this.sqlSession.selectOne("findTransactionByTransactionId", id);
    }

    public void createTransaction(Transaction transaction) {
        this.sqlSession.insert("createTransaction", transaction);
    }
}