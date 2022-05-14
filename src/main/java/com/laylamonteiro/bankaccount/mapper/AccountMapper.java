package com.laylamonteiro.bankaccount.mapper;

import com.laylamonteiro.bankaccount.entity.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM accounts;")
    List<Account> findAllAccounts();

    @Select("SELECT * FROM accounts WHERE accountId = ${accountId}")
    Account findAccountByAccountId(@Param("accountId") String accountId);

    @Select("SELECT * FROM accounts WHERE customerId = ${customerId}")
    Account findAccountsByCustomerId(@Param("customerId") Long customerId);


//    @Results(value = {
//            @Result(property = "currencies", column = "currencies", typeHandler = ArrayTypeHandler.class),
//            @Result(property = "balances", column = "balances", typeHandler = ArrayTypeHandler.class),
//            @Result(property = "transactions", column = "transactions", typeHandler = ArrayTypeHandler.class)
//    })
    @Insert("INSERT INTO accounts (customerId, country) " +
            "VALUES (${customerId}, '${country}')")
    @Options(useGeneratedKeys=true, keyProperty="accountId")
    void createAccount(Account account);
}
