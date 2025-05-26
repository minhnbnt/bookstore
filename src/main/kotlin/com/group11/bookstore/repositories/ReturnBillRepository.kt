package com.group11.bookstore.repositories

import com.group11.bookstore.models.ReturnBill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReturnBillRepository : JpaRepository<ReturnBill, Long>