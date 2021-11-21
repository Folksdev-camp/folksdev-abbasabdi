package com.folksdev.blog.entity

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Post @JvmOverloads constructor(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String?="",

        val title: String,

        val content: String,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        val user: User,

        @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        val comments: List<Comment>,

        @Column(name = "created_at")
        val createdDate: LocalDateTime = LocalDateTime.now(),

        @Column(name = "updated_at")
        val updatedDate: LocalDateTime? = LocalDateTime.now(),

){

        constructor(title: String,content: String, user: User): this(
                "",
                title=title,
                content=content,
                createdDate = LocalDateTime.now(),
                user = user,
                comments = listOf()

        )

        override fun hashCode(): Int = javaClass.hashCode()

        override fun equals(other: Any?): Boolean {
                if (this == other) return true
                if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

                other as Post

                return id != null && id == other.id
        }

        override fun toString(): String {
                return "Post(title='$title',content='$content',createdDate='$createdDate',updatedDate='$updatedDate')"
        }
}
