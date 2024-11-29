using GreenGarden.Models;

namespace GreenGarden.Services.Interfaces;
using System.Collections.Generic;
using System.Threading.Tasks;

public interface ICarrinhoService
{
    Task<Carrinho> GetByIdAsync(int id);
    Task<IEnumerable<Carrinho>> GetAllAsync();
    Task AddAsync(Carrinho carrinho);
    Task UpdateAsync(Carrinho carrinho);
    Task DeleteAsync(int id);
    Task<Carrinho> GetByClienteIdAsync(int clienteId); // Para buscar carrinho por cliente
}
