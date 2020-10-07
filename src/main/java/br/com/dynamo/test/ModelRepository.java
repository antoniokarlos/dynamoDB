package br.com.dynamo.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;

@Repository
public class ModelRepository {
	
	@Autowired
	private DynamoDBMapper mapper;
	
	public Page<Model> findAll(Pageable pageable) {
		  
		 /*if (pageable.getSort() != null) {
		    throw new UnsupportedOperationException("Sorting not supported for find all scan operations");
		  }*/
		  DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		  // Scan to the end of the page after the requested page
		  int scanTo = (int) (pageable.getOffset() + (2 * pageable.getPageSize()));
		  scanExpression.setLimit(scanTo);
		  PaginatedScanList<Model> paginatedScanList = mapper.scan(Model.class, scanExpression);
		  Iterator<Model> iterator = paginatedScanList.iterator();
		  int processedCount = 0;
		  if (pageable.getOffset() > 0) {
		    processedCount = (int) scanThroughResults(iterator, pageable.getOffset());
		    if (processedCount < pageable.getOffset())
		      return new PageImpl<Model>(new ArrayList<Model>());
		  }
		  // Scan ahead to retrieve the next page count
		  List<Model> results = readPageOfResults(iterator, pageable.getPageSize());
		  
		  int totalCount = count(Model.class, scanExpression);
		  
		  return new PageImpl<Model>(results, pageable, totalCount);
		}
	
	private long scanThroughResults(Iterator<Model> iterator, long resultsToScan) {
		long processed = 0;
		while (iterator.hasNext() && processed < resultsToScan) {
			iterator.next();
			processed++;
		}
		return processed;
	}
	
	private List<Model> readPageOfResults(Iterator<Model> paginatedScanListIterator, int pageSize) {
		int processed = 0;
		List<Model> resultsPage = new ArrayList<>();
		while (paginatedScanListIterator.hasNext() && processed < pageSize) {
			resultsPage.add(paginatedScanListIterator.next());
			processed++;
		}
		return resultsPage;
	}	
	
	public <OpenBanking> int count(Class<OpenBanking> class1, DynamoDBScanExpression scanExpression) {
		return mapper.count(class1, scanExpression);
	}
}
