package com.group11.bookstore.repositories

import com.group11.bookstore.models.Customer
import com.group11.bookstore.models.RentBill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RentBillRepository: JpaRepository<RentBill, Long> {

    @Query("""
        select distinct rentBill
        from RentBill rentBill
        inner join RentDetail rentDetail
        left join ReturnDetail returnDetail
        where returnDetail is null
          and rentBill.customer = ?1
    """)
    fun findPendingBills(customer: Customer): List<RentBill>
}