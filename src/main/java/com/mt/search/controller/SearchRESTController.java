package com.mt.search.controller;

import com.mt.search.dto.RequestError;
import com.mt.search.dto.SearchResponse;
import com.mt.search.mapper.ProviderResultToSearchResponseMapper;
import com.mt.search.model.ProviderResult;
import com.mt.search.service.SearchProviderException;
import com.mt.search.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertmurray on 9/27/17.
 */
@Controller
@Validated
@RequestMapping("/")
@Api(value="search", description="Case Insenstive Search API's")
public class SearchRESTController {
    static final Logger LOG = LoggerFactory.getLogger(SearchRESTController.class);

    @Autowired
    private SearchService searchService;

    @Autowired
    private ProviderResultToSearchResponseMapper mapper;

    /*@ApiOperation(value = "default URI", response = Iterable.class)
    @RequestMapping(path="/", method = RequestMethod.GET)
    public @ResponseBody
    Message defaultResource(){
        return new Message("No operation supported. P");

    }
*/

    @ApiOperation(value = "CaseInsensitive Search", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Search"),
            @ApiResponse(code = 400, message = "Invalid request parameters"),
            @ApiResponse(code = 401, message = "You are not authorized to access this api"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 500, message = "Unexpected server error."),

    }
    )
    @RequestMapping(path="/search", method = RequestMethod.GET)
    public @ResponseBody
    SearchResponse search(@Size(max = 100, message = "max search text size is 100") @RequestParam(value = "text", required = true) String searchText) {
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
    public ResponseEntity<Object> handleMissingRequestParameterViolation(MissingServletRequestParameterException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getMessage());
        RequestError error = new RequestError(HttpStatus.BAD_REQUEST, "Please provide the 'text' QueryString parameter. eg. localhost:8080/search?test=bob", errors);
        return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
    }
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getMessage());
        RequestError error = new RequestError(HttpStatus.BAD_REQUEST, "max search string size exceeded. must be less then 100", errors);
        return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
    }


}
