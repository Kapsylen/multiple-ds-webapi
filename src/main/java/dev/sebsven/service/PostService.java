package dev.sebsven.service;

import dev.sebsven.domain.Post;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final JdbcClient client;


    public PostService(@Qualifier("blogJdbcClient") JdbcClient client) {
        this.client = client;
    }

    public List<Post> findAll() {
        return client.sql("SELECT id, title, text, date, time_to_read, tags FROM Post")
                .query(Post.class)
                .list();
    }

    public Optional<Post> findById(Long id) {
        return client.sql("SELECT id, title, text, date, time_to_read, tags FROM Post WHERE id = ?")
                .param(id)
                .query(Post.class)
                .optional();
    }

    public void create(Post post) {
        client.sql("INSERT INTO Post (id, title, text, date, time_to_read, tags) VALUES (?, ?, ?, ?, ?, ?)")
                .params(post.id(), post.title(), post.text(), post.date(), post.time_to_read(), post.tags())
                .update();
    }
    public void update(Post post) {
        client.sql("UPDATE Post SET title = ?, text = ?, date = ?, time_to_read = ?, tags = ? WHERE id = ?")
                .params(post.title(), post.text(), post.date(), post.time_to_read(), post.tags(), post.id())
                .update();
    }

    public void delete(Long id) {
        client.sql("DELETE FROM Post WHERE id = ?")
                .param(id)
                .update();
    }
}
