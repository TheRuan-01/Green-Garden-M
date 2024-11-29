using GreenGarden.DTO;
using GreenGarden.Models;
using GreenGarden.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace GreenGarden.Controllers;
using GreenGarden.Models;
using GreenGarden.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

[Route("api/[controller]")]
[ApiController]
public class ClientesController : ControllerBase
{
    private readonly IClienteService _clienteService;

    public ClientesController(IClienteService clienteService)
    {
        _clienteService = clienteService;
    }

    // GET: api/clientes
    [HttpGet]
    public async Task<ActionResult<IEnumerable<Cliente>>> GetAll()
    {
        var clientes = await _clienteService.GetAllAsync();
        return Ok(clientes);
    }

    // GET: api/clientes/{id}
    [HttpGet("{id}")]
    public async Task<ActionResult<Cliente>> GetById(int id)
    {
        var cliente = await _clienteService.GetByIdAsync(id);
        if (cliente == null)
        {
            return NotFound();
        }
        return Ok(cliente);
    }

// POST: api/clientes/registrar
    [HttpPost("registrar")]
    public async Task<ActionResult<Cliente>> RegistrarCliente([FromBody] ClienteDTO clienteDTO)
    {
        try
        {
            // Mapeia os dados do ClienteDTO para a entidade Cliente
            var cliente = new Cliente
            {
                Nome = clienteDTO.Nome,
                Cpf = clienteDTO.Cpf,
                Telefone = clienteDTO.Telefone,
                Email = clienteDTO.Email,
                Senha = clienteDTO.Senha,
                Endereco = new Endereco
                {
                    Rua = clienteDTO.Endereco.Rua,
                    Numero = clienteDTO.Endereco.Numero,
                    Complemento = clienteDTO.Endereco.Complemento,
                    Cep = clienteDTO.Endereco.Cep,
                    Cidade = clienteDTO.Endereco.Cidade,
                    Estado = clienteDTO.Endereco.Estado
                }
            };
            // Chama o serviço para registrar o cliente
            var novoCliente = await _clienteService.RegistrarClienteAsync(cliente);

            // Retorna o cliente completo (não o DTO)
            return CreatedAtAction(nameof(GetById), new { id = novoCliente.IdCliente }, novoCliente);
        }
        catch (Exception ex)
        {
            return BadRequest(new { message = ex.Message });
        }
    }

    // POST: api/clientes/login
    [HttpPost("login")]
    public async Task<ActionResult<Cliente>> Login([FromBody] LoginDTO loginDTO)
    {
        try
        {
            var cliente = await _clienteService.LoginAsync(loginDTO.Email, loginDTO.Senha);
            return Ok(cliente);
        }
        catch (Exception ex)
        {
            return BadRequest(new { message = ex.Message });
        }
    }

    // PUT: api/clientes/{id}
    [HttpPut("{email}")]
    public async Task<IActionResult> Update(string email, [FromBody] UserDTO cliente)
    {
        try
        {
            await _clienteService.UpdateAsync(cliente, email);
            return NoContent();
        }
        catch
        {
            return NotFound();
        }
    }

    // DELETE: api/clientes/{id}
    [HttpDelete("{id}")]
    public async Task<IActionResult> Delete(int id)
    {
        try
        {
            await _clienteService.DeleteAsync(id);
            return NoContent();
        }
        catch
        {
            return NotFound();
        }
    }
}
