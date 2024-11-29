using GreenGarden.Models;
using GreenGarden.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace GreenGarden.Controllers;
[Route("api/[controller]")]
[ApiController]
public class ItemCarrinhoController : ControllerBase
{
    private readonly IItemCarrinhoService _itemCarrinhoService;

    public ItemCarrinhoController(IItemCarrinhoService itemCarrinhoService)
    {
        _itemCarrinhoService = itemCarrinhoService;
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<ItemCarrinho>> GetItemCarrinhoById(int id)
    {
        var itemCarrinho = await _itemCarrinhoService.GetByIdAsync(id);
        if (itemCarrinho == null)
        {
            return NotFound();
        }
        return Ok(itemCarrinho);
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<ItemCarrinho>>> GetItemCarrinhos()
    {
        var itemCarrinhos = await _itemCarrinhoService.GetAllAsync();
        return Ok(itemCarrinhos);
    }

    [HttpPost]
    public async Task<ActionResult<ItemCarrinho>> CreateItemCarrinho([FromBody] ItemCarrinho itemCarrinho)
    {
        await _itemCarrinhoService.AddAsync(itemCarrinho);
        return CreatedAtAction(nameof(GetItemCarrinhoById), new { id = itemCarrinho.IdItemCarrinho }, itemCarrinho);
    }

    [HttpPut("{id}")]
    public async Task<ActionResult> UpdateItemCarrinho(int id, [FromBody] ItemCarrinho itemCarrinho)
    {
        if (id != itemCarrinho.IdItemCarrinho)
        {
            return BadRequest();
        }

        await _itemCarrinhoService.UpdateAsync(itemCarrinho);
        return NoContent();
    }

    [HttpDelete("{id}")]
    public async Task<ActionResult> DeleteItemCarrinho(int id)
    {
        var itemCarrinho = await _itemCarrinhoService.GetByIdAsync(id);
        if (itemCarrinho == null)
        {
            return NotFound();
        }

        await _itemCarrinhoService.DeleteAsync(id);
        return NoContent();
    }
}
