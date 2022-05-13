package com.laylamonteiro.bankaccount.REMOVER.mapper;

import com.laylamonteiro.bankaccount.entity.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.ArrayTypeHandler;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM accounts;")
    List<Account> findAll();

    @Select("SELECT * FROM accounts WHERE accountId = ${accountId}")
    Account findAccountByAccountId(@Param("accountId") String accountId);

    @Select("SELECT * FROM accounts WHERE customerId = ${customerId}")
    Account findAccountByCustomerId(@Param("customerId") Long customerId);


    @Results(value = {
            @Result(property = "currencies", column = "currencies", typeHandler = ArrayTypeHandler.class),
            @Result(property = "balances", column = "balances", typeHandler = ArrayTypeHandler.class),
            @Result(property = "transactions", column = "transactions", typeHandler = ArrayTypeHandler.class)
    })
    @Insert("INSERT INTO accounts (customerId, accountId, country, currencies, balances, transactions) " +
            "VALUES (${customerId}, '${accountId}', '${country}', '${currencies}', '${balances}', '${transactions}')")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "customerId", before = false, resultType = Long.class)
    void createAccount(Account account);
}
