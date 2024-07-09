package com.trevorism.gcloud

import com.google.gson.Gson
import com.trevorism.https.AppClientSecureHttpClient
import com.trevorism.https.SecureHttpClient
import com.trevorism.model.CryptRequest

this.metaClass.mixin(io.cucumber.groovy.Hooks)
this.metaClass.mixin(io.cucumber.groovy.EN)

SecureHttpClient secureHttpClient = new AppClientSecureHttpClient()
Gson gson = new Gson()
String cryptResult
String decryptResult

When(/I encrypt the text {string} with the key {string}/) { String string, String string2 ->
    CryptRequest request = new CryptRequest([payload: string, key: string2])
    cryptResult = secureHttpClient.post("https://encryption.project.trevorism.com/crypt/encryption", gson.toJson(request))
}


Then(/the encrypted text is returned/) {  ->
    assert cryptResult
}


When(/I decrypt the text with the key {string}/) { String string ->
    CryptRequest request = new CryptRequest([payload: cryptResult, key: string])
    try{
        decryptResult = secureHttpClient.post("https://encryption.project.trevorism.com/crypt/decryption", gson.toJson(request))
    }
    catch (Exception ignored) {
        decryptResult = null
    }
}


Then(/the decrypted text is returned with a value of {string}/) { String string ->
    assert decryptResult == string
}


Then(/an error occurs indicating the text could not be decrypted/) {  ->
    assert !decryptResult
}