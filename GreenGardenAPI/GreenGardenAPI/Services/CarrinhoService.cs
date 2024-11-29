using GreenGarden.Models;
using GreenGarden.Services.Interfaces;
using GreenGarden.Data;
using Microsoft.EntityFrameworkCore;


using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;

public class CarrinhoService : ICarrinhoService
{
    private readonly AppDbContext _context;

    public CarrinhoService(AppDbContext context)
    {
        _context = context;
    }

    public async Task<Carrinho> GetByIdAsync(int id)
    {
        return await _context.Carrinhos.FindAsync(id);
    }

    public async Task<IEnumerable<Carrinho>> GetAllAsync()
    {
        return await _context.Carrinhos.ToListAsync();
    }

    public async Task AddAsync(Carrinho carrinho)
    {
        await _context.Carrinhos.AddAsync(carrinho);
        await _context.SaveChangesAsync();
    }

    public async Task UpdateAsync(Carrinho carrinho)
    {
        _context.Carrinhos.Update(carrinho);
        await _context.SaveChangesAsync();
    }

    public async Task DeleteAsync(int id)
    {
        var carrinho = await GetByIdAsync(id);
        if (carrinho != null)
        {
            _context.Carrinhos.Remove(carrinho);
            await _context.SaveChangesAsync();
        }
    }

    public async Task<Carrinho> GetByClienteIdAsync(int clienteId)
    {
        return await _context.Carrinhos
            .FirstOrDefaultAsync(c => c.ClienteId == clienteId);
    }
}
