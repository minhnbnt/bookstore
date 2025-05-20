@file:Suppress("com.intellij.jpb.NoArgsConstructorInspection")

package com.group11.bookstore.models

import jakarta.persistence.*

@Entity
@Table(name = "tblBook")
class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(optional = false)
    var title: BookTitle,

    @Column(nullable = false)
    var importPrice: Long,

    @Column(nullable = false)
    var rentPrice: Long,

    @Column(nullable = false)
    var rentStatus: String
) {

    override fun hashCode() = id.hashCode()

    override fun equals(other: Any?): Boolean {
        return other is Book && id == other.id
    }
}