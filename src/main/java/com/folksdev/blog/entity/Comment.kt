package com.folksdev.blog.entity

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Comment @JvmOverloads constructor(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val text: String,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    val post: Post,

    @Column(name = "created_at")
    val createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedDate: LocalDateTime = LocalDateTime.now(),

){

    override fun hashCode(): Int = javaClass.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this == other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as Comment

        return id != null && id == other.id
    }

    override fun toString(): String {
        return "Post(text='$text',createdDate='$createdDate',updatedDate='$updatedDate')"
    }

}
