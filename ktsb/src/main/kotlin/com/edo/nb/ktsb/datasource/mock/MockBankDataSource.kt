package com.edo.nb.ktsb.datasource.mock

import com.edo.nb.ktsb.datasource.BankDataSource
import com.edo.nb.ktsb.model.Bank
import org.springframework.stereotype.Repository
import kotlin.IllegalArgumentException

@Repository
class MockBankDataSource : BankDataSource {

    private val banks = mutableListOf(Bank("1234", 3.14, 11),
            Bank("121134", 31.13, 121),
            Bank("122234", 32.13, 111))

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
                ?: throw NoSuchElementException("\"Could not find a bank with account number ${bank.accountNumber}")
        banks.remove(currentBank)
        banks.add(bank)
        return bank
    }

    override fun createBank(bank: Bank): Bank {
        if(banks.any{it.accountNumber == bank.accountNumber}){
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists")
        }
        banks.add(bank)
        return bank
    }

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank =
            banks.firstOrNull() { it.accountNumber == accountNumber }
                    ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")

    override fun removeBank(accountNumber: String) {
        val currentBank = banks.firstOrNull { it.accountNumber == accountNumber }
                ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
        banks.remove(currentBank)
    }
}