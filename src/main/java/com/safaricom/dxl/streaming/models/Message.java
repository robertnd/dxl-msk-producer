package com.safaricom.dxl.streaming.models;

import java.io.Serializable;

/**
 * The type Message.
 */
public class Message implements Serializable
{
    private String transactionType = "";
    private Boolean callback;
    private String sourceSystem = "";
    private String serviceName = "";
    private String msisdn = "";
    private String transactionId = "";
    private String productId = "";
    private String amount = "";
    private String channel = "";
    private String checkoutRequestID = "";
    private String merchantRequestID = "";

    /**
     * No args constructor for use in serialization
     */
    public Message() {
    }

    /**
     * Instantiates a new Message.
     *
     * @param transactionType   the transaction type
     * @param callback          the callback
     * @param sourceSystem      the source system
     * @param serviceName       the service name
     * @param msisdn            the msisdn
     * @param transactionId     the transaction id
     * @param productId         the product id
     * @param amount            the amount
     * @param channel           the channel
     * @param checkoutRequestID the checkout request id
     * @param merchantRequestID the merchant request id
     */
    public Message(String transactionType, Boolean callback, String sourceSystem, String serviceName, String msisdn, String transactionId, String productId, String amount, String channel, String checkoutRequestID, String merchantRequestID) {
        super();
        this.transactionType = transactionType;
        this.callback = callback;
        this.sourceSystem = sourceSystem;
        this.serviceName = serviceName;
        this.msisdn = msisdn;
        this.transactionId = transactionId;
        this.productId = productId;
        this.amount = amount;
        this.channel = channel;
        this.checkoutRequestID = checkoutRequestID;
        this.merchantRequestID = merchantRequestID;
    }

    private Message(MessageBuilder builder) {
        this.transactionType = builder.transactionType;
        this.callback = builder.callback;
        this.sourceSystem = builder.sourceSystem;
        this.serviceName = builder.serviceName;
        this.msisdn = builder.msisdn;
        this.transactionId = builder.transactionId;
        this.productId = builder.productId;
        this.amount = builder.amount;
        this.channel = builder.channel;
        this.checkoutRequestID = builder.checkoutRequestID;
        this.merchantRequestID = builder.merchantRequestID;
    }

    /**
     * Gets transaction type.
     *
     * @return the transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets transaction type.
     *
     * @param transactionType the transaction type
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets callback.
     *
     * @return the callback
     */
    public Boolean getCallback() {
        return callback;
    }

    /**
     * Sets callback.
     *
     * @param callback the callback
     */
    public void setCallback(Boolean callback) {
        this.callback = callback;
    }

    /**
     * Gets source system.
     *
     * @return the source system
     */
    public String getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Sets source system.
     *
     * @param sourceSystem the source system
     */
    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    /**
     * Gets service name.
     *
     * @return the service name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets service name.
     *
     * @param serviceName the service name
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Gets msisdn.
     *
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * Sets msisdn.
     *
     * @param msisdn the msisdn
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * Gets transaction id.
     *
     * @return the transaction id
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets transaction id.
     *
     * @param transactionId the transaction id
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Gets channel.
     *
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Sets channel.
     *
     * @param channel the channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * Gets checkout request id.
     *
     * @return the checkout request id
     */
    public String getCheckoutRequestID() {
        return checkoutRequestID;
    }

    /**
     * Sets checkout request id.
     *
     * @param checkoutRequestID the checkout request id
     */
    public void setCheckoutRequestID(String checkoutRequestID) {
        this.checkoutRequestID = checkoutRequestID;
    }

    /**
     * Gets merchant request id.
     *
     * @return the merchant request id
     */
    public String getMerchantRequestID() {
        return merchantRequestID;
    }

    /**
     * Sets merchant request id.
     *
     * @param merchantRequestID the merchant request id
     */
    public void setMerchantRequestID(String merchantRequestID) {
        this.merchantRequestID = merchantRequestID;
    }


    @Override
    public String toString() {
        return "Message{" +
                "transactionType='" + transactionType + '\'' +
                ", callback=" + callback +
                ", sourceSystem='" + sourceSystem + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", productId='" + productId + '\'' +
                ", amount='" + amount + '\'' +
                ", channel='" + channel + '\'' +
                ", checkoutRequestID='" + checkoutRequestID + '\'' +
                ", merchantRequestID='" + merchantRequestID + '\'' +
                '}';
    }

    /**
     * The type Message builder.
     */
    public static class MessageBuilder
    {

        private String transactionType = "";
        private Boolean callback;
        private String sourceSystem = "";
        private String serviceName = "";
        private String msisdn = "";
        private String transactionId = "";
        private String productId = "";
        private String amount = "";
        private String channel = "";
        private String checkoutRequestID = "";
        private String merchantRequestID = "";

        /**
         * Instantiates a new Message builder.
         *
         * @param transactionId the transaction id
         * @param callback      the callback
         * @param sourceSystem  the source system
         * @param serviceName   the service name
         */
        public MessageBuilder(String transactionId, Boolean callback, String sourceSystem, String serviceName) {
            this.callback = callback;
            this.sourceSystem = sourceSystem;
            this.serviceName = serviceName;
            this.transactionId = transactionId;
        }

        /**
         * Transaction type message builder.
         *
         * @param transactionType the transaction type
         * @return the message builder
         */
        public MessageBuilder transactionType(String transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        /**
         * Msisdn message builder.
         *
         * @param msisdn the msisdn
         * @return the message builder
         */
        public MessageBuilder msisdn(String msisdn) {
            this.msisdn = msisdn;
            return this;
        }

        /**
         * Product id message builder.
         *
         * @param productId the product id
         * @return the message builder
         */
        public MessageBuilder productId(String productId) {
            this.productId = productId;
            return this;
        }

        /**
         * Channel message builder.
         *
         * @param channel the channel
         * @return the message builder
         */
        public MessageBuilder channel(String channel) {
            this.channel = channel;
            return this;
        }

        /**
         * Checkout request id message builder.
         *
         * @param checkoutRequestID the checkout request id
         * @return the message builder
         */
        public MessageBuilder checkoutRequestID(String checkoutRequestID) {
            this.checkoutRequestID = checkoutRequestID;
            return this;
        }

        /**
         * Merchant request id message builder.
         *
         * @param merchantRequestID the merchant request id
         * @return the message builder
         */
        public MessageBuilder merchantRequestID(String merchantRequestID) {
            this.merchantRequestID = merchantRequestID;
            return this;
        }

        /**
         * Amount message builder.
         *
         * @param amount the amount
         * @return the message builder
         */
        public MessageBuilder amount(String amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Build message.
         *
         * @return the message
         */
        public Message build() {
            Message message =  new Message(this);
            validateUserObject(message);
            return message;
        }

        private void validateUserObject(Message message) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
}
