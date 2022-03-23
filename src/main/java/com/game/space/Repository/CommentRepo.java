package com.game.space.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.space.Model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment,String>{
	public List<Comment> findAllByCommentByUserid(String userId);

}
