package com.creaturelove.ecommercebackend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterBody(
        String username,
        String password
) {
}
