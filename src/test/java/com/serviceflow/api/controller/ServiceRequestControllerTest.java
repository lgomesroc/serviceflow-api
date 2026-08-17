package com.serviceflow.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ServiceRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

        mockMvc.perform(get("/api/service-requests/14"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(14))
                .andExpect(jsonPath("$.title").value("Computador não liga - atualizado"))
                .andExpect(jsonPath("$.description").value(
                        "O computador continua sem iniciar após pressionar o botão"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void shouldReturnNotFoundWhenGettingNonExistingServiceRequest() throws Exception {

        mockMvc.perform(get("/api/service-requests/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value(
                        "Service request not found: 9999"));
    }

    @Test
    void shouldReturnCreatedWhenCreatingValidServiceRequest() throws Exception {

        mockMvc.perform(post("/api/service-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Teste automatizado",
                                    "description": "Solicitação criada através de teste automatizado"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Teste automatizado"))
                .andExpect(jsonPath("$.description").value(
                        "Solicitação criada através de teste automatizado"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void shouldReturnOkWhenUpdatingValidServiceRequest() throws Exception {

        mockMvc.perform(put("/api/service-requests/15")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Teste de atualização automatizada",
                                    "description": "Descrição atualizada pelo teste automatizado"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(15))
                .andExpect(jsonPath("$.title").value(
                        "Teste de atualização automatizada"))
                .andExpect(jsonPath("$.description").value(
                        "Descrição atualizada pelo teste automatizado"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingNonExistingServiceRequest() throws Exception {

        mockMvc.perform(put("/api/service-requests/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Teste inexistente",
                                    "description": "Solicitação que não existe"
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value(
                        "Service request not found: 9999"));
    }

    @Test
    void shouldReturnBadRequestWhenTitleIsBlank() throws Exception {

        mockMvc.perform(post("/api/service-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "",
                                    "description": "Computador não liga"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(containsString("title:")));
    }

    @Test
    void shouldReturnBadRequestWhenDescriptionIsBlank() throws Exception {

        mockMvc.perform(post("/api/service-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Computador não liga",
                                    "description": ""
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(containsString("description:")));
    }

    @Test
    void shouldReturnBadRequestWhenUpdatingWithBlankTitle() throws Exception {

        mockMvc.perform(put("/api/service-requests/14")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "",
                                    "description": "Computador não liga"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(containsString("title:")));
    }

    @Test
    void shouldReturnBadRequestWhenUpdatingWithBlankDescription() throws Exception {

        mockMvc.perform(put("/api/service-requests/14")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Computador não liga",
                                    "description": ""
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(containsString("description:")));
    }
}
