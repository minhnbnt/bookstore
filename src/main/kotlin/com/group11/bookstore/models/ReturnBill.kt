@file:Suppress("com.intellij.jpb.NoArgsConstructorInspection")

package com.group11.bookstore.models

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
    @Table(name = "tblReturnBill")
class ReturnBill(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(optional = false)
    var submitter: User,

    @ManyToOne(optional = false)
    var customer: Customer,

    @OneToMany(mappedBy = "bill")
    var rentBooks: MutableList<ReturnDetail>,

    @OneToMany(mappedBy = "bill")
    var collaterals: MutableList<ReturnCollateral>,

    @Column(nullable = false)
    var paymentDate: Timestamp = Timestamp(System.currentTimeMillis())
)