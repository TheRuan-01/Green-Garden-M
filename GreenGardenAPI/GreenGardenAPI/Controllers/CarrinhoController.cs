using GreenGarden.Models;
using GreenGarden.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace GreenGarden.Controllers;
[Route("api/[controller]")]
[ApiController]
public class CarrinhoController : ControllerBase
{
    private readonly ICarrinhoService _carrinhoService;

    public CarrinhoController(ICarrinhoService carrinhoService)
    {
        _carrinhoService = carrinhoService;
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<Carrinho>> GetCarrinhoById(int id)
    {
        var carrinho = await _carrinhoService.GetByIdAsync(id);
        if (carrinho == null)
        {
            return NotFound();
        }
        return Ok(carrinho);
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<Carrinho>>> GetCarrinhos()
    {
        var carrinhos = await _carrinhoService.GetAllAsync();
        return Ok(carrinhos);
    }

    [HttpPost]
    public async Task<ActionResult<Carrinho>> CreateCarrinho([FromBody] Carrinho carrinho)
    {
        await _carrinhoService.AddAsync(carrinho);
        return CreatedAtAction(nameof(GetCarrinhoById), new { id = carrinho.IdCarrinho }, carrinho);
    }

    [HttpPut("{id}")]
    public async Task<ActionResult> UpdateCarrinho(int id, [FromBody] Carrinho carrinho)
    {
        if (id != carrinho.IdCarrinho)
        {
            return BadRequest();
        }

        await _carrinhoService.UpdateAsync(carrinho);
        return NoContent();
    }

    [HttpDelete("{id}")]
    public async Task<ActionResult> DeleteCarrinho(int id)
    {
        var carrinho = await _carrinhoService.GetByIdAsync(id);
        if (carrinho == null)
        {
            return NotFound();
        }

        await _carrinhoService.DeleteAsync(id);
        return NoContent();
    }
}
