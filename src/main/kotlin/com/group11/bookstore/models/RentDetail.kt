@file:Suppress("com.intellij.jpb.NoArgsConstructorInspection")

package com.group11.bookstore.models

import jakarta.persistence.*

@Entity
@Table(name = "tblRentDetail")
class RentDetail(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(optional = false)
    var book: Book,

    @Column(nullable = false)
    var price: Long,

    @ManyToOne(optional = false)
    var bill: RentBill,

    @OneToMany(mappedBy = "rentDetail")
    var rentPenalties: MutableList<RentPenalty>
)