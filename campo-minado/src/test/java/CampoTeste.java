
import br.com.leno.exception.ExplosaoException;
import br.com.leno.model.Campo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CampoTeste {

    private Campo campo;

    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoDistancia1Esquerda() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adcionarVizinho(vizinho);
        Assertions.assertTrue(resultado);

    }

    @Test
    void testeVizinhoDistancia1Direita() {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adcionarVizinho(vizinho);
        Assertions.assertTrue(resultado);

    }

    @Test
    void testeVizinhoDistancia1EmCima() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adcionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1EmBaixo() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adcionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }


    @Test
    void testeVizinhoDistancia2() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adcionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adcionarVizinho(vizinho);
        Assertions.assertFalse(resultado);

    }


    @Test
    void valorPadraoAtributoMarcodo() {
        Assertions.assertFalse(campo.isMarcado());

    }

    @Test
    void alternarMarcacao() {
        campo.alternarMarcacao();
        Assertions.assertTrue(campo.isMarcado());
    }

    @Test
    void alternarMarcacaoDuasVezes() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        Assertions.assertFalse(campo.isMarcado());
    }

    @Test
    void abrirCampoNaoMinadoNaoMarcado() {
        Assertions.assertTrue(campo.abrir());
    }

    @Test
    void abrirCampoNaoMinadoEmarcado() {
        campo.alternarMarcacao();
        Assertions.assertFalse(campo.abrir());
    }

    @Test
    void abrirCampoMinadoEmarcado() {
        campo.alternarMarcacao();
        campo.minar();
        Assertions.assertFalse(campo.abrir());
    }

    @Test
    void abrirCampoMinadoEnaoMarcado() {
        campo.minar();
        Assertions.assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });

    }

    @Test
    void abrirComVizinho1() {

        Campo campo11 = new Campo(1,1);
        Campo campo22 = new Campo(2,2);
        campo22.adcionarVizinho(campo11);
        campo.adcionarVizinho(campo22);
        campo.abrir();

        Assertions.assertTrue(campo22.isAberto() && campo11.isAberto());

    }

    @Test
    void abrirComVizinho2() {

        Campo campo11 = new Campo(1,1);
        Campo campo12 = new Campo(1,1);
        campo12.minar();

        Campo campo22 = new Campo(2,2);
        campo22.adcionarVizinho(campo11);
        campo22.adcionarVizinho(campo12);


        campo.adcionarVizinho(campo22);
        campo.abrir();

        Assertions.assertTrue(campo22.isAberto() && campo11.isFechado());

    }




}
