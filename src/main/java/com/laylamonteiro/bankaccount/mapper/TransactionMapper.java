package com.laylamonteiro.bankaccount.mapper;

import com.laylamonteiro.bankaccount.model.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionMapper {

    @Select("SELECT * FROM TRANSACTION WHERE transactionId = #{transactionId}")
    Transaction findByTransactionId(@Param("transactionId") String transactionId);

    @Select("SELECT * FROM TRANSACTION WHERE customerId = #{customerId}")
    List<Transaction> findByCustomerId(@Param("customerId") String customerId);

}
