package com.group11.bookstore.repositories

import com.group11.bookstore.models.Customer
import com.group11.bookstore.models.RentDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RentDetailRepository : JpaRepository<RentDetail, Long> {

    @Query("""
        select rentDetail
        from RentDetail rentDetail
        inner join RentBill bill
        where bill.customer = ?1
          and rentDetail.id not in (
              select returnDetail.id
              from ReturnDetail returnDetail
          )
    """)
    fun findRentingBooks(customer: Customer): List<RentDetail>
}