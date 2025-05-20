@file:Suppress("com.intellij.jpb.NoArgsConstructorInspection")

package com.group11.bookstore.models

import jakarta.persistence.*

@Entity
@Table(name = "tblCustomer")
class Customer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var fullName: String,

    @Column(nullable = false)
    var address: String,

    @Column(nullable = false)
    var phoneNumber: String,

    @Column(nullable = false)
    var email: String,
)