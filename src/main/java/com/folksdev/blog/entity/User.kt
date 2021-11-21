package com.folksdev.blog.entity

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "users")
data class User @JvmOverloads constructor(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String?="",

        val username: String,

        val firstName: String,

        val lastName: String,

        val email: String,

        @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        val posts: List<Post>? = ArrayList(),

        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        val comments: List<Comment>? = ArrayList(),

        @Column(name = "created_at")
        val createdDate: LocalDateTime = LocalDateTime.now(),

        @Column(name = "updated_at")
        val updatedDate: LocalDateTime? = LocalDateTime.now(),

        ){

        override fun hashCode(): Int = javaClass.hashCode()

        override fun equals(other: Any?): Boolean {
                if (this == other) return true
                if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

                other as User

                return id != null && id == other.id
        }

        override fun toString(): String {
                return "User(username='$username',firstName='$firstName',lastName='$lastName',email='$email',createdDate='$createdDate',updatedDate='$updatedDate')"
        }

}
