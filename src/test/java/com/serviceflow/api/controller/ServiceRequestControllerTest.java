package com.serviceflow.api.controller;

import com.serviceflow.api.entity.ServiceRequest;
import com.serviceflow.api.repository.ServiceRequestRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

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
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].title").exists())
                .andExpect(jsonPath("$[0].description").exists())
                .andExpect(jsonPath("$[0].status").exists())
                .andExpect(jsonPath("$[0].createdAt").exists());
    }

    @Test
    void shouldReturnOkWhenGettingServiceRequestById() throws Exception {
        ServiceRequest request = new ServiceRequest();

        request.setTitle("Teste de consulta por ID");
        request.setDescription("Solicitação criada pelo teste de integração");

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
}