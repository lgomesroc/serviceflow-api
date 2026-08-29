package com.serviceflow.api.controller;

import com.serviceflow.api.entity.ServiceRequest;
import com.serviceflow.api.entity.ServiceRequestStatus;
import com.serviceflow.api.repository.ServiceRequestRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ServiceRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ServiceRequestRepository repository;

    @Test
    void shouldReturnOkWhenGettingServiceRequests() throws Exception {
        mockMvc.perform(get("/api/service-requests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].id").exists())
                .andExpect(jsonPath("$.content[0].title").exists())
                .andExpect(jsonPath("$.content[0].description").exists())
                .andExpect(jsonPath("$.content[0].status").exists())
                .andExpect(jsonPath("$.content[0].createdAt").exists())
                .andExpect(jsonPath("$.pageable").exists())
                .andExpect(jsonPath("$.totalElements").exists())
                .andExpect(jsonPath("$.totalPages").exists());
    }

    @Test
    void shouldReturnFirstPageWithRequestedSize() throws Exception {
        mockMvc.perform(
                        get("/api/service-requests")
                                .param("page", "0")
                                .param("size", "5")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(5))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.size").value(5))
                .andExpect(jsonPath("$.numberOfElements").value(5));
    }

    @Test
    void shouldReturnRequestedPage() throws Exception {
        mockMvc.perform(
                        get("/api/service-requests")
                                .param("page", "1")
                                .param("size", "5")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.number").value(1))
                .andExpect(jsonPath("$.size").value(5))
                .andExpect(jsonPath("$.numberOfElements").value(5));
    }

    @Test
    void shouldReturnServiceRequestsOrderedAscendingById()
            throws Exception {

        Page<ServiceRequest> expectedPage = repository.findAll(
                PageRequest.of(
                        0,
                        2,
                        Sort.by(Sort.Direction.ASC, "id")
                )
        );

        Long firstExpectedId = expectedPage.getContent()
                .get(0)
                .getId();

        Long secondExpectedId = expectedPage.getContent()
                .get(1)
                .getId();

        mockMvc.perform(
                        get("/api/service-requests")
                                .param("page", "0")
                                .param("size", "2")
                                .param("sort", "id,asc")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].id")
                        .value(firstExpectedId))
                .andExpect(jsonPath("$.content[1].id")
                        .value(secondExpectedId));
    }

    @Test
    void shouldReturnServiceRequestsOrderedDescendingById()
            throws Exception {

        Page<ServiceRequest> expectedPage = repository.findAll(
                PageRequest.of(
                        0,
                        2,
                        Sort.by(Sort.Direction.DESC, "id")
                )
        );

        Long firstExpectedId = expectedPage.getContent()
                .get(0)
                .getId();

        Long secondExpectedId = expectedPage.getContent()
                .get(1)
                .getId();

        mockMvc.perform(
                        get("/api/service-requests")
                                .param("page", "0")
                                .param("size", "2")
                                .param("sort", "id,desc")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].id")
                        .value(firstExpectedId))
                .andExpect(jsonPath("$.content[1].id")
                        .value(secondExpectedId));
    }

    @Test
    void shouldReturnOkWhenGettingServiceRequestById() throws Exception {
        ServiceRequest request = new ServiceRequest();

        request.setTitle("Teste de consulta por ID");
        request.setDescription(
                "Solicitação criada pelo teste de integração"
        );

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        get("/api/service-requests/" + savedRequest.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedRequest.getId()))
                .andExpect(jsonPath("$.title").value(
                        "Teste de consulta por ID"
                ))
                .andExpect(jsonPath("$.description").value(
                        "Solicitação criada pelo teste de integração"
                ))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void shouldReturnNotFoundWhenGettingNonExistingServiceRequest()
            throws Exception {

        mockMvc.perform(get("/api/service-requests/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value(
                        "Service request not found: 9999"
                ));
    }

    @Test
    void shouldReturnCreatedWhenCreatingValidServiceRequest()
            throws Exception {

        mockMvc.perform(
                        post("/api/service-requests")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "title": "Teste automatizado",
                                            "description": "Solicitação criada através de teste automatizado"
                                        }
                                        """)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value(
                        "Teste automatizado"
                ))
                .andExpect(jsonPath("$.description").value(
                        "Solicitação criada através de teste automatizado"
                ))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void shouldReturnOkWhenUpdatingValidServiceRequest()
            throws Exception {

        ServiceRequest request = new ServiceRequest();

        request.setTitle("Teste de atualização");
        request.setDescription(
                "Solicitação criada para teste de atualização"
        );

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        put("/api/service-requests/" + savedRequest.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "title": "Teste de atualização automatizada",
                                            "description": "Descrição atualizada pelo teste automatizado"
                                        }
                                        """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedRequest.getId()))
                .andExpect(jsonPath("$.title").value(
                        "Teste de atualização automatizada"
                ))
                .andExpect(jsonPath("$.description").value(
                        "Descrição atualizada pelo teste automatizado"
                ))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void shouldReturnOkWhenUpdatingServiceRequestStatus()
            throws Exception {

        ServiceRequest request = new ServiceRequest();

        request.setTitle("Teste de alteração de status");
        request.setDescription(
                "Solicitação criada para teste de status"
        );

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        patch(
                                "/api/service-requests/"
                                        + savedRequest.getId()
                                        + "/status"
                        )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "status": "IN_PROGRESS"
                                        }
                                        """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedRequest.getId()))
                .andExpect(jsonPath("$.title").value(
                        "Teste de alteração de status"
                ))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingStatusOfNonExistingServiceRequest()
            throws Exception {

        mockMvc.perform(
                        patch("/api/service-requests/9999/status")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "status": "IN_PROGRESS"
                                        }
                                        """)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value(
                        "Service request not found: 9999"
                ));
    }

    @Test
    void shouldReturnBadRequestWhenUpdatingStatusWithNullValue()
            throws Exception {

        ServiceRequest request = new ServiceRequest();

        request.setTitle("Solicitação válida");
        request.setDescription("Descrição válida");

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        patch(
                                "/api/service-requests/"
                                        + savedRequest.getId()
                                        + "/status"
                        )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "status": null
                                        }
                                        """)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(
                        containsString("status:")
                ));
    }

    @Test
    void shouldReturnBadRequestWhenTitleIsBlank()
            throws Exception {

        mockMvc.perform(
                        post("/api/service-requests")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "title": "",
                                            "description": "Computador não liga"
                                        }
                                        """)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(
                        containsString("title:")
                ));
    }

    @Test
    void shouldReturnBadRequestWhenDescriptionIsBlank()
            throws Exception {

        mockMvc.perform(
                        post("/api/service-requests")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "title": "Computador não liga",
                                            "description": ""
                                        }
                                        """)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(
                        containsString("description:")
                ));
    }

    @Test
    void shouldReturnBadRequestWhenUpdatingWithBlankTitle()
            throws Exception {

        ServiceRequest request = new ServiceRequest();

        request.setTitle("Solicitação válida");
        request.setDescription("Descrição válida");

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        put("/api/service-requests/" + savedRequest.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "title": "",
                                            "description": "Computador não liga"
                                        }
                                        """)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(
                        containsString("title:")
                ));
    }

    @Test
    void shouldReturnBadRequestWhenUpdatingWithBlankDescription()
            throws Exception {

        ServiceRequest request = new ServiceRequest();

        request.setTitle("Solicitação válida");
        request.setDescription("Descrição válida");

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        put("/api/service-requests/" + savedRequest.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "title": "Computador não liga",
                                            "description": ""
                                        }
                                        """)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(
                        containsString("description:")
                ));
    }

    @Test
    void shouldReturnConflictWhenTransitioningFromPendingToCompleted()
            throws Exception {

        ServiceRequest request = new ServiceRequest();

        request.setTitle("Teste de transição inválida");
        request.setDescription("Solicitação em PENDING");

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        patch(
                                "/api/service-requests/"
                                        + savedRequest.getId()
                                        + "/status"
                        )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "status": "COMPLETED"
                                        }
                                        """)
                )
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message").value(
                        containsString("Transição de status inválida")
                ));
    }

    @Test
    void shouldReturnConflictWhenTransitioningFromInProgressToPending()
            throws Exception {

        ServiceRequest request = new ServiceRequest();

        request.setTitle("Teste de retorno de status");
        request.setDescription("Solicitação em andamento");
        request.setStatus(ServiceRequestStatus.IN_PROGRESS);

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        patch(
                                "/api/service-requests/"
                                        + savedRequest.getId()
                                        + "/status"
                        )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "status": "PENDING"
                                        }
                                        """)
                )
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message").value(
                        containsString("Transição de status inválida")
                ));
    }

    @Test
    void shouldReturnConflictWhenChangingCompletedServiceRequestStatus()
            throws Exception {

        ServiceRequest request = new ServiceRequest();

        request.setTitle("Solicitação concluída");
        request.setDescription("Solicitação finalizada");
        request.setStatus(ServiceRequestStatus.COMPLETED);

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        patch(
                                "/api/service-requests/"
                                        + savedRequest.getId()
                                        + "/status"
                        )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "status": "IN_PROGRESS"
                                        }
                                        """)
                )
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message").value(
                        containsString("solicitação finalizada")
                ));
    }

    @Test
    void shouldReturnConflictWhenChangingCancelledServiceRequestStatus()
            throws Exception {

        ServiceRequest request = new ServiceRequest();

        request.setTitle("Solicitação cancelada");
        request.setDescription("Solicitação cancelada");
        request.setStatus(ServiceRequestStatus.CANCELLED);

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        patch(
                                "/api/service-requests/"
                                        + savedRequest.getId()
                                        + "/status"
                        )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "status": "IN_PROGRESS"
                                        }
                                        """)
                )
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message").value(
                        containsString("solicitação finalizada")
                ));
    }

    @Test
    void shouldReturnConflictWhenUpdatingCompletedServiceRequest()
            throws Exception {

        ServiceRequest request = new ServiceRequest();

        request.setTitle("Solicitação concluída");
        request.setDescription("Solicitação finalizada");
        request.setStatus(ServiceRequestStatus.COMPLETED);

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        put("/api/service-requests/" + savedRequest.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "title": "Tentativa de alteração",
                                            "description": "Não deveria ser alterada"
                                        }
                                        """)
                )
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message").value(
                        containsString("solicitação finalizada")
                ));
    }

    @Test
    void shouldReturnConflictWhenUpdatingCancelledServiceRequest()
            throws Exception {

        ServiceRequest request = new ServiceRequest();

        request.setTitle("Solicitação cancelada");
        request.setDescription("Solicitação cancelada");
        request.setStatus(ServiceRequestStatus.CANCELLED);

        ServiceRequest savedRequest = repository.save(request);

        assertNotNull(savedRequest.getId());

        mockMvc.perform(
                        put("/api/service-requests/" + savedRequest.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                            "title": "Tentativa de alteração",
                                            "description": "Não deveria ser alterada"
                                        }
                                        """)
                )
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message").value(
                        containsString("solicitação finalizada")
                ));
    }
}
