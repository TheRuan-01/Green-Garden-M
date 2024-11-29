namespace GreenGarden.Models;

using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

public class Pedido
{
    [Key]
    public int IdPedido { get; set; }

    [Required]
    public DateTime DataPedido { get; set; } // Data em que o pedido foi feito

    public DateTime? DataPagamento { get; set; } // Data de pagamento (caso já tenha sido pago)

    [Column(TypeName = "decimal(10,2)")]
    public decimal Total { get; set; } // Total do pedido (preço dos itens + impostos, etc.)

    [Column(TypeName = "decimal(10,2)")]
    public decimal TotalComDesconto { get; set; } // Total com desconto, se aplicável

    [MaxLength(50)]
    public string FormaPagamento { get; set; } // Ex: "Cartão de Crédito", "Boleto", etc.

    [MaxLength(20)]
    public string Status { get; set; } // Status do pedido (ex: "Pendente", "Pago", "Enviado", etc.)

    // Relação 1:1 com Carrinho
    [ForeignKey("Carrinho")]
    public int CarrinhoId { get; set; }
    public Carrinho Carrinho { get; set; }

    // Relação N:1 com Cliente
  
}
