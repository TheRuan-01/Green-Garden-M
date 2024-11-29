using GreenGarden.Models;

namespace GreenGarden.Services.Interfaces;
using System.Collections.Generic;
using System.Threading.Tasks;

public interface IItemCarrinhoService
{
    Task<ItemCarrinho> GetByIdAsync(int id);
    Task<IEnumerable<ItemCarrinho>> GetAllAsync();
    Task AddAsync(ItemCarrinho itemCarrinho);
    Task UpdateAsync(ItemCarrinho itemCarrinho);
    Task DeleteAsync(int id);
}
