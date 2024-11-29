using GreenGarden.Models;

namespace GreenGarden.Services.Interfaces;

using System.Collections.Generic;
using System.Threading.Tasks;

public interface IPedidoService
{
    Task<Pedido> GetByIdAsync(int id);
    Task<IEnumerable<Pedido>> GetAllAsync();
    Task AddAsync(Pedido pedido);
    Task UpdateAsync(Pedido pedido);
    Task DeleteAsync(int id);
}
