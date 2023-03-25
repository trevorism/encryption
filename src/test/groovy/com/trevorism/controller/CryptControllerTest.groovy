package com.trevorism.controller

import com.trevorism.model.CryptRequest
import org.junit.jupiter.api.AssertThrows
import org.junit.jupiter.api.Test

class CryptControllerTest {

    @Test
    void testEncryption() {
        CryptRequest cryptRequest = new CryptRequest(payload: "test payload", key: "testkey")
        CryptController cryptController = new CryptController()
        assert cryptController.encrypt(cryptRequest)

    }

    @Test
    void testDecryption() {
        CryptRequest cryptRequest = new CryptRequest(payload: "QJK6DmotcMKuw73dUyp8FbQfa+K8Z9Eb", key: "testkey")
        CryptController cryptController = new CryptController()
        assert "test payload" == cryptController.decrypt(cryptRequest)
    }

    @Test
    void testDecryption2() {
        CryptRequest cryptRequest = new CryptRequest(payload: "/kidNhRVhibn7NCOHYrfT1SmsFK2BCRX", key: "testkey")
        CryptController cryptController = new CryptController()
        assert "test payload" == cryptController.decrypt(cryptRequest)
    }

    @Test
    void testDecryption3() {
        CryptRequest cryptRequest = new CryptRequest(payload: "IS/zNZHtdy1/d1OmW3NwG+m5sLxzD1wr", key: "testkey")
        CryptController cryptController = new CryptController()
        assert "test payload" == cryptController.decrypt(cryptRequest)
    }

    @Test
    void testEmptyOrNullEncryptionKey(){
        CryptController cryptController = new CryptController()
        AssertThrows.assertThrows(RuntimeException, () ->
                cryptController.encrypt(new CryptRequest(payload: "a test payload", key: null)))
        AssertThrows.assertThrows(RuntimeException, () ->
                cryptController.encrypt(new CryptRequest(payload: "a test payload", key: "")))
        AssertThrows.assertThrows(RuntimeException, () ->
                cryptController.decrypt(new CryptRequest(payload: "a test payload", key: null)))
        AssertThrows.assertThrows(RuntimeException, () ->
                cryptController.decrypt(new CryptRequest(payload: "a test payload", key: "")))
    }
}
