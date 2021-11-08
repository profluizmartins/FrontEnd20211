package br.ufg.inf.fs;

import br.ufg.inf.fs.entities.*;
import br.ufg.inf.fs.enums.Grupo;
import br.ufg.inf.fs.enums.TipoPagamento;
import br.ufg.inf.fs.enums.UnidadeMedida;
import br.ufg.inf.fs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("dev")

public class Config implements CommandLineRunner {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RegraRepository regraRepository;
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private EntradaRepository entradaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        /*
         * INSERIR DADOS INICIAS NO BANCO DE DADOS
         *
         */

        Pessoa p1 = new Pessoa(null, "Ian", "Goiania");
        Pessoa p2 = new Pessoa(null, "Gabriel", "Goiania");
        Pessoa p3 = new Pessoa(null, "Paulo", "Goiania");

        pessoaRepository.save(p1);
        pessoaRepository.save(p2);
        pessoaRepository.save(p3);

        PessoaJuridica pj1 = new PessoaJuridica(null, "11111111000111","Comercio", p1);
        PessoaJuridica pj2 = new PessoaJuridica(null, "22222222000222","Hotelaria", p2);
        
        pessoaJuridicaRepository.save(pj1);
        pessoaJuridicaRepository.save(pj2);

        Entrada et1 = new Entrada(null, LocalDate.now(), pj1, true);    
        Entrada et2 = new Entrada(null, LocalDate.now(), pj2, true);

        entradaRepository.save(et1);
        entradaRepository.save(et2);

        Venda v1 = new Venda(null, p1, LocalDate.now(), true, TipoPagamento.get(1), 2);

        vendaRepository.save(v1);

        Regra r1 = new Regra("Comum");
        Regra r2 = new Regra("Admin");

        List<Regra> regras = new ArrayList<>();
        regras.add(r1);
        regras.add(r2);

        regraRepository.save(r1);
        regraRepository.save(r2);

        Usuario u1 = new Usuario("ian", "senha", p1, regras);
        usuarioRepository.save(u1);

        List<Regra> regras2 = new ArrayList<>();

        Usuario u2 = new Usuario("gabriel", "senha",p2, regras2);
        
        usuarioRepository.save(u2);
        
        
        Produto prod1 = new Produto(null, UnidadeMedida.UNIDADE, Grupo.INFORMATICA,"Mouse sem fio","Mouse sem fio",20.00);
        Produto prod2 = new Produto(null, UnidadeMedida.UNIDADE, Grupo.INFORMATICA,"Teclado","Teclado",150.00);
        Produto prod3 = new Produto(null, UnidadeMedida.UNIDADE, Grupo.INFORMATICA,"Monitor","Monitor",500.00);
        Produto prod4 = new Produto(null, UnidadeMedida.UNIDADE, Grupo.INFORMATICA,"Processador","Processador",999.00);
        produtoRepository.save(prod1);
        produtoRepository.save(prod2);
        produtoRepository.save(prod3);
        produtoRepository.save(prod4);

    }
}