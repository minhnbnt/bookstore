@file:Suppress("com.intellij.jpb.NoArgsConstructorInspection")

package com.group11.bookstore.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tblReturnPenalty")
class ReturnPenalty(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(optional = false)
    var penalty: Penalty,

    @Column(nullable = false)
    var fee: Long,

    @ManyToOne(optional = false)
    var returnDetail: ReturnDetail
)