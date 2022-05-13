package com.laylamonteiro.bankaccount.REMOVER.mapper;

import com.laylamonteiro.bankaccount.entity.Balance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BalanceMapper {

    @Select("SELECT * FROM balances;")
    List<Balance> findAllBalances();

    @Select("SELECT * FROM balances WHERE accountId = ${accountId}")
    Balance findBalanceByAccountId(@Param("accountId") String accountId);

    @Select("SELECT * FROM balances WHERE customerId = ${customerId}")
    Balance findBalanceByCurrencyAndAccountId(@Param("customerId") Long customerId);


    @Insert("INSERT INTO balances (accountId, availableAmount, currency) " +
            "VALUES (${accountId}, '${availableAmount}', '${currency}')")
    void createBalance(Balance balance);
}
