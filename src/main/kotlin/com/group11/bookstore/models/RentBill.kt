@file:Suppress("com.intellij.jpb.NoArgsConstructorInspection")

package com.group11.bookstore.models

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "tblRentBill")
class RentBill(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @ManyToOne(optional = false)
    var submitter: User,

    @ManyToOne(optional = false)
    var customer: Customer,

    @OneToMany(mappedBy = "bill")
    var rentBooks: MutableList<RentDetail>,

    @OneToMany(mappedBy = "bill")
    var collaterals: MutableList<RentCollateral>,

    @Column(nullable = false)
    var date: Timestamp = Timestamp(System.currentTimeMillis())
)