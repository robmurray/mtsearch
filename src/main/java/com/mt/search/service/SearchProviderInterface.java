package com.mt.search.service;

import com.mt.search.model.ProviderResult;

import java.util.List;

/**
 * Subclass to provide different search strategies for access the datafile
 *
 *
 * Created by robertmurray on 9/27/17.
 */
public interface SearchProviderInterface {
    /**
     *
     * @param searchText the text to search on
     * @return ProviderResults or null
     * @throws SearchProviderException
     */
    ProviderResult search(String searchText) throws SearchProviderException;

}
