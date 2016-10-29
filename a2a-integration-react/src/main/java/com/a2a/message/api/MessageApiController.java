package com.a2a.message.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a.api.AbstractController;
import com.a2a.common.Page;
import com.a2a.common.PagingInfo;
import com.a2a.message.Message;
import com.a2a.message.MessageFilter;
import com.a2a.message.MessageService;

/**
 * REST facade for {@link MessageService} class
 *
 */ 
@Controller
@RequestMapping("/api")
public class MessageApiController extends AbstractController {

    private MessageService service;
    
    /**
     * TODO comment
     * 
     * @param r
     * @param query
     * @return
     */
    @RequestMapping(path = "messages", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> listMessages(RequestEntity<?> r, @RequestParam(name = "q", required = false) String query) {
        PagingInfo paging = readPagingInfo(r);

        //get results
        Page<Message> result = service.filterMessages(new MessageFilter(query), paging);
        
        //write response
        return new ResponseEntity<>(result.getContent(), writePagingInfo(result), HttpStatus.OK);
    }

    
    /**
     * TODO
     * 
     * @param id
     * @return
     */
    @RequestMapping(path = "messages/{id}", method = RequestMethod.GET)
    public @ResponseBody Message findMessage(@PathVariable(name = "id") Long id) {
        return service.fetchById(id);
    }

    
    @Autowired
    public void setService(MessageService service) {
        this.service = service;
    }
}
