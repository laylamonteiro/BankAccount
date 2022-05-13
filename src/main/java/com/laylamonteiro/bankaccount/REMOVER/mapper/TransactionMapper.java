package com.laylamonteiro.bankaccount.REMOVER.mapper;

import com.laylamonteiro.bankaccount.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionMapper {

    @Select("SELECT * FROM TRANSACTION WHERE transactionId = #{transactionId}")
    Transaction findTransactionByTransactionId(@Param("transactionId") String transactionId);

    @Select("SELECT * FROM TRANSACTION WHERE customerId = #{customerId}")
    List<Transaction> findTransactionByCustomerId(@Param("customerId") String customerId);

}
