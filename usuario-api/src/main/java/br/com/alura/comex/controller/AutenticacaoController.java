package br.com.alura.comex.controller;

import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.comex.dto.DadosAutenticacao;
import br.com.alura.comex.dto.DadosValidarToken;
import br.com.alura.comex.dto.RequestClienteCadastro;
import br.com.alura.comex.dto.DadosValidarToken.ResultadoValidacaoEnum;
import br.com.alura.comex.infra.security.DadosTokenJWT;
import br.com.alura.comex.infra.security.TokenService;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Usuario;
import br.com.alura.comex.repository.UsuarioRepository;
import br.com.alura.comex.service.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    ClienteService clienteService;
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarCliente(@RequestBody @NotNull @Valid RequestClienteCadastro dados, BindingResult result) {
        if(result.hasErrors()) {
            String mensagem = "";
            List<FieldError> fieldErrors = result.getFieldErrors();
            
            for (FieldError fieldError: fieldErrors) {
                if (fieldError != null) {
                    mensagem += "Erro: [" + fieldError.getObjectName() + "." + fieldError.getField() + "] " + fieldError.getDefaultMessage() + "\n";
                }
            }
            return ResponseEntity.badRequest().body(mensagem);
        }

        String email = dados.getEmail();
        String senha = dados.getSenha();
        String hashSenha = passwordEncoder.encode(senha);
        
        Usuario usuario = new Usuario(email, hashSenha);
        
        Cliente cliente = new Cliente(dados.getCpf(), dados.getNome(),
            dados.getEmail(), dados.getTelefone(), dados.getLogradouro(),
            dados.getBairro(), dados.getCidade(), dados.getEstado(),
            dados.getCep(), usuario);

        usuarioRepository.save(usuario);
        clienteService.cadastrar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/cadastrousuario")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @NotNull @Valid DadosAutenticacao dados, BindingResult result) {
        if(result.hasErrors()) {
            String mensagem = "";
            List<FieldError> fieldErrors = result.getFieldErrors();
            
            for (FieldError fieldError: fieldErrors) {
                if (fieldError != null) {
                    mensagem += "Erro: [" + fieldError.getObjectName() + "." + fieldError.getField() + "] " + fieldError.getDefaultMessage() + "\n";
                }
            }
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        String login = dados.login();
        String senha = dados.senha();
        String hashSenha = passwordEncoder.encode(senha);
        
        Usuario usuario = new Usuario(login, hashSenha);
        
        usuarioRepository.save(usuario);
        
        //return ResponseEntity.ok(String.format("Hash da senha para o usuário \"%s\": \"%s\"", usuario, hashSenha));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/validartoken")
    public ResponseEntity<?> validarToken(@RequestBody String token) {
        try {
            tokenService.getSubject(token);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/validartokenfila")
    public ResponseEntity<?> validarTokenFila(@RequestBody String token) {
        try {
            String tokenUsuario = tokenService.getSubject(token);
            //Message message = new Message(tokeninfo.getBytes("UTF-8"));
            DadosValidarToken tokenDados = new DadosValidarToken(token, tokenUsuario, true, ResultadoValidacaoEnum.AUTH);
            ObjectMapper jsonMapper = new ObjectMapper();
            Message message = new Message(jsonMapper.writeValueAsBytes(tokenDados));
            rabbitTemplate.send("api.token.info", message);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/hash")
    public ResponseEntity<String> obterHashSenha(@RequestBody @NotNull DadosAutenticacao dados) {
        if (dados == null) return ResponseEntity.badRequest().body("dados{login, senha} não podem ser nulos");
        
        String senha = dados.senha();
        if (senha == null) return ResponseEntity.badRequest().body("dados.senha não podem ser nula");
        
        String usuario = dados.login();
        if (usuario == null) {
            usuario = "(null)";
        }
        
        String hashSenha = passwordEncoder.encode(senha);
        
        return ResponseEntity.ok(String.format("Hash da senha para o usuário \"%s\": \"%s\"", usuario, hashSenha));
    }
}
