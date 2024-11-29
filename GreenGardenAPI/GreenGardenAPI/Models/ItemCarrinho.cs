using System.ComponentModel.DataAnnotations.Schema;

namespace GreenGarden.Models;

using System.ComponentModel.DataAnnotations;

public class ItemCarrinho
{
    [Key]
    public int IdItemCarrinho { get; set; }

    [Required]
    public int Quantidade { get; set; } // Quantidade de unidades do produto no carrinho

    [Required]
    [Column(TypeName = "decimal(10,2)")]
    public decimal PrecoUnitario { get; set; } // Preço de uma unidade do produto

    [Column(TypeName = "decimal(10,2)")]
    public decimal PrecoTotal { get; set; } // Preço total (Quantidade * PreçoUnitario)

    // Status do item no carrinho (ex: em espera, removido, etc.)
    [MaxLength(20)]
    public string Status { get; set; }

    // Relação N:1 com Produto
    [ForeignKey("Produto")]
    public int ProdutoId { get; set; }
    public Produto Produto { get; set; }

    // Relação N:1 com Carrinho
    [ForeignKey("Carrinho")]
    public int CarrinhoId { get; set; }
    public Carrinho Carrinho { get; set; }
}
