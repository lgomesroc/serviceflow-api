package com.serviceflow.api.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Formato padrão das respostas de erro da API")
public class ErrorResponse {

    @Schema(
            description = "Código HTTP do erro",
            example = "404"
    )
    private int status;

    @Schema(
            description = "Mensagem descrevendo o erro ocorrido",
            example = "Service request not found"
    )
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}