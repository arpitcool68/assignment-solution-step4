package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsSourceRepository;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */

@Service
@Transactional
public class NewsSourceServiceImpl implements NewsSourceService {

	/*
	 * Autowiring should be implemented for the NewsDao and MongoOperation. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	/*
	 * This method should be used to save a newsSource.
	 */

	private NewsSourceRepository newsSourceRepository;

	@Autowired
	public NewsSourceServiceImpl(NewsSourceRepository newsSourceRepository) {
		super();
		this.newsSourceRepository = newsSourceRepository;
	}

	@Override
	public boolean addNewsSource(NewsSource newsSource) {
		NewsSource savedNewsSource = newsSourceRepository.insert(newsSource);
		return null != savedNewsSource ? true : false;
	}

	/* This method should be used to delete an existing newsSource. */

	@Override
	public boolean deleteNewsSource(int newsSourceId) {

		Optional<NewsSource> optional = newsSourceRepository.findById(newsSourceId);
		if (null != optional && optional.isPresent()) {
			newsSourceRepository.deleteById(newsSourceId);
			return true;
		} else {
			return false;
		}

	}

	/* This method should be used to update an existing newsSource. */

	@Override
	public NewsSource updateNewsSource(NewsSource newsSource, int newsSourceId) throws NewsSourceNotFoundException {

		try {
			Optional<NewsSource> optional = newsSourceRepository.findById(newsSourceId);
			if (optional.isPresent()) {
				BeanUtils.copyProperties(newsSource, optional.get());
				newsSourceRepository.save(newsSource);
				return optional.get();
			} else {
				throw new NewsSourceNotFoundException("News Source not found");
			}
		} catch (Exception e) {
			throw new NewsSourceNotFoundException("News Source not found");
		}
	}

	/* This method should be used to get a specific newsSource for an user. */

	@Override
	public NewsSource getNewsSourceById(String userId, int newsSourceId) throws NewsSourceNotFoundException {
		try {
			List<NewsSource> newsSourceList = newsSourceRepository.findAllNewsSourceByNewsSourceCreatedBy(userId);
			Optional<NewsSource> optional = newsSourceList.stream()
					.filter(newsSource -> newsSource.getNewsSourceId() == newsSourceId).findFirst();
			if (optional.isPresent()) {
				return optional.get();
			} else {
				throw new NewsSourceNotFoundException("Not Found");
			}
		} catch (Exception e) {
			return null;
		}
	}

	/* This method should be used to get all newsSource for a specific userId. */

	@Override
	public List<NewsSource> getAllNewsSourceByUserId(String createdBy) {
		return newsSourceRepository.findAllNewsSourceByNewsSourceCreatedBy(createdBy);

	}

}
