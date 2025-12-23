package br.com.computador.service;

import br.com.computador.dto.ClienteDTO;
import br.com.computador.entity.Cliente;
import br.com.computador.exception.ClienteNaoEncontradoException;
import br.com.computador.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public ClienteDTO salvarCliente (ClienteDTO clienteDTO){
     var cliente = modelMapper.map(clienteDTO, Cliente.class);
     var clienteSalvo = clienteRepository.save(cliente);
     return modelMapper.map(clienteSalvo, ClienteDTO.class);
    }

    public Page<ClienteDTO>listarClientes(Pageable pageable){
        return clienteRepository.findAll(pageable).map(cliente -> modelMapper.map(cliente, ClienteDTO.class));

    }

    public ClienteDTO buscarPorId(Long id){
        var cliente = clienteRepository.findById(id).orElseThrow(
                ()-> new ClienteNaoEncontradoException("Cliente nao encontrado com o id: " + id) );
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public ClienteDTO buscarPorTelefone(String telefone){
        var cliente =  clienteRepository.findByTelefone(telefone).orElseThrow(
                ()-> new ClienteNaoEncontradoException("Cliente nao encontrado com o telefone: " + telefone));
        return modelMapper.map(cliente, ClienteDTO.class);

    }

    public ClienteDTO buscarPorEmail(String email){
        return clienteRepository.findByEmail(email).map(cliente ->modelMapper.map(cliente,ClienteDTO.class))
                .orElseThrow(  ()-> new ClienteNaoEncontradoException("Cliente nao encontrado com o id: " + email));
    }

    @Transactional
    public ClienteDTO atualizarClientes(Long id, ClienteDTO clienteDTO){
        var cliente = clienteRepository.findById(id).orElseThrow(
                ()-> new ClienteNaoEncontradoException("Cliente nao encontrado com o id: " + id) );
        modelMapper.map(clienteDTO,cliente);
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public void excluirCliente(Long id){
        var cliente = clienteRepository.findById(id).orElseThrow(
                ()-> new ClienteNaoEncontradoException("Cliente nao encontrado com o id: " + id) );
        clienteRepository.delete(cliente);
    }


}
