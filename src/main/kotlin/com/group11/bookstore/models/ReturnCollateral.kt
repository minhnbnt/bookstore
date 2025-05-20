@file:Suppress("com.intellij.jpb.NoArgsConstructorInspection")

package com.group11.bookstore.models

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "tblReturnCollateral")
class ReturnCollateral(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(optional = false)
    var bill: ReturnBill,

    @ManyToOne(optional = false)
    var collateral: Collateral,

    @Column(nullable = false)
    var createDate: Timestamp = Timestamp(System.currentTimeMillis())
)