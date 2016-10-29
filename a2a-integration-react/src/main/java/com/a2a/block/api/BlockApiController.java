package com.a2a.block.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a2a.api.AbstractController;
import com.a2a.block.BlockData;
import com.a2a.block.BlockService;

/**
 * REST facade for {@link BlockService} class
 *
 * 
 */ 
@Controller
@RequestMapping("/api/blocks")
public class BlockApiController extends AbstractController {

    
    private BlockService blockService;
    

    @RequestMapping(path = "systems", method = RequestMethod.GET)
    public @ResponseBody List<BlockData> fetchSystems() {
        return blockService.fetchSystems();
    }

    
    @RequestMapping(path = "loaders/{systemId}", method = RequestMethod.GET)
    public @ResponseBody List<BlockData> fetchLoaders(@PathVariable(name = "systemId") String systemId) {
        return blockService.fetchLoaders(systemId);
    }

    
    @RequestMapping(path = "nodes/{parentId}", method = RequestMethod.GET)
    public @ResponseBody List<BlockData> fetch(@PathVariable(name = "parentId") String parentId) {
        return blockService.fetchForParent(parentId);
    }


    @Autowired
    public void setBlockService(BlockService blockService) {
        this.blockService = blockService;
    }
    
}
