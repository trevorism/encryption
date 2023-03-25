package com.trevorism.controller

import com.trevorism.model.CryptRequest
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.jasypt.util.text.StrongTextEncryptor

@Controller("/crypt")
class CryptController {

    @Tag(name = "Crypt Operations")
    @Operation(summary = "Encrypt a string with a key **Secure")
    @Post(value = "encryption", consumes = MediaType.APPLICATION_JSON, produces = MediaType.TEXT_PLAIN)
    @Secure(value = Roles.USER, allowInternal = true)
    String encrypt(@Body CryptRequest request) {
        try{
            StrongTextEncryptor encryptor = new StrongTextEncryptor()
            encryptor.setPassword(request.key)
            return encryptor.encrypt(request.payload)
        }catch(Exception e){
            throw new RuntimeException("Invalid encryption request", e)
        }
    }

    @Tag(name = "Crypt Operations")
    @Operation(summary = "Decrypt a string with a key **Secure")
    @Post(value = "decryption", consumes = MediaType.APPLICATION_JSON, produces = MediaType.TEXT_PLAIN)
    @Secure(value = Roles.USER, allowInternal = true)
    String decrypt(@Body CryptRequest request) {
        try{
            StrongTextEncryptor encryptor = new StrongTextEncryptor()
            encryptor.setPassword(request.key)
            return encryptor.decrypt(request.payload)
        }catch(Exception e){
            throw new RuntimeException("Invalid decryption request", e)
        }
    }
}
