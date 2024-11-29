using GreenGarden.Models;
using GreenGarden.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace GreenGarden.Controllers;

[Route("api/[controller]")]
[ApiController]
public class ProdutoController : ControllerBase
{
    private readonly IProdutoService _produtoService;

    public ProdutoController(IProdutoService produtoService)
    {
        _produtoService = produtoService;
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<Produto>> GetProdutoById(int id)
    {
        var produto = await _produtoService.GetByIdAsync(id);
        if (produto == null)
        {
            return NotFound();
        }
        return Ok(produto);
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<Produto>>> GetProdutos()
    {
        var produtos = await _produtoService.GetAllAsync();
        return Ok(produtos);
    }

    [HttpPost]
    public async Task<ActionResult<Produto>> CreateProduto([FromBody] Produto produto)
    {
        await _produtoService.AddAsync(produto);
        return CreatedAtAction(nameof(GetProdutoById), new { id = produto.IdProduto }, produto);
    }

    [HttpPut("{id}")]
    public async Task<ActionResult> UpdateProduto(int id, [FromBody] Produto produto)
    {
        if (id != produto.IdProduto)
        {
            return BadRequest();
        }

        await _produtoService.UpdateAsync(produto);
        return NoContent();
    }

    [HttpDelete("{id}")]
    public async Task<ActionResult> DeleteProduto(int id)
    {
        var produto = await _produtoService.GetByIdAsync(id);
        if (produto == null)
        {
            return NotFound();
        }

        await _produtoService.DeleteAsync(id);
        return NoContent();
    }
}
