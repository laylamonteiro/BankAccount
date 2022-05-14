package com.laylamonteiro.bankaccount;

import com.laylamonteiro.bankaccount.entity.Account;
import com.laylamonteiro.bankaccount.entity.Transaction;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes({Account.class, Transaction.class})
@MapperScan("com.laylamonteiro.bankaccount.mapper")
@SpringBootApplication
public class BankAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountApplication.class, args);
    }

}
