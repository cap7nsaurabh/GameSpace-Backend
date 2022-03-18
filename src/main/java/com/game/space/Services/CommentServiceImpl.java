package com.game.space.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.space.Exception.CommentNotExistException;
import com.game.space.Exception.CommentUserDataAbsentException;
import com.game.space.Exception.GameHasNoCommentException;
import com.game.space.Exception.GameNotPresentException;
import com.game.space.Exception.UserNotExistException;
import com.game.space.Model.Comment;
import com.game.space.Model.Game;
import com.game.space.Repository.CommentRepo;
import com.game.space.Repository.UserRepo;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	GameServiceImpl gameService;
	@Autowired
	CommentRepo commentRepo;
	@Autowired
	UserRepo userRepo;
	
	@Override
	public List<Comment> getAllCommentsByGameId(long gameId) throws GameNotPresentException {
		Game newGame=gameService.getGameByid(gameId);
		List<Comment> commentList=new ArrayList<Comment>();
		commentList=newGame.getGameComments();
		return commentList;
	}

	@Override
	public Comment addComment(Comment comment, long gameId) throws GameNotPresentException, UserNotExistException, CommentUserDataAbsentException {
		Game newGame=gameService.getGameByid(gameId);
		if(comment.getCommentByUserid()==null) {
			throw new CommentUserDataAbsentException("comment should have a user");
		}
		else if(userRepo.findById(comment.getCommentByUserid())==null) {
			throw new UserNotExistException("comment should have a valid user");
		}
		List<Comment> commentList=newGame.getGameComments();
		if(commentList==null) {
			commentList=new ArrayList<Comment>();
		}
		commentList.add(comment);
		newGame.setGameComment(commentList);
		return comment;
	}

	@Override
	public Comment deleteComment(long commentId, long gameId) throws GameNotPresentException, GameHasNoCommentException, CommentNotExistException {
		Game newGame=gameService.getGameByid(gameId);
		List<Comment> commentList=newGame.getGameComments();
		Comment comment=commentRepo.getById(commentId);
		if(comment==null) {
			throw new CommentNotExistException("comment not present");
		}
		if(commentList==null) {
			throw new GameHasNoCommentException("game already has 0 comments");
		}
		else if(commentList.isEmpty()) {
			throw new GameHasNoCommentException("game already has 0 comments");
		}
		else if(!commentList.contains(comment)) {
			throw new CommentNotExistException("comment not present for this game");
		}
		commentList.remove(comment);
		newGame.setGameComment(commentList);
		return comment;
	}

	@Override
	public Comment editComment(Comment comment, long gameId) {
		// TODO Auto-generated method stub
		return null;
	}

}
