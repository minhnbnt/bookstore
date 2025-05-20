@file:Suppress("com.intellij.jpb.NoArgsConstructorInspection")

package com.group11.bookstore.models

import jakarta.persistence.*

@Entity
@Table(name = "tblReturnDetail")
class ReturnDetail(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(optional = false)
    var bill: ReturnBill,

    @OneToOne(optional = false)
    var rentDetail: RentDetail,

    @OneToMany(mappedBy = "returnDetail")
    var penalties: MutableList<ReturnPenalty>,

    @Column(nullable = false)
    var price: Long
)