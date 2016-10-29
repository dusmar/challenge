package com.a2a.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2a.block.BlockData.BlockType;
import com.a2a.block.repository.BlockRepository;


@Service
public class BlockRepositoryService implements BlockService {

    private BlockRepository repository;

    @Override
    public List<BlockData> fetchForParent(String parentId) {
        return repository.findForParentId(parentId);
    }
    
    
    @Autowired
    public void setRepository(BlockRepository repository) {
        this.repository = repository;
    }


    /**
     * @return
     * @see com.a2a.block.BlockService#fetchSystems()
     */
    @Override
    public List<BlockData> fetchSystems() {
        List<String> systems = repository.findSystems();
        if (systems == null) {
            return Collections.emptyList();
        }
        
        List<BlockData> blocks = new ArrayList<>();
        for (String s : systems) {
            BlockData bd = new BlockData();
            bd.setBlockType(BlockType.SYSTEM);
            bd.setName(s);
            blocks.add(bd);
        }
        return blocks;
    }


    /**
     * @param systemId
     * @return
     * @see com.a2a.block.BlockService#fetchLoaders(java.lang.String)
     */
    @Override
    public List<BlockData> fetchLoaders(String systemId) {
        return repository.findLoaders(systemId);
    }

}
