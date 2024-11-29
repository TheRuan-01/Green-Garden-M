using GreenGarden.DTO;
using GreenGarden.Models;

namespace GreenGarden.Services.Interfaces;
using System.Collections.Generic;
using System.Threading.Tasks;

public interface IClienteService
{
    Task<Cliente> GetByIdAsync(int id);
    Task<IEnumerable<Cliente>> GetAllAsync();
    Task AddAsync(Cliente cliente);
    Task UpdateAsync(UserDTO cliente, string email);
    Task DeleteAsync(int id);
    Task<Cliente> RegistrarClienteAsync(Cliente cliente);
    Task<Cliente> LoginAsync(string email, string senha);

}
