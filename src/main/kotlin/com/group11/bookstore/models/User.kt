@file:Suppress("com.intellij.jpb.NoArgsConstructorInspection")

package com.group11.bookstore.models

import jakarta.persistence.*

@Entity
@Table(name = "tblUser")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var fullName: String?,

    @Column(nullable = false)
    var phoneNumber: String?,

    @Column(nullable = false)
    var email: String?,

    @Column(nullable = false)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var role: String?
) {
    constructor(username: String, password: String)
        : this(0, null, null, null, username, password, null)
}