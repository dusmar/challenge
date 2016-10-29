package com.a2a.block.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.a2a.block.BlockData;

@Repository
public class BlockWsRepository implements BlockRepository {

    
    @Value("${system.ws.endpoint}")
    private String serviceEndpoint;
    
    
    @Override
    public List<String> findSystems() {
    //    SystemWs systemWs = getService();
    //    return systemWs.getSystems();
    	return new ArrayList<String>();
    }
    
    @Override
    public List<BlockData> findLoaders(String systemId) {
//        if (systemId == null) {
//            return Collections.emptyList();
//        }
//        
//        SystemWs systemWs = getService();
//        List<ProcessorTreeElementWs> loaders = systemWs.getLoaders(systemId);
//        
//        return loaders.stream().map(this::parse).collect(Collectors.toList());
    	return     	new ArrayList<BlockData>();
    }

    
    @Override
    public List<BlockData> findForParentId(String parentId) {
//        SystemWs systemWs = getService();
 //       List<ProcessorTreeElementWs> childrenWs = systemWs.getChildren(null);
//        for(ProcessorTreeElementWs childWs : childrenWs){
//            children.add(SystemUtils.unwrapProcessorTreeElement(childWs));
//        }
//        return children;

//        return childrenWs.stream().map(this::parse).collect(Collectors.toList());
        return new ArrayList<BlockData>();

    }

//    protected SystemWs getService() {
//        SystemWs_Service service = new SystemWs_Service();
//        QName endpointName = new QName("http://ws.a2a.com/", "SystemWsPort");
//        service.addPort(endpointName, SOAPBinding.SOAP11HTTP_BINDING, serviceEndpoint);
//        SystemWs systemWs = service.getPort(endpointName, SystemWs.class);
//        return systemWs;
//    }
    
    
//    protected BlockData parse(ProcessorTreeElementWs in) {
//        BlockData out = new BlockData();
//
//        out.setActivityBeanName(in.getActivityBeanName());
//        out.setBaseQueueName(in.getBaseQueueName());
//        out.setBeanName(in.getBeanName());
//        out.setChannel(in.getChannel());
//        
//        out.setClientID(in.getClientID());
//        out.setHostName(in.getHostName());
//        
//        out.setInputMessage(in.getInputMessage());
//        out.setInputQueueId(in.getInputQueueId());
//        out.setInputQueue(in.getInputQueue());
//        out.setName(in.getName());
//        out.setOutputMessage(in.getOutputMessage());
//        
//        out.setOutputQueue(in.getOutputQueues());
////        out.setParameterPath(in.getParameterPaths());
//        
//        out.setParentBeanName(in.getParentBeanName());
//        out.setPort(in.getPort());
//        out.setQueueManager(in.getQueueManager());
//        out.setTransportType(in.getTransportType());
//        out.setType(in.getType());
//        
//        out.setDecoratedBeanName(in.getDecoratedBeanName());
//        out.setDecoratedBeanType(in.getDecoratedBeanType());
//        
//        return out;
//    }


}
