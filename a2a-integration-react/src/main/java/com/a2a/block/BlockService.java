package com.a2a.block;

import java.util.List;

/**
 * TODO
 *
 */
public interface BlockService {

    
    List<BlockData> fetchSystems();
    
    List<BlockData> fetchLoaders(String systemId);
    
    /**
     * 
     * @param parentId
     * @return
     */
    List<BlockData> fetchForParent(String parentId);

}
