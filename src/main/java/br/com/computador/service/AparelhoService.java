package br.com.computador.service;

import br.com.computador.dto.AparelhoDTO;
import br.com.computador.dto.AtualizarDTO;
import br.com.computador.dto.AtualizarStatus;
import br.com.computador.entity.Aparelho;
import br.com.computador.enums.StatusOrcamento;
import br.com.computador.exception.AparelhoNaoEncontradoException;
import br.com.computador.exception.ClienteNaoEncontradoException;
import br.com.computador.repository.AparelhoRepository;
import br.com.computador.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AparelhoService {

    private final AparelhoRepository aparelhoRepository;
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public AparelhoDTO salvarAparelho(Long clienteId, AparelhoDTO aparelhoDTO) {
        var cliente = clienteRepository.findById(clienteId).orElseThrow(
                () -> new ClienteNaoEncontradoException("Cliente nao encontrado com o id: " + clienteId));
        var aparelho = modelMapper.map(aparelhoDTO, Aparelho.class);
        aparelho.setCliente(cliente);
        var aparelhoSalvo = aparelhoRepository.save(aparelho);
        return modelMapper.map(aparelhoSalvo, AparelhoDTO.class);
    }

    public Page<AparelhoDTO> buscarAparelhos(Pageable pageable) {
        return aparelhoRepository.findAll(pageable).map(aparelho -> modelMapper.map(aparelho, AparelhoDTO.class));
    }

    public AparelhoDTO buscarPorId(Long id) {
        var aparelho = aparelhoRepository.findById(id).orElseThrow(
                () -> new AparelhoNaoEncontradoException("Aparelho não encontrado com o id: " + id));
        return modelMapper.map(aparelho, AparelhoDTO.class);
    }

    public Page<AparelhoDTO> buscarOrcamentos(Pageable pageable) {
        Page<Aparelho> aparelhos = aparelhoRepository.findByStatus(StatusOrcamento.ORCAMENTO, pageable);
        return aparelhos.map(aparelho -> modelMapper.map(aparelho, AparelhoDTO.class));

    }

    public Page<AparelhoDTO> buscarServicos(Pageable pageable) {
        Page<Aparelho> aparelhos = aparelhoRepository.findByStatus(StatusOrcamento.SERVICO, pageable);
        return aparelhos.map(aparelho -> modelMapper.map(aparelho, AparelhoDTO.class));
    }

    @Transactional
    public AtualizarStatus atualizarOrcamentoEdataEntregaEvalor(Long id,AtualizarStatus dto){
       var aparelho = aparelhoRepository.findById(id).orElseThrow(
                () -> new AparelhoNaoEncontradoException("Aparelho não encontrado com o id: " + id));
       modelMapper.map(dto,aparelho);
       var aparelhoAtualizado = aparelhoRepository.save(aparelho);
       return modelMapper.map(aparelhoAtualizado, AtualizarStatus.class);
    }


    @Transactional
    public AtualizarDTO atualizarOrcamento(Long id, AtualizarDTO atualizarDTO){
        var aparelho = aparelhoRepository.findById(id).orElseThrow(
                () -> new AparelhoNaoEncontradoException("Aparelho não encontrado com o id " + id));
        modelMapper.map(atualizarDTO,aparelho);
        var aparelhoAtualizado = aparelhoRepository.save(aparelho);
        return modelMapper.map(aparelhoAtualizado, AtualizarDTO.class);
    }

    public AparelhoDTO buscarPorOrdemDeServico(Integer ordemServico){
     var  aparelho = aparelhoRepository.findByOrdemServico(ordemServico).orElseThrow(() -> new AparelhoNaoEncontradoException("Aparelho não encontrado com o a ordem de serviço: " + ordemServico));
     return modelMapper.map(aparelho, AparelhoDTO.class);
    }

    public void excluirAparelho(Long id){
        var aparelho = aparelhoRepository.findById(id).orElseThrow(
                () -> new AparelhoNaoEncontradoException("Aparelho não encontrado com o id: " + id));
        aparelhoRepository.delete(aparelho);
    }

}