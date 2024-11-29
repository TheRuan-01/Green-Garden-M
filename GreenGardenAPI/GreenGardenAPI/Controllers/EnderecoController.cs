using GreenGarden.Models;
using GreenGarden.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace GreenGarden.Controllers;

[Route("api/[controller]")]
[ApiController]
public class EnderecoController : ControllerBase
{
    private readonly IEnderecoService _enderecoService;

    public EnderecoController(IEnderecoService enderecoService)
    {
        _enderecoService = enderecoService;
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<Endereco>> GetEnderecoById(int id)
    {
        var endereco = await _enderecoService.GetByIdAsync(id);
        if (endereco == null)
        {
            return NotFound();
        }
        return Ok(endereco);
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<Endereco>>> GetEnderecos()
    {
        var enderecos = await _enderecoService.GetAllAsync();
        return Ok(enderecos);
    }

    [HttpPost]
    public async Task<ActionResult<Endereco>> CreateEndereco([FromBody] Endereco endereco)
    {
        await _enderecoService.AddAsync(endereco);
        return CreatedAtAction(nameof(GetEnderecoById), new { id = endereco.IdEndereco }, endereco);
    }

    [HttpPut("{id}")]
    public async Task<ActionResult> UpdateEndereco(int id, [FromBody] Endereco endereco)
    {
        if (id != endereco.IdEndereco)
        {
            return BadRequest();
        }

        await _enderecoService.UpdateAsync(endereco);
        return NoContent();
    }

    [HttpDelete("{id}")]
    public async Task<ActionResult> DeleteEndereco(int id)
    {
        var endereco = await _enderecoService.GetByIdAsync(id);
        if (endereco == null)
        {
            return NotFound();
        }

        await _enderecoService.DeleteAsync(id);
        return NoContent();
    }
}
