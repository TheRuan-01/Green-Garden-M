using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;
using GreenGarden.DTO;
using GreenGarden.Models;
using GreenGarden.Services.Interfaces;
using GreenGarden.Data;

public class ClienteService : IClienteService
{
    private readonly AppDbContext _context;

    public ClienteService(AppDbContext context)
    {
        _context = context;
    }

    public async Task<Cliente> GetByIdAsync(int id)
    {
        return await _context.Clientes.FindAsync(id);
    }

    public async Task<IEnumerable<Cliente>> GetAllAsync()
    {
        return await _context.Clientes.ToListAsync();
    }

    public async Task AddAsync(Cliente cliente)
    {
        await _context.Clientes.AddAsync(cliente);
        await _context.SaveChangesAsync();
    }

    public async Task<Cliente?> FindByEmailAsync(string email)
    {
        // Busca o cliente com base no email (case-insensitive)
        return await _context.Clientes
            .FirstOrDefaultAsync(c => c.Email.ToLower() == email.ToLower());
    }
    public async Task UpdateAsync(UserDTO clienteDto, string email)
    {
        var clienteExistente = await _context.Clientes
            .FirstOrDefaultAsync(c => c.Email == email);

        if (clienteExistente == null)
        {
            throw new Exception("Usuario não encontrado.");
        }
        
        clienteExistente.Nome = clienteDto.Nome ?? clienteExistente.Nome;
        clienteExistente.Email = clienteDto.Email ?? clienteExistente.Email;
        clienteExistente.Telefone = clienteDto.Telefone ?? clienteExistente.Telefone;
        clienteExistente.Cpf = clienteDto.Cpf ?? clienteExistente.Cpf;
        clienteExistente.Senha = clienteDto.Senha ?? clienteExistente.Senha;
        
        await _context.SaveChangesAsync();
    }

    public async Task DeleteAsync(int id)
    {
        var cliente = await GetByIdAsync(id);
        if (cliente != null)
        {
            _context.Clientes.Remove(cliente);
            await _context.SaveChangesAsync();
        }
    }


    // Registrar um novo cliente
    public async Task<Cliente> RegistrarClienteAsync(Cliente cliente)
    {
        // Verifica se o cliente já existe com o mesmo email
        var clienteExistente = await _context.Clientes
            .FirstOrDefaultAsync(c => c.Email == cliente.Email);

        if (clienteExistente != null)
        {
            throw new Exception("Cliente já existe");
        }

        // Adiciona o novo cliente ao banco de dados
        _context.Clientes.Add(cliente);
        await _context.SaveChangesAsync();
        
        return cliente;
    }

    public async Task<Cliente> LoginAsync(string email, string senha)
    {
        var cliente = await _context.Clientes
            .FirstOrDefaultAsync(c => c.Email == email && c.Senha == senha);

        if (cliente == null)
        {
            throw new Exception("Email ou senha incorretos");
        }

        return cliente;
    } 
    
}