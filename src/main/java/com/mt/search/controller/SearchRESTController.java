package com.mt.search.controller;

import com.mt.search.dto.RequestError;
import com.mt.search.dto.SearchResponse;
import com.mt.search.mapper.ProviderResultToSearchResponseMapper;
import com.mt.search.model.ProviderResult;
import com.mt.search.service.SearchProviderException;
import com.mt.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertmurray on 9/27/17.
 */
@Controller
@RequestMapping("/search")
public class SearchRESTController {
    static final Logger LOG = LoggerFactory.getLogger(SearchRESTController.class);

    @Autowired
    private SearchService searchService;

    @Autowired
    private ProviderResultToSearchResponseMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    SearchResponse search(@RequestParam(value = "text", required = true) String searchText) {
        ProviderResult providerResult = null;
        try {
            if(LOG.isDebugEnabled()) {
                LOG.debug(searchText);
            }

            // TODO additional querystring parameter validation

            providerResult = searchService.search(searchText);
        } catch (SearchProviderException e) {
            LOG.error("oops",e);
        }
        return mapper.convert(providerResult);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Object> handleConstraintViolation(MissingServletRequestParameterException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getMessage());
        RequestError error = new RequestError(HttpStatus.BAD_REQUEST, "Please provide the 'text' QueryString parameter. eg. localhost:8080/search?test=bob", errors);
        return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
    }

}
