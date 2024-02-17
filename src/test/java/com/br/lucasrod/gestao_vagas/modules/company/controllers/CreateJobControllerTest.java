package com.br.lucasrod.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.br.lucasrod.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.br.lucasrod.gestao_vagas.modules.company.entities.CompanyEntity;
import com.br.lucasrod.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.br.lucasrod.gestao_vagas.utils.TestUtils;

@SuppressWarnings("null")

@RunWith(SpringRunner.class) //sinaliza que a classe é como um servidor para rodar/subir a aplicação
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //cria uma porta randomica para o servidor rodar 
@ActiveProfiles("test") //sinaliza para o Spring que deve ser usado o app.prop de test
public class CreateJobControllerTest {
    
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup () {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity()) //mock do spring security
                .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception{

        var company = CompanyEntity.builder()
                .description("DESCRIPTION_COMPANY")
                .email("email@company.com")
                .password("1234567890")
                .name("NAME_COMPANY")
                .username("USERNAME_COMPANY")
                .cnpj("000000000000").build();
        

        company = companyRepository.saveAndFlush(company); //SaveAndFlush solicita que salve imediatamente sem passar por toda a requisicao
        var createdJob = CreateJobDTO.builder()
            .benefits("BENEFITS_TEST")
            .level("LEVEL_TEST")
            .description("DESCRIPTION_TEST")
            .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJSON(createdJob))
            .header("Authorization", TestUtils.generateToken(company.getId(), "Rod_123")))        
            .andExpect(MockMvcResultMatchers.status().isOk()); //AndExpect-> Eu espero que esse procedimento retorne status OK

        System.out.println(result);

    }

    @Test
    public void should_not_be_able_to_create_a_new_job() throws Exception{

        var createdJob = CreateJobDTO.builder()
            .benefits("BENEFITS_TEST")
            .level("LEVEL_TEST")
            .description("DESCRIPTION_TEST")
            .build();
       
        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJSON(createdJob))
            .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "Rod_123")))
            .andExpect(MockMvcResultMatchers.status().isBadRequest()); 
 
    }

}
