Feature: Encrypt some text and Decrypt it back

  Scenario: Encrypt and Decrypt
    Given the application is alive
    When I encrypt the text "super secret" with the key "123456"
    Then the encrypted text is returned
    When I decrypt the text with the key "123456"
    Then the decrypted text is returned with a value of "super secret"

  Scenario: Encrypt and Decrypt with mismatched keys
    Given the application is alive
    When I encrypt the text "super secret" with the key "123456"
    Then the encrypted text is returned
    When I decrypt the text with the key "654321"
    Then an error occurs indicating the text could not be decrypted