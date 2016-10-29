package com.a2a.block;

import java.util.List;


public class BlockData {

    public static enum BlockType {
        SYSTEM, LOADER, NODE
    }
    
    private BlockType blockType;
    
    private String activityBeanName;
    private String baseQueueName;
    private String beanName;
    private String channel;
    private String clientID;
    private String hostName;
    private String inputMessage;
    private String inputQueue;
    private String inputQueueId;
    private String name;
    private String outputMessage;
    private List<String> outputQueue;
    private List<String> parameterPath;
    private String parentBeanName;
    private String port;
    private String queueManager;
    private String system;
    private String transportType;
    private String type;
    private String decoratedBeanName;
    private String decoratedBeanType;
    
    /**
     * 
     */
    public BlockData() {
    }

    
    public String getActivityBeanName() {
        return activityBeanName;
    }

    public String getBaseQueueName() {
        return baseQueueName;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getChannel() {
        return channel;
    }

    public String getClientID() {
        return clientID;
    }

    public String getHostName() {
        return hostName;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public String getInputQueue() {
        return inputQueue;
    }

    public String getInputQueueId() {
        return inputQueueId;
    }

    public String getName() {
        return name;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public List<String> getOutputQueue() {
        return outputQueue;
    }

    public List<String> getParameterPath() {
        return parameterPath;
    }

    public String getParentBeanName() {
        return parentBeanName;
    }

    public String getPort() {
        return port;
    }

    public String getQueueManager() {
        return queueManager;
    }

    public String getSystem() {
        return system;
    }

    public String getTransportType() {
        return transportType;
    }

    public String getType() {
        return type;
    }

    public String getDecoratedBeanName() {
        return decoratedBeanName;
    }

    public String getDecoratedBeanType() {
        return decoratedBeanType;
    }


    public void setActivityBeanName(String activityBeanName) {
        this.activityBeanName = activityBeanName;
    }


    public void setBaseQueueName(String baseQueueName) {
        this.baseQueueName = baseQueueName;
    }


    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }


    public void setChannel(String channel) {
        this.channel = channel;
    }


    public void setClientID(String clientID) {
        this.clientID = clientID;
    }


    public void setHostName(String hostName) {
        this.hostName = hostName;
    }


    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }


    public void setInputQueue(String inputQueue) {
        this.inputQueue = inputQueue;
    }


    public void setInputQueueId(String inputQueueId) {
        this.inputQueueId = inputQueueId;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }


    public void setOutputQueue(List<String> outputQueue) {
        this.outputQueue = outputQueue;
    }


    public void setParameterPath(List<String> parameterPath) {
        this.parameterPath = parameterPath;
    }


    public void setParentBeanName(String parentBeanName) {
        this.parentBeanName = parentBeanName;
    }


    public void setPort(String port) {
        this.port = port;
    }


    public void setQueueManager(String queueManager) {
        this.queueManager = queueManager;
    }


    public void setSystem(String system) {
        this.system = system;
    }


    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }


    public void setType(String type) {
        this.type = type;
    }


    public void setDecoratedBeanName(String decoratedBeanName) {
        this.decoratedBeanName = decoratedBeanName;
    }


    public void setDecoratedBeanType(String decoratedBeanType) {
        this.decoratedBeanType = decoratedBeanType;
    }


    public BlockType getBlockType() {
        return blockType;
    }


    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

}
