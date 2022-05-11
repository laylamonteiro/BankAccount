package com.laylamonteiro.bankaccount.mapper;

import com.laylamonteiro.bankaccount.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM ACCOUNT WHERE accountId = #{accountId}")
    Account findByAccountId(@Param("accountId") String accountId);

    @Select("SELECT * FROM ACCOUNT WHERE customerId = #{customerId}")
    Account findByCustomerId(@Param("customerId") Long customerId);

}
