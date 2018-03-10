package io.vertx.tp.ali.sms;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.init.TpConfig;

import java.io.Serializable;
import java.util.Objects;

public class SmsConfig implements Serializable {

    private static final String KEY = "ali-sms";
    private static final String KEY_ID = "access_id";
    private static final String KEY_SECRET = "access_secret";
    private static final String SIGN_NAME = "sign_name";

    static final String TIMEOUT_CONN = "timeout_connect";
    static final String TIMEOUT_READ = "timeout_read";
    static final String DFT_PRODUCT = "Dysmsapi";
    static final String DFT_REGION = "cn-hangzhou";

    static final String RESPONSE_REQUEST_ID = "request_id";
    static final String RESPONSE_BUSINESS_ID = "business_id";
    static final String RESPONSE_CODE = "code";
    static final String RESPONSE_MESSAGE = "message";

    private static final String DFT_DOMAIN = "dysmsapi.aliyuncs.com";

    private static final TpConfig CONFIG = TpConfig.create(KEY, KEY);

    private final String accessId;
    private final String accessSecret;
    private final String signName;
    private String endpoint;

    static SmsConfig create(final String accessId,
                            final String accessSecret,
                            final String signName) {
        return new SmsConfig(accessId, accessSecret, signName);
    }

    static SmsConfig create(final JsonObject config) {
        return new SmsConfig(config.getString(KEY_ID), config.getString(KEY_SECRET), config.getString(SIGN_NAME));
    }

    static SmsConfig create() {
        return create(CONFIG.getConfig());
    }

    private SmsConfig(final String accessId, final String accessSecret, final String signName) {
        this.accessId = accessId;
        this.accessSecret = accessSecret;
        this.signName = signName;
        this.endpoint = CONFIG.getEndPoint();
        if (null == this.endpoint) this.endpoint = DFT_DOMAIN;
    }

    public JsonObject getConfig() {
        return CONFIG.getConfig();
    }

    public String getAccessId() {
        return this.accessId;
    }

    public String getAccessSecret() {
        return this.accessSecret;
    }

    public String getSignName() {
        return this.signName;
    }

    public String getDomain() {
        return this.endpoint;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SmsConfig)) return false;
        final SmsConfig smsConfig = (SmsConfig) o;
        return Objects.equals(this.accessId, smsConfig.accessId) &&
                Objects.equals(this.accessSecret, smsConfig.accessSecret);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.accessId, this.accessSecret);
    }
}