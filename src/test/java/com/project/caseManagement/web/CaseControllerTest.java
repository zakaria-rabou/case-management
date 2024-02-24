package com.project.caseManagement.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.caseManagement.dtos.CaseDto;
import com.project.caseManagement.exceptions.CaseEntityNotFoundException;
import com.project.caseManagement.exceptions.InvalidCaseEntityException;
import com.project.caseManagement.services.CaseService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.project.caseManagement.services.CaseServiceImpl.CASE_NOT_FOUND;
import static com.project.caseManagement.services.CaseServiceImpl.ERROR_CASE_VALIDATION;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CaseService caseService;


    @Test
    void should_return_response_bad_request_when_post() throws Exception {
        mockMvc.perform(post("/Cases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void should_call_service_and_post() throws Exception {

        CaseDto caseDto = new CaseDto();
        caseDto.setDescription("description");
        caseDto.setTitle("title");

        CaseDto expected = CaseDto.builder()
            .caseId(1L)
            .title("title")
            .description("description").build();

        when(caseService.createCase(caseDto)).thenReturn(expected);

        mockMvc.perform(post("/Cases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(caseDto)))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void should_call_service_and_post_when_invalid_dto() throws Exception {

        CaseDto caseDto = new CaseDto();
        caseDto.setTitle("title");


        when(caseService.createCase(caseDto)).thenThrow(new InvalidCaseEntityException(ERROR_CASE_VALIDATION));

        mockMvc.perform(post("/Cases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(caseDto)))
            .andExpect(status().isBadRequest());
    }




    @Test
    public void should_call_account_service_and_delete() throws Exception {

        Long id = 1L;

        mockMvc.perform(delete("/Cases/{id}", id))
            .andExpect(status().is2xxSuccessful());

        Mockito.verify(caseService, Mockito.times(1)).deleteCase(id);

    }

    @Test
    public void should_return_error_delete_when_notfound() throws Exception {

        Long id = 1L;

        doThrow(new CaseEntityNotFoundException(CASE_NOT_FOUND)).when(caseService).deleteCase(id);

        mockMvc.perform(delete("/Cases/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void should_get_case_when_ok() throws Exception {
        Long caseId = 1L;

        CaseDto expected = CaseDto.builder()
            .caseId(caseId)
            .description("description")
            .title("title").build();

        when(caseService.findById(caseId)).thenReturn(expected);

        mockMvc.perform(get("/Cases/{id}", caseId))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)));

    }

    @Test
    public void should_return_error_get_when_notfound() throws Exception {

        Long id = 1L;

        doThrow(new CaseEntityNotFoundException(CASE_NOT_FOUND)).when(caseService).findById(id);

        mockMvc.perform(get("/Cases/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void should_call_service_and_put() throws Exception {
        Long caseId = 2L;
        CaseDto expected = CaseDto.builder()
            .caseId(caseId)
            .title("updatedTitle")
            .description("updatedDescription")
            .build();

        CaseDto caseDto = CaseDto.builder()
            .description("updatedDescription")
            .title("updatedTitle").build();

        when(caseService.updateCase(caseId, caseDto)).thenReturn(expected);

        mockMvc.perform(put("/Cases/{id}", caseId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(caseDto)))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void should_return_error_put_when_notfound() throws Exception {

        Long id = 1L;
        CaseDto caseDto = CaseDto.builder()
            .description("updatedDescription")
            .title("updatedTitle").build();

        doThrow(new CaseEntityNotFoundException(CASE_NOT_FOUND)).when(caseService).updateCase(id, caseDto);

        mockMvc.perform(put("/Cases/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(caseDto)))
            .andExpect(status().isNotFound());
    }

}
