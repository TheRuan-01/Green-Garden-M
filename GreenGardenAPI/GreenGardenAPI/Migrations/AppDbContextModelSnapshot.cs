﻿// <auto-generated />
using System;
using GreenGarden.Data;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

#nullable disable

namespace GreenGardenAPI.Migrations
{
    [DbContext(typeof(AppDbContext))]
    partial class AppDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "9.0.0")
                .HasAnnotation("Relational:MaxIdentifierLength", 128);

            SqlServerModelBuilderExtensions.UseIdentityColumns(modelBuilder);

            modelBuilder.Entity("GreenGarden.Models.Carrinho", b =>
                {
                    b.Property<int>("IdCarrinho")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    SqlServerPropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("IdCarrinho"));

                    b.Property<int>("ClienteId")
                        .HasColumnType("int");

                    b.Property<DateTime>("DataCriacao")
                        .HasColumnType("datetime2");

                    b.Property<DateTime?>("DataUltimaAtualizacao")
                        .HasColumnType("datetime2");

                    b.HasKey("IdCarrinho");

                    b.HasIndex("ClienteId");

                    b.ToTable("Carrinhos");
                });

            modelBuilder.Entity("GreenGarden.Models.Cliente", b =>
                {
                    b.Property<int>("IdCliente")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    SqlServerPropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("IdCliente"));

                    b.Property<string>("Cpf")
                        .IsRequired()
                        .HasMaxLength(11)
                        .HasColumnType("nvarchar(11)");

                    b.Property<string>("Email")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.Property<int>("EnderecoId")
                        .HasColumnType("int");

                    b.Property<string>("Nome")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.Property<string>("Senha")
                        .IsRequired()
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Telefone")
                        .IsRequired()
                        .HasMaxLength(15)
                        .HasColumnType("nvarchar(15)");

                    b.HasKey("IdCliente");

                    b.HasIndex("EnderecoId");

                    b.ToTable("Clientes");
                });

            modelBuilder.Entity("GreenGarden.Models.Endereco", b =>
                {
                    b.Property<int>("IdEndereco")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    SqlServerPropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("IdEndereco"));

                    b.Property<string>("Cep")
                        .IsRequired()
                        .HasMaxLength(15)
                        .HasColumnType("nvarchar(15)");

                    b.Property<string>("Cidade")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.Property<string>("Complemento")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.Property<string>("Estado")
                        .IsRequired()
                        .HasMaxLength(15)
                        .HasColumnType("nvarchar(15)");

                    b.Property<string>("Numero")
                        .IsRequired()
                        .HasMaxLength(10)
                        .HasColumnType("nvarchar(10)");

                    b.Property<string>("Rua")
                        .IsRequired()
                        .HasMaxLength(200)
                        .HasColumnType("nvarchar(200)");

                    b.HasKey("IdEndereco");

                    b.ToTable("Enderecos");
                });

            modelBuilder.Entity("GreenGarden.Models.ItemCarrinho", b =>
                {
                    b.Property<int>("IdItemCarrinho")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    SqlServerPropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("IdItemCarrinho"));

                    b.Property<int>("CarrinhoId")
                        .HasColumnType("int");

                    b.Property<decimal>("PrecoTotal")
                        .HasColumnType("decimal(10,2)");

                    b.Property<decimal>("PrecoUnitario")
                        .HasColumnType("decimal(10,2)");

                    b.Property<int>("ProdutoId")
                        .HasColumnType("int");

                    b.Property<int>("Quantidade")
                        .HasColumnType("int");

                    b.Property<string>("Status")
                        .IsRequired()
                        .HasMaxLength(20)
                        .HasColumnType("nvarchar(20)");

                    b.HasKey("IdItemCarrinho");

                    b.HasIndex("CarrinhoId");

                    b.HasIndex("ProdutoId");

                    b.ToTable("ItensCarrinho");
                });

            modelBuilder.Entity("GreenGarden.Models.Pedido", b =>
                {
                    b.Property<int>("IdPedido")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    SqlServerPropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("IdPedido"));

                    b.Property<int>("CarrinhoId")
                        .HasColumnType("int");

                    b.Property<int?>("ClienteIdCliente")
                        .HasColumnType("int");

                    b.Property<DateTime?>("DataPagamento")
                        .HasColumnType("datetime2");

                    b.Property<DateTime>("DataPedido")
                        .HasColumnType("datetime2");

                    b.Property<string>("FormaPagamento")
                        .IsRequired()
                        .HasMaxLength(50)
                        .HasColumnType("nvarchar(50)");

                    b.Property<string>("Status")
                        .IsRequired()
                        .HasMaxLength(20)
                        .HasColumnType("nvarchar(20)");

                    b.Property<decimal>("Total")
                        .HasColumnType("decimal(10,2)");

                    b.Property<decimal>("TotalComDesconto")
                        .HasColumnType("decimal(10,2)");

                    b.HasKey("IdPedido");

                    b.HasIndex("CarrinhoId")
                        .IsUnique();

                    b.HasIndex("ClienteIdCliente");

                    b.ToTable("Pedidos");
                });

            modelBuilder.Entity("GreenGarden.Models.Produto", b =>
                {
                    b.Property<int>("IdProduto")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    SqlServerPropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("IdProduto"));

                    b.Property<string>("Descricao")
                        .IsRequired()
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Imagem")
                        .IsRequired()
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Nome")
                        .IsRequired()
                        .HasMaxLength(200)
                        .HasColumnType("nvarchar(200)");

                    b.Property<decimal>("Preco")
                        .HasColumnType("decimal(10,2)");

                    b.HasKey("IdProduto");

                    b.ToTable("Produtos");
                });

            modelBuilder.Entity("GreenGarden.Models.Carrinho", b =>
                {
                    b.HasOne("GreenGarden.Models.Cliente", "Cliente")
                        .WithMany("Carrinhos")
                        .HasForeignKey("ClienteId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Cliente");
                });

            modelBuilder.Entity("GreenGarden.Models.Cliente", b =>
                {
                    b.HasOne("GreenGarden.Models.Endereco", "Endereco")
                        .WithMany()
                        .HasForeignKey("EnderecoId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Endereco");
                });

            modelBuilder.Entity("GreenGarden.Models.ItemCarrinho", b =>
                {
                    b.HasOne("GreenGarden.Models.Carrinho", "Carrinho")
                        .WithMany("Itens")
                        .HasForeignKey("CarrinhoId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("GreenGarden.Models.Produto", "Produto")
                        .WithMany()
                        .HasForeignKey("ProdutoId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Carrinho");

                    b.Navigation("Produto");
                });

            modelBuilder.Entity("GreenGarden.Models.Pedido", b =>
                {
                    b.HasOne("GreenGarden.Models.Carrinho", "Carrinho")
                        .WithOne("Pedido")
                        .HasForeignKey("GreenGarden.Models.Pedido", "CarrinhoId")
                        .OnDelete(DeleteBehavior.NoAction)
                        .IsRequired();

                    b.HasOne("GreenGarden.Models.Cliente", null)
                        .WithMany("Pedidos")
                        .HasForeignKey("ClienteIdCliente");

                    b.Navigation("Carrinho");
                });

            modelBuilder.Entity("GreenGarden.Models.Carrinho", b =>
                {
                    b.Navigation("Itens");

                    b.Navigation("Pedido")
                        .IsRequired();
                });

            modelBuilder.Entity("GreenGarden.Models.Cliente", b =>
                {
                    b.Navigation("Carrinhos");

                    b.Navigation("Pedidos");
                });
#pragma warning restore 612, 618
        }
    }
}