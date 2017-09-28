package com.mt.search.service;

import com.mt.search.model.ProviderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertmurray on 9/27/17.
 */
@Service
public class SearchService {
    static final Logger LOG = LoggerFactory.getLogger(SearchService.class);

    private SearchProviderInterface searchProvider;

    public SearchService(SearchProviderInterface searchProvider) {
        this.searchProvider = searchProvider;
    }

    /**
     *
     * @param textSearch
     * @return ProviderResults  - will always return a none null value or throw an exception
     * @throws SearchProviderException
     */
    public ProviderResult search(String textSearch) throws SearchProviderException {
        ProviderResult results = this.searchProvider.search(textSearch);
        if(results == null){
            results = new ProviderResult();
            results.setSearchDurationInms(0L);
            results.setSearchResultCount(0L);
            results.setLineMatches(new ArrayList<String>());
        }
        if(LOG.isDebugEnabled()){

            LOG.debug("line matches: "+results.getLineMatches());
            LOG.debug("search duration (ms): "+results.getSearchDurationInms());
        }
        return results;
    }

}
