namespace GreenGarden.Models;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

public class Cliente
{
    [Key]
    public int IdCliente { get; set; }

    [Required]
    [MaxLength(100)]
    public string Nome { get; set; }

    [Required]
    [MaxLength(11)] // CPF no formato sem máscara
    public string Cpf { get; set; }

    [Required]
    [MaxLength(15)]
    public string Telefone { get; set; }

    [Required]
    [MaxLength(100)]
    public string Email { get; set; }

    [Required]
    public string Senha { get; set; }

    // Relação 1:1 com Endereco
    [ForeignKey("Endereco")]
    public int EnderecoId { get; set; }
    public Endereco Endereco { get; set; }

    // Relação 1:N com Pedidos
    public ICollection<Pedido> Pedidos { get; set; }

    // Relação 1:N com Carrinhos
    public ICollection<Carrinho> Carrinhos { get; set; }
}
