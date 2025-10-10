package br.com.alura.comex.model;

import br.com.alura.comex.dto.DadosValidarToken;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TokenValidado {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Id private Long id;

    private String nome;

    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private ResultadoValidacaoEnum tipo;
    
    @Column(unique = true)
    private String token;

    public TokenValidado () {}

    public TokenValidado(String nome, boolean ativo, ResultadoValidacaoEnum tipo, String token) {
        this.nome = nome;
        this.ativo = ativo;
        this.tipo = tipo;
        this.token = token;
    }

    public enum ResultadoValidacaoEnum {
        AUTH(1),
        BLOCKED(0);
        
        private final int value;
        
        ResultadoValidacaoEnum(int value) {
            this.value = value;
        }
        
        public int getValue() {
          return value;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ResultadoValidacaoEnum getTipo() {
        return tipo;
    }

    public void setTipo(ResultadoValidacaoEnum tipo) {
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static TokenValidado fromDadosValidarToken(DadosValidarToken dadosToken) {
        return new TokenValidado(dadosToken.nome(), dadosToken.ativo(), ResultadoValidacaoEnum.valueOf(dadosToken.tipo().name()), dadosToken.token());
    }

    @Override
    public String toString() {
        return "TokenValidado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ativo='" + ativo + '\'' +
                ", nome='" + nome + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}