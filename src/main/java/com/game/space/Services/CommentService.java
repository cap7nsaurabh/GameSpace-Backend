package com.game.space.Services;

import java.util.List;

import com.game.space.Exception.CommentNotExistException;
import com.game.space.Exception.CommentUserDataAbsentException;
import com.game.space.Exception.GameHasNoCommentException;
import com.game.space.Exception.GameNotPresentException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Model.Comment;

public interface CommentService {
	public List<Comment> getAllCommentsByGameId(String gameId) throws GameNotPresentException;
	public Comment addComment(Comment comment,String gameId) throws GameNotPresentException, UserNotExistException, CommentUserDataAbsentException;
	public Comment deleteComment(String CommentId,String gameId) throws GameNotPresentException, GameHasNoCommentException, CommentNotExistException;
	public Comment editComment(Comment comment,String gameId);
}
