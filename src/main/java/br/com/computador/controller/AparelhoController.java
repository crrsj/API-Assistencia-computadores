package br.com.computador.controller;

import br.com.computador.dto.AparelhoDTO;
import br.com.computador.dto.AtualizarDTO;
import br.com.computador.dto.AtualizarStatus;
import br.com.computador.entity.Aparelho;
import br.com.computador.service.AparelhoService;
import br.com.computador.service.RelatorioService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/aparelhos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AparelhoController {

    private final RelatorioService relatorioService;
    private final AparelhoService aparelhoService;

    @PostMapping("/{clienteId}")
    public ResponseEntity<AparelhoDTO>salvarAparelho(@PathVariable Long clienteId, @RequestBody @Valid AparelhoDTO aparelhoDTO){
        var aparelho = aparelhoService.salvarAparelho(clienteId, aparelhoDTO);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(aparelho.getId()).toUri();
        return ResponseEntity.created(uri).body(aparelho);
    }

    @GetMapping("/aparelho/{id}")
    public ResponseEntity<byte[]> baixarPdfAparelho(@PathVariable Long id) {
        // Busca o aparelho no banco (assumindo que você tem o AparelhoRepository)
        AparelhoDTO aparelho = aparelhoService.buscarPorId(id);


        byte[] pdf = relatorioService.gerarPdfAparelho(aparelho);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Define o nome do arquivo baixado
        headers.setContentDispositionFormData("attachment", "Recibo_Aparelho_" + id + ".pdf");

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AparelhoDTO>>buscarAparelhos(
            @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable){
        Page<AparelhoDTO>aparelhos = aparelhoService.buscarAparelhos(pageable);
        return ResponseEntity.ok(aparelhos);

    }
    @GetMapping("/{id}")
    public ResponseEntity<AparelhoDTO>buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(aparelhoService.buscarPorId(id));
    }

    @GetMapping("/servicos")
    public ResponseEntity<Page<AparelhoDTO>>buscarServicos(Pageable pageable){
        Page<AparelhoDTO> aparelhos = aparelhoService.buscarServicos(pageable);
        return ResponseEntity.ok(aparelhos);
    }

    @GetMapping("/orcamentos")
    public  ResponseEntity<Page<AparelhoDTO>>buscarOrcamentos(Pageable pageable){
        Page<AparelhoDTO> aparelhos = aparelhoService.buscarOrcamentos(pageable);
        return ResponseEntity.ok(aparelhos);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<AtualizarDTO>atualizarOrcamento(@PathVariable Long id, @RequestBody AtualizarDTO atualizarDTO){
        return ResponseEntity.ok(aparelhoService.atualizarOrcamento(id,atualizarDTO));

    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<AtualizarStatus>atualizarStatus(@PathVariable Long id,@RequestBody AtualizarStatus status){
        return ResponseEntity.ok(aparelhoService.atualizarOrcamentoEdataEntregaEvalor(id,status));
    }


    @GetMapping("/ordem")
    public ResponseEntity<AparelhoDTO>buscarPorOrdemDeServico(@PathParam("ordemServico") Integer ordemServico){
        return ResponseEntity.ok(aparelhoService.buscarPorOrdemDeServico(ordemServico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>excluirAparelhos(@PathVariable Long id){
        aparelhoService.excluirAparelho(id);
        return ResponseEntity.noContent().build();
    }
}
