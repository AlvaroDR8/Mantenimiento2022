package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Comment;
import repositories.CommentRepository;

@Service
@Transactional
public class CommentService {
	
	// Managed Repository
	
	@Autowired
	private CommentRepository commentRepository;
	
	public CommentService() {
		super();
	}
	
	public Comment create() {
		Comment result;
		result = new Comment();
		return result;
	}
	
	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = commentRepository.findAll();
		Assert.notNull(result);

		return result;
	}
	
	public Comment findOne(int commentId) {
		Comment result;

		result = commentRepository.findOne(commentId);
		Assert.notNull(result);

		return result;
	}
	
	public Comment save(Comment comment) {
		Assert.notNull(comment);

		Comment result;
		result = commentRepository.save(comment);

		return result;
	}
	
	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);

		commentRepository.delete(comment);
	}
}