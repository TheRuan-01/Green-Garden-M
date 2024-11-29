using GreenGarden.Models;
using GreenGarden.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace GreenGarden.Controllers;

[Route("api/[controller]")]
[ApiController]
public class PedidoController : ControllerBase
{
    private readonly IPedidoService _pedidoService;

    public PedidoController(IPedidoService pedidoService)
    {
        _pedidoService = pedidoService;
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<Pedido>> GetPedidoById(int id)
    {
        var pedido = await _pedidoService.GetByIdAsync(id);
        if (pedido == null)
        {
            return NotFound();
        }
        return Ok(pedido);
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<Pedido>>> GetPedidos()
    {
        var pedidos = await _pedidoService.GetAllAsync();
        return Ok(pedidos);
    }

    [HttpPost]
    public async Task<ActionResult<Pedido>> CreatePedido([FromBody] Pedido pedido)
    {
        await _pedidoService.AddAsync(pedido);
        return CreatedAtAction(nameof(GetPedidoById), new { id = pedido.IdPedido }, pedido);
    }

    [HttpPut("{id}")]
    public async Task<ActionResult> UpdatePedido(int id, [FromBody] Pedido pedido)
    {
        if (id != pedido.IdPedido)
        {
            return BadRequest();
        }

        await _pedidoService.UpdateAsync(pedido);
        return NoContent();
    }

    [HttpDelete("{id}")]
    public async Task<ActionResult> DeletePedido(int id)
    {
        var pedido = await _pedidoService.GetByIdAsync(id);
        if (pedido == null)
        {
            return NotFound();
        }

        await _pedidoService.DeleteAsync(id);
        return NoContent();
    }
}
