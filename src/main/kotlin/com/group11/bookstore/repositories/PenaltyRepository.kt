package com.group11.bookstore.repositories

import com.group11.bookstore.models.Book
import com.group11.bookstore.models.Penalty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.stream.Stream

@Repository
interface PenaltyRepository : JpaRepository<Penalty, Long> {

    @Query("""
        select penalty
        from Book book
        left join RentDetail rentDetail
        left join ReturnDetail returnDetail
        left join RentPenalty rentPenalty
        left join ReturnPenalty returnPenalty
        left join Penalty penalty
        where book = ?1
    """)
    fun findBookPenalties(book: Book): Stream<Penalty>
}