package br.com.escuderodev.api.depoimento;

import br.com.escuderodev.api.depoimento.controller.DepoimentoController;
import br.com.escuderodev.api.depoimento.models.depoimento.DadosAtualizaDepoimento;
import br.com.escuderodev.api.depoimento.models.depoimento.DadosCadastroDepoimento;

import br.com.escuderodev.api.depoimento.models.depoimento.Depoimento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepoimentoControllerTest extends DepoimentoApplicationTests {
    private MockMvc mockMvc;

    @Autowired
    private DepoimentoController depoimentoController;

    @BeforeAll
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(depoimentoController).build();
    }

    @Test
    public void listarDepoimento_retornarStatusOK200() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/depoimentos"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void cadastrarDepoimento_retornarStatusCreated201() throws Exception {
        DadosCadastroDepoimento dados = new DadosCadastroDepoimento(
                "https://www.carpemundi.com.br/wp-content/uploads/2022/01/frases-de-viagem-cm.jpg",
                "Tudo certo e nada resolvido",
                "Maria");

        Depoimento depoimento = new Depoimento(dados);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(depoimento);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", Matchers.containsString("http://localhost/depoimento/10")));
    }

    @Test
    public void atualizarDepoimento_retornarStatusOK200() throws Exception {
        DadosAtualizaDepoimento dados = new DadosAtualizaDepoimento(
                10L,
                "https://www.carpemundi.com.br/wp-content/uploads/2022/01/frases-de-viagem-cm.jpg",
                "Tudo certo e nada resolvido",
                "Maria de Fátima");

        Depoimento depoimento = new Depoimento();
        depoimento.atualizaDados(dados);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(depoimento);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void excluirDepoimento_retornarStatusNoContent() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/depoimentos/10"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
