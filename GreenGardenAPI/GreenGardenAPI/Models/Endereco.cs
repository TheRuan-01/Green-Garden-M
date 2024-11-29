namespace GreenGarden.Models;
using System.ComponentModel.DataAnnotations;

public class Endereco
{
    [Key]
    public int IdEndereco { get; set; }

    [Required]
    [MaxLength(200)]
    public string Rua { get; set; }

    [Required]
    [MaxLength(10)]
    public string Numero { get; set; }

    [MaxLength(100)]
    public string Complemento { get; set; }

    [Required]
    [MaxLength(15)] // CEP sem máscara
    public string Cep { get; set; }

    [Required]
    [MaxLength(100)]
    public string Cidade { get; set; }

    [Required]
    [MaxLength(15)] // Estado em formato UF
    public string Estado { get; set; }

}
