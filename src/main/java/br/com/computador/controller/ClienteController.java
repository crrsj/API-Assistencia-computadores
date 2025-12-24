package br.com.computador.controller;

import br.com.computador.dto.ClienteDTO;
import br.com.computador.entity.Cliente;
import br.com.computador.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @Operation(summary = "endpoint responsável por cadastrar clientes.")
    @ApiResponse(responseCode = "201", description = " success", content = {
            @Content(mediaType = "application.json", schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<ClienteDTO>salvarClientes(@RequestBody @Valid ClienteDTO clienteDTO){
        var cliente = clienteService.salvarCliente(clienteDTO);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping
    @Operation(summary = "endpoint responsável por buscar clientes.")
    @ApiResponse(responseCode = "200", description = " success", content = {
            @Content(mediaType = "application.json", schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<Page<ClienteDTO>> listarClientes(
            @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable) {
      Page<ClienteDTO> cliente = clienteService.listarClientes(pageable);
      return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    @Operation(summary = "endpoint responsável por buscar clientes pelo id.")
    @ApiResponse(responseCode = "200", description = " success", content = {
            @Content(mediaType = "application.json", schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<ClienteDTO>buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping("/busca/telefone")
    @Operation(summary = "endpoint responsável por buscar o clientes pelo telefone.")
    @ApiResponse(responseCode = "200", description = " success", content = {
            @Content(mediaType = "application.json", schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<ClienteDTO>buscarPorTelefone(@RequestParam("telefone") String telefone){
        return ResponseEntity.ok(clienteService.buscarPorTelefone(telefone));
    }

    @GetMapping("busca/email")
    @Operation(summary = "endpoint responsável por o buscar cliente por email.")
    @ApiResponse(responseCode = "200", description = " success", content = {
            @Content(mediaType = "application.json", schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<ClienteDTO>buscarPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(clienteService.buscarPorEmail(email));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "endpoint responsável por excluir cliente.")
    @ApiResponse(responseCode = "204", description = " success", content = {
            @Content(mediaType = "application.json", schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<Void>excluirCliente(@PathVariable Long id){
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "endpoint responsável por atualizar o cliente.")
    @ApiResponse(responseCode = "200", description = " success", content = {
            @Content(mediaType = "application.json", schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<ClienteDTO>atualizarCliente(@PathVariable Long id,@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok(clienteService.atualizarClientes(id,clienteDTO));
    }
}
