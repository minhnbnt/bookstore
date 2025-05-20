package com.group11.bookstore.repositories

import com.group11.bookstore.models.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByFullNameContaining(keyword: String): List<Customer>
}