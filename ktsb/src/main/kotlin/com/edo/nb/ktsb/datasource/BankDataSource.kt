package com.edo.nb.ktsb.datasource

import com.edo.nb.ktsb.model.Bank

interface BankDataSource {

    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
    fun createBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
    fun removeBank(accountNumber: String): Unit
}