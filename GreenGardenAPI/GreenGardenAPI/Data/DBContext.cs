using GreenGarden.Models;
using Microsoft.EntityFrameworkCore;

namespace GreenGarden.Data;

public class AppDbContext : DbContext
{
    public DbSet<Cliente> Clientes { get; set; }
    public DbSet<Endereco> Enderecos { get; set; }
    public DbSet<Produto> Produtos { get; set; }
    public DbSet<ItemCarrinho> ItensCarrinho { get; set; }
    public DbSet<Carrinho> Carrinhos { get; set; }
    public DbSet<Pedido> Pedidos { get; set; }

    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }
    
  protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);

        // Remover a cascata de exclusão do relacionamento Cliente -> Pedido


        // Remover a cascata de exclusão do relacionamento Carrinho -> Pedido
        modelBuilder.Entity<Pedido>()
            .HasOne(p => p.Carrinho)
            .WithOne(c => c.Pedido)
            .HasForeignKey<Pedido>(p => p.CarrinhoId)
            .OnDelete(DeleteBehavior.NoAction); // Não propaga cascata


        // Manter a exclusão em cascata apenas na relação de Carrinho -> ItemCarrinho
        modelBuilder.Entity<ItemCarrinho>()
            .HasOne(ic => ic.Carrinho)
            .WithMany(c => c.Itens)
            .HasForeignKey(ic => ic.CarrinhoId)
            .OnDelete(DeleteBehavior.Cascade); // Mantém cascata
        
    }
}