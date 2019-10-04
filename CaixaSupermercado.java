package Supermercado;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CaixaSupermercado {

     Registradora registra;

    public CaixaSupermercado() {
        this.registra = new Registradora();
    }

    public static void main(String[] args) {
        CaixaSupermercado meuCaixa = new CaixaSupermercado();
        meuCaixa.constroiCatalogoProdutos();
        Scanner input = new Scanner(System.in);

        //menu de opções
        boolean termina = false;
        int opcao = 1;
        while(!termina) {
            System.out.println("Selecione 1 para nova venda, 2 para encerrar");
            opcao = input.nextInt();
            switch(opcao) {
                case 1:
                    meuCaixa.iniciarVenda(input);
                    break;
                case 2:
                    System.out.println("Tchau");
                    termina = true;
                    break;
                default:
                    System.out.println("Opcao invelida");
            }
        }
    }

    void constroiCatalogoProdutos() {


        try
        {
            FileReader arquivo = new FileReader( "src/Supermercado/ArqEspecProds" );
            BufferedReader arquivoEspecProds = new BufferedReader( arquivo );

            // Le espec prods e insere
            String linha;

            while( ( linha = arquivoEspecProds.readLine( ) ) != null )
            {
                StringTokenizer st = new StringTokenizer( linha,"," );

                try
                {
                    if( st.countTokens( ) != 3 )
                    {
                        System.err.println( "Desconsiderando linhas mal formatadas " + linha );
                        continue;
                    }
                    int id  = Integer.parseInt( st.nextToken() );
                    String descr    = st.nextToken( );
                    float preco    = Float.parseFloat( st.nextToken() );
                    registra.incluirEspecProd(id, descr, preco);
                }
                catch( NumberFormatException e )
                { System.err.println( "Desconsiderando linhas mal formatadas " + linha ); }
            }
        }
        catch( IOException e )
        { System.err.println( e ); }

        System.out.println( "Arquivo lido..." );
        System.out.println(registra.getCatProd().getEspecProd(1).toString());
        System.out.println(registra.getCatProd().getEspecProd(2).toString());
        System.out.println(registra.getCatProd().getEspecProd(3).toString());
        System.out.println(registra.getCatProd().getEspecProd(4).toString());

    }

    void iniciarVenda(Scanner input) {
        registra.iniciarVenda();
        boolean encerraVenda = false;
        int opcaoVenda = 1;
        int codProd;
        int quantProd;
        while(!encerraVenda) {
            System.out.println("Digite 1 para incluir produto; 2 para concluir venda");
            opcaoVenda = input.nextInt();
            switch(opcaoVenda) {
                case 1:
                    System.out.println("Digite o código do produto");
                    codProd = input.nextInt();
                    System.out.println("Digite o numero de unidades");
                    quantProd = input.nextInt();
                    registra.incluirItem(codProd,quantProd);
                    break;
                case 2:
                    float total = registra.gettotal();
                    System.out.println("Total venda: " + total);
                    System.out.println("Informe valor pago");
                    float valorPago = input.nextFloat();
                    System.out.println("Troco = " + registra.getTroco(valorPago));
                    encerraVenda = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }
    }
}

class Registradora {



    private CatalogoProdutos catProd = new CatalogoProdutos();
    private Venda venda;

    public CatalogoProdutos getCatProd() {
        return catProd;
    }

    void incluirEspecProd(int id, String d, float p) {
        catProd.incluirEspecProd(id, d, p);
    }

    void iniciarVenda() {
        venda = new Venda();
    }

    void incluirItem(int codProd, int quantProd) {
        EspecificacaoProduto ep = catProd.getEspecProd(codProd);
        venda.incluirItem(ep,quantProd);


    }

    float gettotal() {
        return venda.getTotal();
    }

    float getTroco(float valorPago) {return venda.getTroco(valorPago);}
}

class CatalogoProdutos {
    Map<Integer, EspecificacaoProduto> mapaEspecs = new HashMap<Integer, EspecificacaoProduto>();

    EspecificacaoProduto getEspecProd(int id) {
        return mapaEspecs.get(id);
    }

    void incluirEspecProd(int id, String d, float p) {

        EspecificacaoProduto ep = new EspecificacaoProduto(id,d,p);
        mapaEspecs.put(id,ep);

    }
}

class EspecificacaoProduto {
    private int id;
    private String descricao;
    private float preco;


    public String getDescricao() {
        return descricao;
    }

    public float getPreco() {
        return preco;
    }


    public EspecificacaoProduto(int id, String descricao, float preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String toString() {
        return id + " " + descricao + " " + preco;
    }


}
