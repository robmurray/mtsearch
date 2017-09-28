package com.mt.search.model;

/**
 * Contains search results
 *
 * Created by robertmurray on 9/27/17.
 */
import java.util.List;

public class ProviderResult {
    private long searchResultCount;
    private long searchDurationInms;
    private List<String> lineMatches;

    public ProviderResult() {
    }

    public ProviderResult(long searchResultCount, long searchDurationInms, List<String> lineMatches) {
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
