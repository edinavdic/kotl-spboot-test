package com.edo.nb.ktsb.service

import com.edo.nb.ktsb.datasource.BankDataSource
import com.edo.nb.ktsb.datasource.mock.MockBankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BankServiceTest {

    private val dataSource: BankDataSource = mockk(relaxed = true)
    private val bankService: BankService = BankService(dataSource)

    @Test
    fun `should call its data source to retrieve banks`(){
        //given
        //every { dataSource.retrieveBanks() } returns emptyList()
        //when
        val banks = bankService.getBanks()
        //then
        verify(exactly = 1) { dataSource.retrieveBanks() }
    }
}