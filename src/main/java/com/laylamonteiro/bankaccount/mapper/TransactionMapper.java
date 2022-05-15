package com.laylamonteiro.bankaccount.mapper;

import com.laylamonteiro.bankaccount.entity.Transaction;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TransactionMapper {

    @Select("SELECT * FROM transactions")
    List<Transaction> findAllTransactions();

    @Select("SELECT * FROM transactions WHERE accountId = '${accountId}'")
    List<Transaction> findTransactionsByAccountId(@Param("accountId") Long accountId);

    @Insert("INSERT INTO transactions (accountId, amount, currency, direction, description) " +
            "VALUES (${accountId}, '${amount}', '${currency}', '${direction}', '${description}')")
    @Options(useGeneratedKeys = true, keyProperty = "transactionId")
    void createTransaction(Transaction transaction);
}
