using System.ComponentModel.DataAnnotations.Schema;

namespace GreenGarden.Models;

using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;


public class Carrinho
{
    [Key]
    public int IdCarrinho { get; set; }

    [Required]
    public DateTime DataCriacao { get; set; } // Data de criação do carrinho

    public DateTime? DataUltimaAtualizacao { get; set; } // Data da última atualização

    // Relação 1:N com ItemCarrinho
    public ICollection<ItemCarrinho> Itens { get; set; }

    // Relação 1:1 com Cliente
    [ForeignKey("Cliente")]
    public int ClienteId { get; set; }
    public Cliente Cliente { get; set; }

    // Relação 1:1 com Pedido
    public Pedido Pedido { get; set; }
}