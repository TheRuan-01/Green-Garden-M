using GreenGarden.Models;
using GreenGarden.Services.Interfaces;
using GreenGarden.Models;
using GreenGarden.Data;

namespace GreenGarden.Services.Interfaces;

using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;

public class EnderecoService : IEnderecoService
{
    private readonly AppDbContext _context;

    public EnderecoService(AppDbContext context)
    {
        _context = context;
    }

    public async Task<Endereco> GetByIdAsync(int id)
    {
        return await _context.Enderecos.FindAsync(id);
    }

    public async Task<IEnumerable<Endereco>> GetAllAsync()
    {
        return await _context.Enderecos.ToListAsync();
    }

    public async Task AddAsync(Endereco endereco)
    {
        await _context.Enderecos.AddAsync(endereco);
        await _context.SaveChangesAsync();
    }

    public async Task UpdateAsync(Endereco endereco)
    {
        _context.Enderecos.Update(endereco);
        await _context.SaveChangesAsync();
    }

    public async Task DeleteAsync(int id)
    {
        var endereco = await GetByIdAsync(id);
        if (endereco != null)
        {
            _context.Enderecos.Remove(endereco);
            await _context.SaveChangesAsync();
        }
    }
}
