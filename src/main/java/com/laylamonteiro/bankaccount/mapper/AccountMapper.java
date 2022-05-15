package com.laylamonteiro.bankaccount.mapper;

import com.laylamonteiro.bankaccount.entity.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM accounts;")
    List<Account> findAllAccounts();

    @Select("SELECT * FROM accounts WHERE accountId = ${accountId}")
    Account findAccountByAccountId(@Param("accountId") Long accountId);

    @Insert("INSERT INTO accounts (customerId, country) " +
            "VALUES (${customerId}, '${country}')")
    @Options(useGeneratedKeys = true, keyProperty = "accountId")
    void createAccount(Account account);
}
