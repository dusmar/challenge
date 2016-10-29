package com.a2a.block.repository;

import java.util.List;

import com.a2a.block.BlockData;


public interface BlockRepository {

    /**
     * TODO
     * 
     * @param parentId
     * @return
     */
    List<BlockData> findForParentId(String parentId);

    /**
     * 
     * @return
     */
    List<String> findSystems();

    /**
     * 
     * @param systemId
     * @return
     */
    List<BlockData> findLoaders(String systemId);

}
