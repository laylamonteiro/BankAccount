package com.laylamonteiro.bankaccount.mapper;

import com.laylamonteiro.bankaccount.entity.Balance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BalanceMapper {

    @Select("SELECT * FROM balances;")
    List<Balance> findAllBalances();

    @Select("SELECT * FROM balances WHERE accountId = ${accountId}")
    Balance findBalancesByAccountId(@Param("accountId") String accountId);

    @Select("SELECT * FROM balances WHERE accountId = '${accountId}' AND currency = '${currency}'")
    Balance findBalancesByAccountIdAndCurrency(@Param("accountId") String accountId);

    @Insert("INSERT INTO balances (accountId, availableAmount, currency) " +
            "VALUES (${accountId}, '${availableAmount}', '${currency}')")
    void createBalance(Balance balance);

    @Update("UPDATE balances SET availableAmount = '${availableAmount}' " +
            "WHERE accountId = '${accountId}' AND currency = '${currency}'")
    void updateBalance(Balance balance);
}
