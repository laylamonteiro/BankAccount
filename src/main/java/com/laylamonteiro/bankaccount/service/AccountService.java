package com.laylamonteiro.bankaccount.service;

import com.laylamonteiro.bankaccount.dto.request.AccountForm;
import com.laylamonteiro.bankaccount.enums.AvailableCurrencies;
import com.laylamonteiro.bankaccount.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Currency;
import java.util.List;

import static com.laylamonteiro.bankaccount.enums.AvailableCurrencies.findByValue;
import static com.laylamonteiro.bankaccount.utils.RandomString.getAlphaNumericString;

@Slf4j
@Service
public class AccountService {

    public List<Account> findAll() {
        return null;
    }

    public Account findByAccountId(Long id) {
        return null;
    }

    public Account findByCustomerId(Long id) {
        return null;
    }

    public void create(AccountForm form) throws DuplicateMemberException {
        validateCurrencies(form.getCurrencies());
        Account account = validateExistingAccount(form.getCustomerId());
        account.setCustomerId(form.getCustomerId());
        account.setAccountId(generateAccountId());
        account.setCountry(form.getCountry());
        account.setCurrencies(form.getCurrencies());
        //repository.save(account)

        log.info("Created account '{}'", account.getAccountId());
    }

    private Account validateExistingAccount(Long customerId) throws DuplicateMemberException {
        Account existingAccount = findByCustomerId(customerId);
        if (ObjectUtils.isEmpty(existingAccount)) {
            return new Account();
        } else {
            String errorMessage = String.format("Account %s already registered.", existingAccount.getAccountId());
            log.info(errorMessage);
            throw new DuplicateMemberException(errorMessage);
        }
    }

    private void validateCurrencies(List<Currency> incomingCurrencies) {
        incomingCurrencies.forEach(currency -> {
            Boolean currencyAllowed = findByValue(currency.toString());
            if (!currencyAllowed) {
                throw new EnumConstantNotPresentException(AvailableCurrencies.class, currency.getSymbol());
            }
        });
    }

    private String generateAccountId() {
        return getAlphaNumericString();
    }
}
