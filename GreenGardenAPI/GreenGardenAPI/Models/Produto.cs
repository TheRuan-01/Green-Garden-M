namespace GreenGarden.Models;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

public class Produto
{
    [Key]
    public int IdProduto { get; set; }

    [Required]
    [MaxLength(200)]
    public string Nome { get; set; }

    [Required]
    [Column(TypeName = "decimal(10,2)")]
    public decimal Preco { get; set; }

    public string Descricao { get; set; }

    public string Imagem { get; set; } // URL ou caminho da imagem

}
