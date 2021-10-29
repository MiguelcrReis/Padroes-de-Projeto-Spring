package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import one.digitalinnovation.gof.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * insejtada pelo Spring (via {@link Autowired}). Assim, essa classe
 * é um {@link Service}, ela será tratada com um <b>Singleton</b>.
 *
 * Essa classe implementa implementa essa interface que obrigatoriamente implementa todas as operações.
 * @author MiguelCrReis
 */

@Service
public class ClienteServiceImpl implements ClienteService {

    //  Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    //  Strategy: Implementar os métodos definidos na interface.
    //  Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos()  {

        // Buscar todos os clientes.

        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        //  Buscar cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {

        salvarClienteCep(cliente);

    }

    @Override
    public void atualizar(Long id, Cliente cliente) {

        //  Buscar cliente por ID, caso exista:
        Optional<Cliente> clienteDataBase = clienteRepository.findById(id);
        if (clienteDataBase.isPresent()){
            //  Verificar se o endereco do cliente já existe, pelo CEP.
            //  Caso não exista, integrar com o ViaCEP e persistir o retorno.
            // FXME Alterar o cliente, vinculando o endereco (novo ou existente).
            salvarClienteCep(cliente);
        }


    }

    @Override
    public void deletar(Long id) {

        //  Deletar cliente por ID.
        clienteRepository.deleteById(id);
    }

    //Metodo de salvar cliente com CEP para otimizar o codigo
    private void salvarClienteCep(Cliente cliente) {
        //  Verificar se o endereco do cliente já existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            //  Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        //  Inserir Cliente, vinculando o endereco, novo ou existente.
        clienteRepository.save(cliente);
    }
}
