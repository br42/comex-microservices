package br.com.alura.comex.dto;

public record DadosValidarToken(
    String token,
    String nome,
    boolean ativo,
    ResultadoValidacaoEnum tipo
) {
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
}
