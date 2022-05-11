package com.laylamonteiro.bankaccount.dao;

import com.laylamonteiro.bankaccount.model.Account;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    private final SqlSession sqlSession;

    public AccountDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Account findByCustomerId(Long id) {
        return this.sqlSession.selectOne("findByCustomerId", id);
    }

    public Account findByAccountId(String id) {
        return this.sqlSession.selectOne("findByAccountId", id);
    }
}
