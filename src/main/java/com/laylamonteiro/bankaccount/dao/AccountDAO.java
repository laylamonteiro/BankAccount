package com.laylamonteiro.bankaccount.dao;

import com.laylamonteiro.bankaccount.entity.Account;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDAO {

    private final SqlSession sqlSession;

    public AccountDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Account> findAll() {
        return this.sqlSession.selectList("findAllAccounts");
    }

    public Account findByAccountId(Long id) {
        return this.sqlSession.selectOne("findAccountByAccountId", id);
    }

    public void createAccount(Account account) {
        this.sqlSession.insert("createAccount", account);
    }
}
