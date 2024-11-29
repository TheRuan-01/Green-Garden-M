using GreenGarden.Models;

namespace GreenGarden.Services.Interfaces;
using System.Collections.Generic;
using System.Threading.Tasks;

public interface IEnderecoService
{
    Task<Endereco> GetByIdAsync(int id);
    Task<IEnumerable<Endereco>> GetAllAsync();
    Task AddAsync(Endereco endereco);
    Task UpdateAsync(Endereco endereco);
    Task DeleteAsync(int id);
}
