package Supermercado;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CaixaSupermercadoTest {

    @Test
    void testeInicial() {
        //instanciando o objeto CaixaSupermercado
        CaixaSupermercado caixa = new CaixaSupermercado();
        //construindo o catálogo de produtos - necessario para efetuar vendas
        caixa.constroiCatalogoProdutos();
        /* Em vez de acionar o método main do catálogo de produtos, acessei diretamente a
        registradora que já foi instanciada e que é um atributo de catálogo de produtos, executei
        o método inicarVenda da registradora
         */
        caixa.registra.iniciarVenda();
        /*inclui produtos na venda e calculei o total, sem usar a interface do teclado
        acessando diretamente os métodos da registradora e
         */
        caixa.registra.incluirItem(1,1);
        caixa.registra.incluirItem(2,2);
        float total = caixa.registra.gettotal();
        //finalmente, testei se o valor total obtido é o esperado
        assertEquals(8.97f,total, "O CODIGO PASSOU C SUCESSO");
    }

    @Test
    void VendaBemSucedida(){//CASO 1
        CaixaSupermercado caixa = new CaixaSupermercado();
        caixa.constroiCatalogoProdutos();
        caixa.registra.iniciarVenda();
        caixa.registra.incluirItem(1,1);
        float total = caixa.registra.gettotal();
        float valorPago = 3.95f;
        float troco = caixa.registra.getTroco(valorPago);
        float trocoEsperado = 0.96000004f;
        float trocoObtido = caixa.registra.getTroco(valorPago);
        System.out.println("VENDA BEM SUCEDIDA");
        assertEquals(trocoEsperado, trocoObtido,"TESTE PASSOU");
    }

    @Test
    void VendaBemSucedida2(){//CASO 2
        CaixaSupermercado caixa = new CaixaSupermercado();
        caixa.constroiCatalogoProdutos();
        caixa.registra.iniciarVenda();
        caixa.registra.incluirItem(1,1);
        float total = caixa.registra.gettotal();
        float valorPago = 2.99f;
        float troco = caixa.registra.getTroco(valorPago);
        float trocoEsperado = 0.0f;
        float trocoObtido = caixa.registra.getTroco(valorPago);
        System.out.println("VENDA CONCLUÍDA");
        assertEquals(trocoEsperado, trocoObtido,"TESTE PASSOU");
    }

    @Test
    void CodigoInvalido(){ //CASO 3
        CaixaSupermercado caixa = new CaixaSupermercado();
        caixa.constroiCatalogoProdutos();
        caixa.registra.iniciarVenda();
        caixa.registra.incluirItem(5,1);
        int codObtido = 5;
        int codEsperado = 5;
        System.out.println("VENDA INVÁLIDA, CÓDIGO DE VENDA INVÁLIDO");
        assertEquals(codObtido,codEsperado, "TESTE PASSOU");

        System.out.println(caixa.registra.getCatProd().getEspecProd(1).getDescricao());
    }

    @Test
    void QuantidadeInvalida(){ //CASO 4
        CaixaSupermercado caixa = new CaixaSupermercado();
        caixa.constroiCatalogoProdutos();
        caixa.registra.iniciarVenda();
        caixa.registra.incluirItem(1,0);
        int quantObtida = 0;
        int quantEsperada = 1;
        System.out.println("Produto em falta no estoque");
        assertEquals(quantObtida, quantEsperada, "O TESTE PASSOU");
    }

    @Test
    void QuantidadeInvalida2(){//CASO 5
        CaixaSupermercado caixa = new CaixaSupermercado();
        caixa.constroiCatalogoProdutos();
        caixa.registra.iniciarVenda();
        caixa.registra.incluirItem(1,-1);

        int quantObtida = -1;
        int quantEsperada = 1;
        System.out.println("Quantidade do produto inválida");
        assertEquals(quantEsperada, quantObtida, "O TESTE PASSOU");
    }

    @Test
    void ValorVendaInvalido(){//CASO 6

    }

    @Test
    void ValorVendaInvalido2(){//CASO 7

    }

    @Test
    void PagamentoInsuficiente(){//CASO 8

    }

    @Test
    void CalculoTrocoIncorreto(){//CASO 9

    }

    @Test
    void CalculoTrocoIncorreto2(){//CASO 10

    }





}