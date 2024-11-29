namespace GreenGarden.DTO;
public record UserDTO
{
    public string Nome { get; set; }
    public string Cpf { get; set; }
    public string Telefone { get; set; }
    public string Email { get; set; }
    public string Senha { get; set; }
}
