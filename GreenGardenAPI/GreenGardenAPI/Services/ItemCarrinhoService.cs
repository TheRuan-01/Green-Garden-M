using GreenGarden.Data;
using GreenGarden.Models;
using GreenGarden.Services.Interfaces;

namespace GreenGarden.Services;

using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;

public class ItemCarrinhoService : IItemCarrinhoService
{
    private readonly AppDbContext _context;

    public ItemCarrinhoService(AppDbContext context)
    {
        _context = context;
    }

    public async Task<ItemCarrinho> GetByIdAsync(int id)
    {
        return await _context.ItensCarrinho.FindAsync(id);
    }

    public async Task<IEnumerable<ItemCarrinho>> GetAllAsync()
    {
        return await _context.ItensCarrinho.ToListAsync();
    }

    public async Task AddAsync(ItemCarrinho itemCarrinho)
    {
        await _context.ItensCarrinho.AddAsync(itemCarrinho);
        await _context.SaveChangesAsync();
    }

    public async Task UpdateAsync(ItemCarrinho itemCarrinho)
    {
        _context.ItensCarrinho.Update(itemCarrinho);
        await _context.SaveChangesAsync();
    }

    public async Task DeleteAsync(int id)
    {
        var itemCarrinho = await GetByIdAsync(id);
        if (itemCarrinho != null)
        {
            _context.ItensCarrinho.Remove(itemCarrinho);
            await _context.SaveChangesAsync();
        }
    }
}
