package Supermercado;

import java.util.ArrayList;

public class Venda {
    ArrayList<ItemDeVenda> itensDeVenda;

    public Venda() {
        this.itensDeVenda = new ArrayList<ItemDeVenda>();
    }

    public void incluirItem(EspecificacaoProduto ep, int quantProd) {
        itensDeVenda.add(new ItemDeVenda(ep,quantProd));
    }

    public float getTotal() {
        float total = 0.f;
        for (ItemDeVenda item: itensDeVenda)
        {
            total += item.getSubtotal();
        }
        return total;
    }

    public float getTroco(float valorPago) {
        return valorPago - this.getTotal();
    }
}

class ItemDeVenda {
    private int quant;
    private EspecificacaoProduto minhaEspec;

    public ItemDeVenda(EspecificacaoProduto minhaEspec, int quant) {
        this.quant = quant;
        this.minhaEspec = minhaEspec;
    }

    float getSubtotal() {
        return quant * minhaEspec.getPreco();
    }
}
