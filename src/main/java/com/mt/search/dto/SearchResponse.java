package com.mt.search.dto;

/**
 * . response should include the following -
 . Total count of search results
 . Time (in milliseconds) it took to search.
 . List of all the matching lines.
 * Created by robertmurray on 9/27/17.
 */
import java.util.List;
public class SearchResponse {
    private long searchResultCount;
    private long searchDurationInms;
    private List<String> lineMatches;

    public SearchResponse() {
    }

    public SearchResponse(long searchResultCount, long searchDurationInms, List<String> lineMatches) {
        this.searchResultCount = searchResultCount;
        this.searchDurationInms = searchDurationInms;
        this.lineMatches = lineMatches;
    }

    public long getSearchResultCount() {
        return searchResultCount;
    }

    public void setSearchResultCount(long searchResultCount) {
        this.searchResultCount = searchResultCount;
    }

    public long getSearchDurationInms() {
        return searchDurationInms;
    }

    public void setSearchDurationInms(long searchDurationInms) {
        this.searchDurationInms = searchDurationInms;
    }

    public List<String> getLineMatches() {
        return lineMatches;
    }

    public void setLineMatches(List<String> lineMatches) {
        this.lineMatches = lineMatches;
    }
}
