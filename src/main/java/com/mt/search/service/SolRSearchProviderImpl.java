package com.mt.search.service;

import com.mt.search.model.ProviderResult;
import com.mt.search.util.SimpleTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * TODO implement me
 *
 * Created by robertmurray on 9/27/17.
 */
public class SolRSearchProviderImpl implements SearchProviderInterface {
    static final Logger LOG = LoggerFactory.getLogger(SolRSearchProviderImpl.class);


    public SolRSearchProviderImpl() throws SearchProviderException {

    }


    @Override
    public ProviderResult search(String searchText) throws SearchProviderException {
        return null;
    }


}
