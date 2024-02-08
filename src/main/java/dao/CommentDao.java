package dao;

import entity.Comment;

import java.util.List;
import java.util.Optional;

public class CommentDao implements Dao<Integer, Comment>{
    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(Comment entity) {

    }

    @Override
    public Comment save(Comment entity) {
        return null;
    }
}
