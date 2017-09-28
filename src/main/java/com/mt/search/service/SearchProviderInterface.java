package com.mt.search.service;

import com.mt.search.model.ProviderResult;

import java.util.List;

/**
 * Created by robertmurray on 9/27/17.
 */
public interface SearchProviderInterface {
    ProviderResult search(String searchText) throws SearchProviderException;

}
