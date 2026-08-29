package com.serviceflow.api.controller;

import com.serviceflow.api.dto.ServiceRequestRequest;
import com.serviceflow.api.dto.ServiceRequestResponse;
import com.serviceflow.api.dto.ServiceRequestStatusRequest;
import com.serviceflow.api.exception.ErrorResponse;
import com.serviceflow.api.service.ServiceRequestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-requests")
@Tag(
        name = "Solicitações de Serviço",
        description = "Operações para gerenciamento de solicitações de serviço"
)
public class ServiceRequestController {

    private final ServiceRequestService service;

    public ServiceRequestController(ServiceRequestService service) {
        this.service = service;
    }

    @Operation(
            summary = "Criar solicitação de serviço",
            description = "Cria uma nova solicitação de serviço."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Solicitação criada com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = ServiceRequestResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados da solicitação inválidos",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceRequestResponse create(
            @Valid @RequestBody ServiceRequestRequest request) {

        return service.create(request);
    }

    @Operation(
            summary = "Listar solicitações de serviço",
            description = "Retorna todas as solicitações de serviço cadastradas."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Solicitações retornadas com sucesso",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ServiceRequestResponse.class
                            )
                    )
            )
    })
    @GetMapping
    public List<ServiceRequestResponse> findAll() {
        return service.findAll();
    }

    @Operation(
            summary = "Buscar solicitação por ID",
            description = "Retorna uma solicitação de serviço a partir do seu identificador."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Solicitação encontrada",
                    content = @Content(
                            schema = @Schema(implementation = ServiceRequestResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Solicitação não encontrada",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ServiceRequestResponse findById(
            @Parameter(
                    name = "id",
                    description = "Identificador da solicitação de serviço",
                    required = true,
                    in = ParameterIn.PATH,
                    example = "1"
            )
            @PathVariable Long id) {

        return service.findById(id);
    }

    @Operation(
            summary = "Atualizar solicitação de serviço",
            description = "Atualiza os dados de uma solicitação de serviço existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Solicitação atualizada com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = ServiceRequestResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados da solicitação inválidos",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Solicitação não encontrada",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ServiceRequestResponse update(
            @Parameter(
                    name = "id",
                    description = "Identificador da solicitação de serviço",
                    required = true,
                    in = ParameterIn.PATH,
                    example = "1"
            )
            @PathVariable Long id,
            @Valid @RequestBody ServiceRequestRequest request) {

        return service.update(id, request);
    }

    @Operation(
            summary = "Atualizar status da solicitação",
            description = "Altera o status de uma solicitação de serviço respeitando as regras de transição definidas pela API."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Status atualizado com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = ServiceRequestResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados da solicitação inválidos",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Solicitação não encontrada",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Transição de status não permitida",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PatchMapping("/{id}/status")
    public ServiceRequestResponse updateStatus(
            @Parameter(
                    name = "id",
                    description = "Identificador da solicitação de serviço",
                    required = true,
                    in = ParameterIn.PATH,
                    example = "1"
            )
            @PathVariable Long id,
            @Valid @RequestBody ServiceRequestStatusRequest request) {

        return service.updateStatus(id, request);
    }
}