using GreenGarden.Models;

namespace GreenGarden.Services.Interfaces;
using System.Collections.Generic;
using System.Threading.Tasks;

public interface IProdutoService
{
    Task<Produto> GetByIdAsync(int id);
    Task<IEnumerable<Produto>> GetAllAsync();
    Task AddAsync(Produto produto);
    Task UpdateAsync(Produto produto);
    Task DeleteAsync(int id);
}
