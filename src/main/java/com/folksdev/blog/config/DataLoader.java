package com.folksdev.blog.config;


import com.folksdev.blog.entity.Comment;
import com.folksdev.blog.entity.Post;
import com.folksdev.blog.entity.User;
import com.folksdev.blog.repository.CommentRepository;
import com.folksdev.blog.repository.PostRepository;
import com.folksdev.blog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "command.line.runner.enable",havingValue = "true")
public class DataLoader implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public DataLoader(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    /**
    val username: String,

    val firstName: String,

    val lastName: String,

    val email: String,
            **/

    @Override
    public void run(String... args) throws Exception {

        User abbas = new User("safarov","abbas","ahmet","abbasabdullaziz@gmail.com");
        User cagriBaskan = new User("cagriBaskan","cagri","dursun","cagridursun@gmail.com");

        Post firstPost = new Post("hello title","hello content",abbas);
        Post secondPost = new Post("Spring boot advanced","about everything with spring",cagriBaskan);

        Comment comment = new Comment("hello world!",abbas,firstPost);

        postRepository.save(firstPost);
        postRepository.save(secondPost);
        commentRepository.save(comment);

        userRepository.findAll().forEach(user->System.out.printf("\n Id -> %s \n Info -> %s",user.getId(),user));

    }
}
