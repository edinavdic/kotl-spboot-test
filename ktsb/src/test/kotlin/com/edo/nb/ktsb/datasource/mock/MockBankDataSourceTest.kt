package com.edo.nb.ktsb.datasource.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {

    private val mockDataSource: MockBankDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of banks`(){
        //given
        //when
        val banks = mockDataSource.retrieveBanks()
        //then
        //Assertions.assertThat(banks).isNotEmpty
        Assertions.assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should provide some mock data`(){
        //given
        //when
        val banks = mockDataSource.retrieveBanks()
        //then
        Assertions.assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        Assertions.assertThat(banks).anyMatch { it.trust != 0.0 }
        Assertions.assertThat(banks).anyMatch { it.transactionFee != 0 }
    }
}