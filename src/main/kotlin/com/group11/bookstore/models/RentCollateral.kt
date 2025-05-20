@file:Suppress("com.intellij.jpb.NoArgsConstructorInspection")

package com.group11.bookstore.models

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "tblRentCollateral")
class RentCollateral(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(optional = false)
    var rentBill: RentBill,

    @ManyToOne(optional = false)
    var collateral: Collateral,

    @ManyToOne(optional = false)
    var bill: RentBill,

    @Column(nullable = false)
    var createDate: Timestamp = Timestamp(System.currentTimeMillis())
)