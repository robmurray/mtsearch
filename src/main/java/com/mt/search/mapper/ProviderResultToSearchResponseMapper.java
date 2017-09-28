package com.mt.search.mapper;

import com.mt.search.dto.SearchResponse;
import com.mt.search.model.ProviderResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by robertmurray on 3/11/17.
 */
@Component
public class ProviderResultToSearchResponseMapper {

    public SearchResponse convert(ProviderResult result) {
        return convert.apply(result);
    }

    Function<ProviderResult,SearchResponse> convert = new Function<ProviderResult,SearchResponse>() {
        @Override
        public SearchResponse apply(ProviderResult providerResult) {
            SearchResponse searchResponse = null;
            if (providerResult != null) {
                searchResponse = new SearchResponse(providerResult.getSearchResultCount(),providerResult.getSearchDurationInms(),providerResult.getLineMatches());
            }
            return searchResponse;
        }
    };


}
