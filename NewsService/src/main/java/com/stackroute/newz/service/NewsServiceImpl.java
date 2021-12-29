package com.stackroute.newz.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.UserNews;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.util.exception.NewsNotFoundException;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	private NewsRepository newsRepository;
	
	@Autowired
	public NewsServiceImpl(NewsRepository newsRepository) {
		super();
		this.newsRepository = newsRepository;
	}

	
	@Override
	public boolean addNews(News news) {
		UserNews userNews = new UserNews();
		userNews.setNewslist(Arrays.asList(news));
		UserNews userNewsResp = newsRepository.insert(userNews);
		return null != userNewsResp ? true:false;
	}

	/* This method should be used to delete an existing news. */
	
	public boolean deleteNews(String userId, int newsId) {
		Optional<UserNews> optional = newsRepository.findById(userId);
		if (optional.isPresent()) {
			newsRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

	/* This method should be used to delete all news for a  specific userId. */
	
	public boolean deleteAllNews(String userId) throws NewsNotFoundException {
		try {
			Optional<UserNews> optional = newsRepository.findById(userId);
			if (optional.isPresent()) {
				newsRepository.delete(optional.get());
				return true;
			} else {
				throw new NewsNotFoundException(userId);
			}
		} catch (Exception e) {
			throw new NewsNotFoundException(userId);
		}
	}

	/*
	 * This method should be used to update a existing news.
	 */

	public News updateNews(News news, int newsId, String userId) throws NewsNotFoundException {
		try {
		Optional<UserNews> optional = newsRepository.findById(userId);

	
			if (optional.isPresent()) {
				UserNews userNews = optional.get();
				news.setContent("sdad");
				userNews.getNewslist().add(news);
				UserNews userNewsResponse = newsRepository.save(userNews);
				if(null != userNewsResponse) {
					return userNewsResponse.getNewslist().get(0);
				}else {
					return news;
				}
				
			}else {
				throw new NewsNotFoundException(userId); 
			}
		} catch (Exception e) {
			throw new NewsNotFoundException(userId);
		}
	}

	/*
	 * This method should be used to get a news by newsId created by specific user
	 */

	public News getNewsByNewsId(String userId, int newsId) throws NewsNotFoundException {
		
		try {
		Optional<UserNews> optional = newsRepository.findById(userId);
		if (optional.isPresent()) {
			UserNews userNews = optional.get();
			List<News> newsList = userNews.getNewslist();
			Optional<News> optionalNews = newsList.stream().filter(news -> news.getNewsId() == newsId).findFirst();
			if(optionalNews.isPresent()) {
				return optionalNews.get();
			}else {
				throw new NewsNotFoundException(userId);
			}
			
		} else {
			throw new NewsNotFoundException(userId);
		}
		}catch (Exception e) {
			throw new NewsNotFoundException(userId);
		}
	}

	/*
	 * This method should be used to get all news for a specific userId.
	 */

	public List<News> getAllNewsByUserId(String userId) {
		
		Optional<UserNews> optional = newsRepository.findById(userId);
		if (optional.isPresent()) {
			return optional.get().getNewslist();
		} else {
			return null;
		}
	}

}
