package com.mt.search.service;

import com.mt.search.util.SimpleTimer;
import com.mt.search.model.ProviderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * A very basic Search provider suitable for demostrative and POC purposes
 *
 * Attemps to load the data file from the classpath if that fails will load from
 * the current directory
 *
 * Created by robertmurray on 9/27/17.
 */
public class SimpleFileSearchProviderImpl implements SearchProviderInterface {
    static final Logger LOG = LoggerFactory.getLogger(SimpleFileSearchProviderImpl.class);

    private Path sourceFilePath;

    public SimpleFileSearchProviderImpl(String sourceFile) throws SearchProviderException {
        if(sourceFile== null){
            throw new SearchProviderException("missing sourcefile");
        }
        if(LOG.isDebugEnabled()) {
            LOG.debug("source file:" + sourceFile);
        }
        try {
            sourceFilePath = Paths.get(getClass().getClassLoader().getResource(sourceFile).toURI());
            if(LOG.isDebugEnabled()) {
                LOG.debug("File path: "+sourceFilePath.toAbsolutePath().toString());
            }
        } catch (URISyntaxException e) {
            throw new SearchProviderException("error accessing source file" ,e);
        } catch (FileSystemNotFoundException e){
            try {
                // probably running the executable jar directly
                // attempt to load the file from the current directory
                LOG.info("unable to load from classpath. loading from current directory instead");
                sourceFilePath = Paths.get(sourceFile);
            }catch (Exception e2){
                throw new SearchProviderException("error accessing source file" ,e2);
            }

        }
    }

    //@PostConstruct
    //private void init() throws URISyntaxException {
    //}


    @Override
    public ProviderResult search(String searchText) throws SearchProviderException {
        if(searchText == null){
            return null;
        }

        ProviderResult pr = new ProviderResult();

        // needs to be effectively final to use in a stream
        AtomicLong occuranceCount = new AtomicLong();
        try {
            SimpleTimer ost = new SimpleTimer();
            ost.start();

            // TODO optimize into one iteration
            List<String> matches =Files.lines(sourceFilePath).filter(line->line.contains(searchText)).collect(Collectors.toList());
            pr.setLineMatches(matches);
            matches.forEach(line ->{
                occuranceCount.addAndGet(StringUtils.countOccurrencesOf(line,searchText));
            });
            pr.setSearchResultCount(occuranceCount.get());
            ost.stop();
            pr.setSearchDurationInms(ost.results());
            return pr;
        } catch (IOException e) {
            throw new SearchProviderException("An error occurred executing the search",e);
        }
    }


}
